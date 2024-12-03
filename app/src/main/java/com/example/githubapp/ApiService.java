package com.example.githubapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {

    // Search repositories
    @GET("search/repositories")
    Call<ApiResponse> searchRepositories(
            @Query("q") String query,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    // Get contributors for a repository
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
