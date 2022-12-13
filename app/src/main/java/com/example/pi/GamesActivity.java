package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pi.models.MessageDialog;

public class GamesActivity extends AppCompatActivity {

    String passedRa, passedUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        passedRa = getIntent().getStringExtra("keyra");
        passedUserName = getIntent().getStringExtra("keyusername");
    }

    public void OpenQuizRH(View v) {
        Intent intent = new Intent(GamesActivity.this, RhQuizInitialScreenActivity.class);
        intent.putExtra("keyra", passedRa);
        intent.putExtra("keyusername", passedUserName);
        intent.putExtra("keyquiz", "quizrh");
        startActivity(intent);
    }

    public void OpenQuizLog(View v) {
        Intent intent = new Intent(GamesActivity.this, LogQuizInitialScreenActivity.class);
        intent.putExtra("keyra", passedRa);
        intent.putExtra("keyusername", passedUserName);
        intent.putExtra("keyquiz", "quizlog");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_icons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sobre) {
            showUpDialogMessage("Aqui você pode encontrar jogos relacionados aos cursos do senac, caso esteja disponível", "informações sobre a aba jogos");

        }
        return super.onOptionsItemSelected(item);
    }

    public void showUpDialogMessage(String txt, String title) {
        MessageDialog messageDialog = new MessageDialog(txt, title);
        messageDialog.show(getSupportFragmentManager(), "mensagem");
    }
}