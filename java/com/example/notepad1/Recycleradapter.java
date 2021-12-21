package com.example.notepad1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad1.db.Note;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.MyViewHolder> {

    ArrayList<Note> noteArrayList;
    Context context;
    setnotefunction  listner;

    public Recycleradapter(Context context, ArrayList<Note> noteArrayList,setnotefunction listner) {
        this.context = context;
        this.noteArrayList = noteArrayList;
        this.listner =listner;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v, context, noteArrayList,listner); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText((String) noteArrayList.get(position).getMassage());
        holder.tvCreated.setText("created : "+ noteArrayList.get(position).getCreated_date());
        holder.tvModified.setText("Modified : " +noteArrayList.get(position).getModified_date());
        //holder.imageView.setImageResource((Integer ) personimagess.get(position));
        // implement setOnClickListener event on item view.

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       private final ArrayList<Note> arrayList;
       // setnotefunction lisner;
        TextView name,tvCreated,tvModified;
        ImageView imageView;
        Context context;


        public MyViewHolder(View itemView, Context context, ArrayList<Note> arrayList,setnotefunction listner) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.text_rey);
            tvCreated = (TextView) itemView.findViewById(R.id.tvCreateDate);
            tvModified = (TextView) itemView.findViewById(R.id.tvModifiedDate);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
               this.context =context;
               this.arrayList=arrayList;
               //this.lisner =listner;
            //  this.personimagess=personimagess;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               listner.deletenote(arrayList.get(getPosition()));


                }
            });

        }
        @Override
        public void onClick(View v) {

          listner.editnote(arrayList.get(getPosition()));
            //  Intent intent =new Intent(context,MainAcitivitydisyplay.class);
            //intent.putExtra("imageid", (Integer) personimagess.get(getAdapterPosition()));
            // context.startActivity(intent);

        }

    }


         interface  setnotefunction{

         void deletenote( Note note);


         void editnote(Note note);

         }


    }





