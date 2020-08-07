package com.example.tastycookiesclicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton increaseScoreButton;
    private int score;
    private TextView scoreTextView;
    private boolean isUserGetFirst100;
    private String scoreAsString;
    private static  final String resetText = "Упс! Попробуй снова. Может быть, в следующий раз не сброшу твой счет...";
    private static  final String praiseText = "Горжусь тобой! Будь таким же целеустремленным и за пределами этой игры!";
    private  Animation animAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        scoreTextView = findViewById(R.id.countTextView);
        increaseScoreButton = findViewById(R.id.increaseScoreButton);
        increaseScoreButton.setOnClickListener(onClickListener);
        isUserGetFirst100 = false;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            increaseScore(v);
            v.startAnimation(animAlpha);

        }
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt("score");
        scoreAsString = String.valueOf(score);
        scoreTextView.setText(scoreAsString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_show_score:
                Intent intent = new Intent(MainActivity.this, ShowScoreActivity.class);
                intent.putExtra("score", scoreAsString);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void increaseScore(View v) {
        if (v.getId() == R.id.increaseScoreButton) {
            score++;
            resetFirst100Points();
            praiseUserIn100Points();
            scoreAsString = String.valueOf(score);
            scoreTextView.setText(scoreAsString);

        }
    }

    private void praiseUserIn100Points() {
        if (score == 100) {
            Toast praiseToast = Toast.makeText(MainActivity.this, praiseText, Toast.LENGTH_LONG);
            praiseToast.show();
        }
    }

    private void resetFirst100Points() {
        if (!isUserGetFirst100) {
            if (score == 100) {
                isUserGetFirst100 = true;
                score = 0;
                Toast resetToast = Toast.makeText(MainActivity.this, resetText, Toast.LENGTH_LONG);
                resetToast.show();
            }
        }
    }
}