package com.example.android3.assignment.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import com.example.android3.assignment.R;

public class LoadingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    CountDownTimer count;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setProgress(0);
        progressBar.setMax(40);
        count = new CountDownTimer(4000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                current = progressBar.getProgress();
                progressBar.setProgress(current + 6);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        };
        count.start();
    }
}
