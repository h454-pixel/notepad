package com.example.notepad1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notepad1.db.MyAppDatabase;
import com.example.notepad1.db.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements Recycleradapter.setnotefunction {
    public static MyAppDatabase myAppDatabase;

    FloatingActionButton fab;
    RecyclerView recyclerView;

    Recycleradapter recycleradapter;
    ArrayList<Note> arrayListNote1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recycleradapter = new Recycleradapter(this, arrayListNote1, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleradapter); 

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });

    }


    private void getnotelist() {
        arrayListNote1.clear();
        arrayListNote1.addAll(myAppDatabase.myDao().getNote());
        recycleradapter.notifyDataSetChanged();// update adapter........
    }
                                  /// resume without this function one to second this does not work
    @Override
    protected void onResume() {
        super.onResume();
        getnotelist();
    }

    public void open(Note note) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("Are you sure, delete");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        myAppDatabase.myDao().deleteNote(note);
                        Toast.makeText(getApplicationContext(), "deleted", LENGTH_SHORT).show();
                        getnotelist();
                        arg0.dismiss();

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void deletenote(Note note) {
        open(note);


    }

    @Override
    public void editnote(Note note) {

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("id", note.getId());
        intent.putExtra("msg", note.getMassage());
        intent.putExtra("edit", true);
        startActivity(intent);


    }
}