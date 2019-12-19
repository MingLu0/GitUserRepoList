package com.example.gituserrepolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

    private EditText txt_input_name;

    private Retrofit mRetrofit;
    private GitHubService mService;
    private List<SimpleRepo>mRepos;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private final String TAG = MainActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_fetch = findViewById(R.id.btn_fetch);
        txt_input_name = findViewById(R.id.editText_name);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RepoAdapter(mRepos);
        recyclerView.setAdapter(mAdapter);

        btn_fetch.setOnClickListener(v -> fetchRepos(txt_input_name.getText().toString()));

        if(mRetrofit == null){
            String BASE_URL = "https://api.github.com/users/";
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
    private void fetchRepos( @NonNull String user){

        if(!user.isEmpty()){
            Call<List<SimpleRepo>> call = mService.listRepos(user);

            call.enqueue(new Callback<List<SimpleRepo>>() {

                @Override
                public void onResponse(@NonNull Call<List<SimpleRepo>> call, @NonNull Response<List<SimpleRepo>> response) {

                    mRepos = response.body();
                    if(mRepos!=null){
                        for(int i=0;i<mRepos.size();i++){
                            Log.d(TAG,mRepos.get(i).getName());
                        }

                        mAdapter = new RepoAdapter(mRepos);
                        recyclerView.setAdapter(mAdapter);

                    } else {

                        Toast toast = Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();

                    }

                }

                @Override
                public void onFailure(@NonNull Call<List<SimpleRepo>> call, @NonNull Throwable t) {


                }
            });
        } else {

            Toast toast = Toast.makeText(MainActivity.this, "User Name Can Not Be Empty", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

        }

    }


}
