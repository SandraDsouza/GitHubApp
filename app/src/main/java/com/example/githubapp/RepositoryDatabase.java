package com.example.githubapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RepositoryEntity.class}, version = 1,exportSchema = false)
public abstract class RepositoryDatabase extends RoomDatabase {

    private static RepositoryDatabase instance;

    public abstract RepositoryDao repositoryDao();

    public static synchronized RepositoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            RepositoryDatabase.class, "repository_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
