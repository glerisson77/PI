package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RhQuizInitialScreenActivity extends AppCompatActivity {

    String passedRa, passedUserName, passedQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rh_quiz_initial_screen);
        passedRa = getIntent().getStringExtra("keyra");
        passedUserName = getIntent().getStringExtra("keyusername");
        passedQuiz = getIntent().getStringExtra("keyquiz");

    }

    public void exitRhInitialScreen(View v){
        finish();
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, GamesActivity.class);
        startActivity(intent);
    }
    public void startQuizRh(View v) {
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, QuizRhActivity.class);
        intent.putExtra("keyra", passedRa);
        intent.putExtra("keyusername", passedUserName);
        intent.putExtra("keyquiz", passedQuiz);
        startActivity(intent);
    }

    public void openRankingScreen(View v){
        Intent intent = new Intent(RhQuizInitialScreenActivity.this, RecordsRHQuiz.class);
        startActivity(intent);
    }
}