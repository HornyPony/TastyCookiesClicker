package com.example.tastycookiesclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity {
private TextView scoreTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        scoreTextView = findViewById(R.id.scoreTextView);
        Bundle arguments = getIntent().getExtras();
        String score = arguments.get("score").toString();
        scoreTextView.setText(score);
    }
}