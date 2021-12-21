package com.example.notepad1.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface noteDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addNote(Note note);

    @Query("select * from note" )
    public List<Note> getNote();

    @Delete
    public void deleteNote(Note note);

    @Update
    public void updateNote(Note note);



}
