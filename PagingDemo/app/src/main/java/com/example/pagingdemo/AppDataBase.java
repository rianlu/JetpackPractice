package com.example.pagingdemo;

import android.content.Context;
import android.database.DatabaseUtils;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase dataBase;

    static synchronized AppDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context, AppDataBase.class, "user_database").build();
        }
        return dataBase;
    }

    abstract UserDao getUserDao();
}
