package com.example.katalogmovie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.katalogmovie.R;
import com.example.katalogmovie.model.Result;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra";
    String title,overview,image,release;
    ImageView imgDetail;
    TextView tvTitle, tvDetail,tvRelease;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvJudulDeskripsi);
        tvDetail = findViewById(R.id.tvIsiDeskripsi);
        imgDetail = findViewById(R.id.imgMovieDeskripsi);
        tvRelease = findViewById(R.id.tvIsiReleaseDate);
        Button buttonLang = (Button) findViewById(R.id.buttonLang);
        buttonLang.setOnClickListener(v -> {
            Intent intentLang =  new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intentLang);
        });
        Button buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, aboutActivity.class);
            startActivity(intent);
        });
        result = getIntent().getParcelableExtra(EXTRA_MOVIE);

        title = result.getOriginalTitle();
        overview = result.getOverview();
        release = result.getReleaseDate();
        image = result.getPosterPath();

        tvTitle.setText(title);
        tvDetail.setText(overview);
        tvRelease.setText(release);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w185" + image)
                .into(imgDetail);


        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}