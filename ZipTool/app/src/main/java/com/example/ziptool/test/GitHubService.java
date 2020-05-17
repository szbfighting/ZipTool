package com.example.ziptool.test;

import androidx.lifecycle.ReportFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("user/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
