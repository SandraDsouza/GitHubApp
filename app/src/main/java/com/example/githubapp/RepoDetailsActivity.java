package com.example.githubapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RepoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        TextView repoName = findViewById(R.id.repoName);
        TextView description = findViewById(R.id.repoDescription);
        TextView stars = findViewById(R.id.repoStars);
        TextView forks = findViewById(R.id.repoForks);
        ImageView avatar = findViewById(R.id.repoAvatar);

        Repository repository = (Repository) getIntent().getSerializableExtra("repository");

        if (repository != null) {
            repoName.setText(repository.getName());
            description.setText(repository.getDescription() != null ? repository.getDescription() : "No description available");
            stars.setText("Stars: " + repository.getStars());
            forks.setText("Forks: " + repository.getForks());

            String avatarUrl = repository.getAvatarUrl(); // Safely get the avatar URL
            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                Glide.with(this)
                        .load(avatarUrl)
                        .placeholder(R.drawable.ic_launcher_background) // Optional: Add a placeholder image
                        .error(R.drawable.ic_launcher_background)       // Optional: Add an error image
                        .into(avatar);
            } else {
                // Optional: Set a default image if no avatar is available
                avatar.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }
}
