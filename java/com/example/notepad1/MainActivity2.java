package com.example.notepad1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad1.db.MyAppDatabase;
import com.example.notepad1.db.Note;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    public static MyAppDatabase myAppDatabase;
    TextInputLayout inputLayout;
    EditText editText;
    TextView textView;

    boolean edit = false;
    Integer id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();

        inputLayout = (TextInputLayout) findViewById(R.id.text_input);
        editText = (EditText) findViewById(R.id.messageEditText);
        textView = (TextView) findViewById(R.id.textview_edit);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });


        edit = getIntent().getBooleanExtra("edit",false);
        if(edit) { /// editing click send data one to second
            String msg = getIntent().getStringExtra("msg");
            id=getIntent().getIntExtra("id",0);
            editText.setText(msg);
        }

    }


    private void saveNote() { // new item add

        String massage = editText.getText().toString();

        if (massage.equals("")) {

            Toast.makeText(this, "please enter the massage", Toast.LENGTH_SHORT).show();

            return;
        }

        if(edit){   // editing item
            Note note = new Note();

            note.setId(id);
            note.setMassage(massage);
            note.setModified_date(getCurrentDate());

            myAppDatabase.myDao().updateNote(note);

            Toast.makeText(MainActivity2.this, "edit succesfully", Toast.LENGTH_SHORT).show();
            finish();
        }else { /// new item first time
            Note note = new Note();

            // note.setId(-1);
            note.setMassage(massage);
            note.setCreated_date(getCurrentDate());
            note.setModified_date(getCurrentDate());
            myAppDatabase.myDao().addNote(note);

            Toast.makeText(MainActivity2.this, "add succesfully", Toast.LENGTH_SHORT).show();
            finish();
            editText.setText("");
        }
    }


    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}