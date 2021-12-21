package com.example.notepad1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1,exportSchema = true)


    public abstract class MyAppDatabase extends RoomDatabase
    {

        public abstract noteDao myDao();
    }




