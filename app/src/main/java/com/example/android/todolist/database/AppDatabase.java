package com.example.android.todolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    // TAG for Logging or debugging purposes
    private static final String TAG = AppDatabase.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static final String DATABASE_NAME = "todolist";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME)
                        //.allowMainThreadQueries()
                        // Queries should be done in a separate thread to a void locking the UI, we will allow this ONLY TEMPORALLY to see that our DB is working
                        .build();
            }
        }
        Log.d(TAG, "Retrieving AppDatabase instance ...!");
        return sInstance;
    }

    public abstract TaskDao taskDao();
}
