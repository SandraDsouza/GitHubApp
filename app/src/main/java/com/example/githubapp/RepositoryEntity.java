package com.example.githubapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "repositories")
public class RepositoryEntity {

    @PrimaryKey(autoGenerate = true)
    private long id; // Changed from int to long for consistency with Room
    private String name;
    private String description;
    private String ownerAvatarUrl;
    private String htmlUrl;

    // Add necessary getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
