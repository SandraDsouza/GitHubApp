package com.example.githubapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;
import java.util.List;

@Dao
public interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepositories(List<RepositoryEntity> repositories);

    @Query("SELECT * FROM repositories")
    List<RepositoryEntity> getAllRepositories();
}
