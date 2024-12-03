package com.example.githubapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repository implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("forks_count")
    private int forks;

    @SerializedName("owner")
    private OwnerInfo owner;

    @SerializedName("html_url")
    private String projectLink; // Added projectLink field

    // Inner class representing Owner Info
    public static class OwnerInfo {
        @SerializedName("avatar_url")
        private String avatarUrl;

        @SerializedName("login")
        private String ownerName;

        // Getters and Setters for OwnerInfo
        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }
    }

    // Getters and Setters for Repository
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    // Getters and Setters for the new fields
    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    // Getters for Owner Info
    public String getAvatarUrl() {
        return owner != null ? owner.getAvatarUrl() : null;
    }

    public String getOwnerName() {
        return owner != null ? owner.getOwnerName() : null;
    }

    public void setOwner(OwnerInfo owner) {
        this.owner = owner;
    }

    // Handle the case when owner is null and avoid NullPointerException
    public void setOwnerAvatarUrl(String avatarUrl) {
        if (owner != null) {
            owner.setAvatarUrl(avatarUrl);
        }
    }
}
