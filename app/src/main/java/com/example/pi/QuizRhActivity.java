package com.example.pi;

import static com.example.pi.R.drawable.whitecurledbackground;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.models.Questions;
import com.example.pi.models.StudentScore;
import com.google.firebase.database.FirebaseDatabase;

public class QuizRhActivity extends AppCompatActivity implements View.OnClickListener{
    ///declaracao das variaveis
    TextView totalquestionstv, question, actualuc;
    Button answer1,answer2,answer3,answer4, confirm;
    LinearLayout linearLayout;
    EditText nameForRecord;

    int score = 0;
    int totalquestions = Questions.question.length;
    int currentQuestionIndex = 0;
    int unidadeCurricular = 1;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_rh);
        ///atribui as views as variaveis
        totalquestionstv = findViewById(R.id.totalperguntas);
        question = findViewById(R.id.perguntatv);
        answer1 = findViewById(R.id.resposta1);
        answer2 = findViewById(R.id.resposta2);
        answer3 = findViewById(R.id.resposta3);
        answer4 = findViewById(R.id.resposta4);
        confirm = findViewById(R.id.confirmarbt);
        actualuc = findViewById(R.id.ucatual);
        linearLayout = findViewById(R.id.layoutdasrespostas);
        nameForRecord = findViewById(R.id.nameperson);
        ///seta o metodo de click nos botoes
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        confirm.setOnClickListener(this);
    ///coloca o numero de perguntas do quiz
    totalquestionstv.setText("Perguntas totais : " + totalquestions);
    actualuc.setText("UC " + unidadeCurricular);
    ///carrega as perguntas
    loadNewQuestion();
    changeLinearLayoutColor();

    }

    private void changeLinearLayoutColor() {
//        linearLayout.setBackgroundColor(Color.GREEN);
        if (unidadeCurricular == 1){
//            linearLayout.setBackgroundColor(Color.GREEN);
            linearLayout.setBackground(getDrawable(R.drawable.orangebackground));
        }else if (unidadeCurricular == 2){
            linearLayout.setBackgroundColor(Color.RED);
        }else if(unidadeCurricular == 3){
            linearLayout.setBackgroundColor(Color.BLUE);
        }else if(unidadeCurricular == 4){
            linearLayout.setBackgroundColor(Color.YELLOW);

        }else if(unidadeCurricular == 5){
            linearLayout.setBackgroundColor(Color.GRAY);
        }else if(unidadeCurricular == 6){
            linearLayout.setBackgroundColor(Color.CYAN);
        }else if(unidadeCurricular == 7){
            linearLayout.setBackgroundColor(Color.DKGRAY);
        }else if(unidadeCurricular == 8){
            linearLayout.setBackgroundColor(Color.LTGRAY);
        }else if(unidadeCurricular == 9){
            linearLayout.setBackgroundColor(-6434);
        }else if(unidadeCurricular == 10){
            linearLayout.setBackgroundColor(-78566);
        }
    }

    private void loadNewQuestion() {

        if(currentQuestionIndex == totalquestions){
            finishQuiz();
            return;
        }
        ///seta o texto das perguntas e respostas nos botoes
        question.setText("Pergunta " + (currentQuestionIndex  + 1) + " '" + Questions.question[currentQuestionIndex] + "'");
        answer1.setText(Questions.choices[currentQuestionIndex][0]);
        answer2.setText(Questions.choices[currentQuestionIndex][1]);
        answer3.setText(Questions.choices[currentQuestionIndex][2]);
        answer4.setText(Questions.choices[currentQuestionIndex][3]);

        if (currentQuestionIndex >= 10){
            unidadeCurricular = 2;
        }else if (currentQuestionIndex >= 20){
            unidadeCurricular = 3;
        }else if (currentQuestionIndex >= 30) {
            unidadeCurricular = 4;
        }else if (currentQuestionIndex >= 40) {
            unidadeCurricular = 5;
        }else if (currentQuestionIndex >= 50) {
            unidadeCurricular = 6;
        }else if (currentQuestionIndex >= 60) {
            unidadeCurricular = 7;
        }else if (currentQuestionIndex >= 70) {
            unidadeCurricular = 8;
        }else if (currentQuestionIndex >= 80) {
            unidadeCurricular = 9;
        }else if (currentQuestionIndex >= 90) {
            unidadeCurricular = 10;
        }
        actualuc.setText("UC " + unidadeCurricular);
        changeLinearLayoutColor();

    }


    private void finishQuiz() {
        ///mostra os pontos que a pessoa fez
        String getName = nameForRecord.getText().toString();

        if (getName.equals(""))
            getName = "Anônimo";
        String passStatus = "";

        StudentScore studentScore = new StudentScore(getName, String.valueOf(score));

        String id = "id" + System.currentTimeMillis();

        FirebaseDatabase.getInstance().getReference().child("rankingrhquiz").child(id).setValue(studentScore);
        if(score > totalquestions*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + "out of" + totalquestions)
                .setPositiveButton("restart", ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        ///da restart no quiz
        score = 0;
        unidadeCurricular = 1;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    public void sair(View v){
        finish();
    }

    @Override
    public void onClick(View view) {
        ///coloca o cor dos botoes de volta para o branco
        answer1.setBackgroundColor(Color.WHITE);
        answer2.setBackgroundColor(Color.WHITE);
        answer3.setBackgroundColor(Color.WHITE);
        answer4.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        ///se selecionar a resposta certa aumenta o score
        if (clickedButton.getId() == R.id.confirmarbt){
            if(selectedAnswer.equals(Questions.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            changeLinearLayoutColor();
            loadNewQuestion();

        }else{
            //if choice button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
    public void openRankingScreen(View v){
        Intent intent = new Intent(QuizRhActivity.this, RecordsRHQuiz.class);
        startActivity(intent);
    }
}