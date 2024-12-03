package com.example.githubapp;

import java.util.ArrayList;
import java.util.List;

public class RepositoryEntityMapper {

    // Map from Repository to RepositoryEntity
    public static List<RepositoryEntity> map(List<Repository> repositories) {
        List<RepositoryEntity> entities = new ArrayList<>();
        for (Repository repo : repositories) {
            RepositoryEntity entity = new RepositoryEntity();
            entity.setName(repo.getName());
            entity.setDescription(repo.getDescription());
            entity.setHtmlUrl(repo.getProjectLink());

            // Null check for owner
            if (repo.getOwnerName() != null) {  // Changed to getOwnerName() for checking
                entity.setOwnerAvatarUrl(repo.getAvatarUrl());
            } else {
                entity.setOwnerAvatarUrl(""); // If no owner, set empty string
            }
            entities.add(entity);
        }
        return entities;
    }

    // Map from RepositoryEntity to Repository
    public static List<Repository> mapBack(List<RepositoryEntity> entities) {
        List<Repository> repositories = new ArrayList<>();
        for (RepositoryEntity entity : entities) {
            Repository repo = new Repository();

            // Set repository fields
            repo.setName(entity.getName());
            repo.setDescription(entity.getDescription());
            repo.setProjectLink(entity.getHtmlUrl());

            // Create Owner object only if avatarUrl exists
            if (entity.getOwnerAvatarUrl() != null && !entity.getOwnerAvatarUrl().isEmpty()) {
                // Create the Owner object
                Repository.OwnerInfo owner = new Repository.OwnerInfo();  // Corrected to OwnerInfo
                owner.setAvatarUrl(entity.getOwnerAvatarUrl());  // Set the avatarUrl
                repo.setOwner(owner);  // Set the owner object
            } else {
                repo.setOwner(null);  // No owner found, set owner as null
            }

            repositories.add(repo);
        }
        return repositories;
    }
}
