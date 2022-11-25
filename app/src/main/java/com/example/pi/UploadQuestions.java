package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pi.models.Questions;
import com.example.pi.models.projectInformation;
import com.google.firebase.database.FirebaseDatabase;

public class UploadQuestions extends AppCompatActivity {

    EditText pergunta;
    EditText resposta1;
    EditText resposta2;
    EditText resposta3;
    EditText resposta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_questions);

        pergunta = findViewById(R.id.pergunta);
        resposta1 = findViewById(R.id.resposta1);
        resposta2 = findViewById(R.id.resposta2);
        resposta3 = findViewById(R.id.resposta3);
        resposta4 = findViewById(R.id.resposta4);
    }

    public void uploadQuestion(View v){

        String perguntaS = pergunta.getText().toString();
        String resposta1S = resposta1.getText().toString();
        String resposta2s = resposta2.getText().toString();
        String resposta3s = resposta3.getText().toString();
        String resposta4s = resposta4.getText().toString();
        Questions pergunta = new Questions(perguntaS, resposta1S, resposta2s, resposta3s, resposta4s);
        FirebaseDatabase.getInstance().getReference().child("quiz").child("id" + System.currentTimeMillis()).setValue(pergunta);
        ///storage the project id to exclude after #implement
    }
}