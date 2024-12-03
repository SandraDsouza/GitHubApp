package com.example.githubapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Repository>> repositoriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final ApiService apiService;

    public MainViewModel(Application application) {
        super(application);
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<Repository>> getRepositories() {
        return repositoriesLiveData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchRepositories(String query, int page) {
        apiService.searchRepositories(query, page, 10).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Repository> newRepos = response.body().getItems();
                    if (repositoriesLiveData.getValue() == null || page == 1) {
                        repositoriesLiveData.setValue(newRepos);
                    } else {
                        List<Repository> currentRepos = new ArrayList<>(repositoriesLiveData.getValue());
                        currentRepos.addAll(newRepos);
                        repositoriesLiveData.setValue(currentRepos);
                    }
                } else {
                    errorMessage.setValue("Failed to fetch repositories: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorMessage.setValue("Network error: " + t.getMessage());
            }
        });
    }
}