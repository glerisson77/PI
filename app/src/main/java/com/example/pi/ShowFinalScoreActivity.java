package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class ShowFinalScoreActivity extends AppCompatActivity {

    TextView name, score, observation;
    ImageView pontuationBox;
    String passedName, passedScore;
    LottieAnimationView scoreLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_score);

        score = findViewById(R.id.finalscore);
        scoreLottie = findViewById(R.id.scorelottie);
        observation = findViewById(R.id.observationtv);
        pontuationBox = findViewById(R.id.pontuationbox);


        passedName = getIntent().getStringExtra("keyname");
        passedScore = getIntent().getStringExtra("keyscore");

        score.setText(passedScore);

        addLottieAnimation();
        setObservationText();

    }

    public void addLottieAnimation(){
        int intPassedScore = Integer.parseInt(passedScore);
        if (intPassedScore >= 60){
            scoreLottie.setAnimation(R.raw.businessmanwinnerwithtrophy);
        }else if (intPassedScore >= 30){
            scoreLottie.setAnimation(R.raw.businessmanwinnerwithtrophy);
        }else if (intPassedScore <= 29){
            scoreLottie.setAnimation(R.raw.businessmanwinnerwithtrophy);
        }
    }

    public void setObservationText(){
        int intPassedScore = Integer.parseInt(passedScore);
        if (intPassedScore >= 60){
            observation.setText("Pefeito!");
        }else if (intPassedScore >= 30){
            observation.setText("Estude mais!");
        }else if (intPassedScore <= 29){
            observation.setText("Que pena!!!");
        }
    }

    public void setBoxColor(){
        int intPassedScore = Integer.parseInt(passedScore);
        if (intPassedScore >= 60){
           pontuationBox.setImageResource(R.drawable.greenbox);
        }else if (intPassedScore >= 30){
            pontuationBox.setImageResource(R.drawable.greenbox);
        }else if (intPassedScore <= 29){
            pontuationBox.setImageResource(R.drawable.greenbox);
        }
    }
}