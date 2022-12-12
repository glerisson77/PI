package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;

public class InitialSplashScreenActivity extends AppCompatActivity {

    private final Timer timer = new Timer();
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_splash_screen);

        animate();
        animateTwo();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoInconsActivity();
                    }
                });
            }
        };
        timer.schedule(timerTask, 3000);
    }

    private void gotoInconsActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void animate(){
        YoYo.with(Techniques.FadeOut)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.senaclogoup));

    }
    public void animateTwo(){
        YoYo.with(Techniques.FadeInDown)
                .duration(5000)
                .repeat(0)
                .playOn(findViewById(R.id.splashapptittle));
        YoYo.with(Techniques.FadeInDown)
                .duration(5000)
                .repeat(0)
                .playOn(findViewById(R.id.logoimage));


    }
}