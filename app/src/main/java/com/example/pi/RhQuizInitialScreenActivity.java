package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RhQuizInitialScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rh_quiz_initial_screen);
    }

    public void exitRhInitialScreen(View v){
        finish();
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, GamesActivity.class);
        startActivity(intent);
    }
    public void startQuizRh(View v) {
        finish();
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, QuizRhActivity.class);
        startActivity(intent);
    }

    public void openRankingScreen(View v){
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, RecordsRHQuiz.class);
        startActivity(intent);
    }
}