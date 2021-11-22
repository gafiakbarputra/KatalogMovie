package com.example.katalogmovie.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.katalogmovie.R;
import com.example.katalogmovie.adapter.RecyclerMoviesAdapter;
import com.example.katalogmovie.model.Response;
import com.example.katalogmovie.model.Result;
import com.example.katalogmovie.rest.ApiClient;
import com.example.katalogmovie.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private RecyclerMoviesAdapter adapter;
    String API_KEY = "1805007963a4760044640bde94121491";
    String LANGUAGE = "en-US";
    String CATEGORY = "popular";
    int PAGE = 1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        panggilRetrofit();
    }

    private void panggilRetrofit() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getMovie(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> mList = response.body().getResults();
                adapter = new RecyclerMoviesAdapter(MainActivity.this, mList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
    }

}