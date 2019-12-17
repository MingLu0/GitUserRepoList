package com.example.gituserrepolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_fetch;
    private EditText txt_input_name;

    private Retrofit mRetrofit;
    private String BASE_URL = "https://api.github.com/users/";
    private GitHubService mService;
    private List<SimpleRepo>mRepos;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private final String TAG = MainActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_fetch = findViewById(R.id.btn_fetch);
        txt_input_name = findViewById(R.id.editText_name);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RepoAdapter(mRepos);
        recyclerView.setAdapter(mAdapter);

        btn_fetch.setOnClickListener(v -> fetchRepos(txt_input_name.getText().toString()));

        if(mRetrofit == null){
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }

        mService = mRetrofit.create(GitHubService.class);

    }

    /**
     *  This method handles the button click event by sending asynchronous request to the server
     *  with a callback when a response comes back which either onResponse or on Failure and handles
     *  those two situations respectively.
     *
     * @param user the name of the git repo user provided by user input.
     */
    private void fetchRepos(@Nullable @NonNull String user){

        Call<List<SimpleRepo>> call = mService.listRepos(user);

        call.enqueue(new Callback<List<SimpleRepo>>() {

            @Override
            public void onResponse(Call<List<SimpleRepo>> call, Response<List<SimpleRepo>> response) {

                mRepos = response.body();
                if(mRepos!=null){
                    for(int i=0;i<mRepos.size();i++){
                        Log.d(TAG,mRepos.get(i).getName());
                    }
                }

                mAdapter = new RepoAdapter(mRepos);
                recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<SimpleRepo>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
