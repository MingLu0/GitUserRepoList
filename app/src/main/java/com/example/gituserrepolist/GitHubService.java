package com.example.gituserrepolist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 */
public interface GitHubService {

    /**
     *
     * @return
     */
    @GET("/repos")
    Call<List<SimpleRepo>>getRepos();
}
