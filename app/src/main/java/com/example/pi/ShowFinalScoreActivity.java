package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowFinalScoreActivity extends AppCompatActivity {

    TextView name, score;
    String passedName, passedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_score);
        name = findViewById(R.id.namescore);
        score = findViewById(R.id.finalscore);

        passedName = getIntent().getStringExtra("keyname");
        passedScore = getIntent().getStringExtra("keyscore");

        name.setText(passedName);
        score.setText(passedScore);

    }
}