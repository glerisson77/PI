package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogQuizInitialScreenActivity extends AppCompatActivity {

    String passedquiz, passedRa, passedUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_quiz_initial_screen);

        passedquiz = getIntent().getStringExtra("keyquiz");
        passedRa = getIntent().getStringExtra("keyra");
        passedUserName = getIntent().getStringExtra("keyusername");
    }
    public void startQuizRh(View v) {
        Intent intent = new Intent(LogQuizInitialScreenActivity.this, QuizRhActivity.class);
        intent.putExtra("keyra", passedRa);
        intent.putExtra("keyusername", passedUserName);
        intent.putExtra("keyquiz", passedquiz);
        startActivity(intent);
    }

    public void startQuizLog(View v) {
        Intent intent = new Intent(LogQuizInitialScreenActivity.this, QuizRhActivity.class);
        intent.putExtra("keyra", passedRa);
        intent.putExtra("keyusername", passedUserName);
        intent.putExtra("keyquiz", passedquiz);
        startActivity(intent);
    }

}