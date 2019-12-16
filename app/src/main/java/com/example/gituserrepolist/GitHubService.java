package com.example.gituserrepolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This is an interface class to be implemented by
 * the Retrofit class in which the endpoints are being
 * defined.
 */
public interface GitHubService {

    /**
     * This method defines a GET HTTP request endpoint with a String type parameter being encoded
     * in the annotation.
     * @param name the username of the repository provided by the user input which will dynamically
     *             update the replacement block in the annotation.
     * @return a Call generic which takes the type of list of SimpleRepo objects returned by the http
     *         method.
     */
    @GET("{userName}/repos")
    Call<List<SimpleRepo>>listRepos(@NonNull @Nullable @Path("userName") String name);
}
