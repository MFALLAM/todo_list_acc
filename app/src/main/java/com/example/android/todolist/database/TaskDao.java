package com.example.android.todolist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    // get data
    @Query("SELECT * FROM task ORDER BY priority")
    List<TaskEntry> loadAllTasks(TaskEntry taskEntry);

    // insert data
    @Insert
    void insertTask(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);
    // delete data

    @Delete
    void deleteTask(TaskEntry taskEntry);

}
