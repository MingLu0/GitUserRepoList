package com.example.gituserrepolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_fetch;
    private EditText txt_input_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_fetch = findViewById(R.id.btn_fetch);
        txt_input_name = findViewById(R.id.editText_name);

        btn_fetch.setOnClickListener(v -> fetchRepos(txt_input_name.getText().toString()));

    }

    /**
     *
     * @param user
     */
    private void fetchRepos(@Nullable @NonNull String user){
        Toast.makeText(this,user,Toast.LENGTH_SHORT).show();
    }


}
