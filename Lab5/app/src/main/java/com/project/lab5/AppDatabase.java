package com.project.lab5;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Worker.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkerDao workerDao;
}
