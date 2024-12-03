package com.example.githubapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RepositoryAdapter repositoryAdapter;
    private EditText searchBar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        repositoryAdapter = new RepositoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repositoryAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observing repository data
        mainViewModel.getRepositories().observe(this, repositories -> {
            if (repositories != null && !repositories.isEmpty()) {
                if (currentPage == 1) {
                    repositoryAdapter.setRepositories(repositories);
                } else {
                    repositoryAdapter.addRepositories(repositories);
                }
                progressBar.setVisibility(View.GONE);
            } else if (currentPage == 1) {
                Toast.makeText(this, "No repositories found", Toast.LENGTH_SHORT).show();
            }
        });

        // Observing error messages
        mainViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        searchBar.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                String query = searchBar.getText().toString().trim();
                if (!query.isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    currentPage = 1; // Reset to the first page
                    repositoryAdapter.clearData();
                    mainViewModel.fetchRepositories(query, currentPage);
                } else {
                    Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage++;
                    String query = searchBar.getText().toString().trim();
                    if (!query.isEmpty()) {
                        progressBar.setVisibility(View.VISIBLE);
                        mainViewModel.fetchRepositories(query, currentPage);
                    }
                }
            }
        });

        repositoryAdapter.setOnItemClickListener(repository -> {
            Intent intent = new Intent(this, RepoDetailsActivity.class);
            intent.putExtra("repository", repository);
            startActivity(intent);
        });
    }
}