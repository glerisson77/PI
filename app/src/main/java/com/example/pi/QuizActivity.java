package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pi.models.DatabaseRA;
import com.example.pi.models.QuestionsLog;
import com.example.pi.models.QuestionsRH;
import com.example.pi.models.StudentScore;
import com.google.firebase.database.FirebaseDatabase;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    ///declaracao das variaveis
    TextView question, actualuc, numberQuestions, userNameTV;
    Button answer1,answer2,answer3,answer4;
    LinearLayout linearLayout;
    ProgressBar progressBar;
    DatabaseRA myDB;
    String passedUserName = "", passedQuiz;

    int score = 0;
    int totalquestions;
    int currentQuestionIndex = 0;
    int unidadeCurricular = 1;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_rh);
        ///atribui as views as variaveis
        myDB = new DatabaseRA(this);
        passedQuiz = getIntent().getStringExtra("keyquiz");
        if (passedQuiz.equals("quizlog")){
            totalquestions = QuestionsLog.question.length;
        }else {
            totalquestions = QuestionsRH.question.length;
        }
        question = findViewById(R.id.perguntatv);
        answer1 = findViewById(R.id.resposta1);
        answer2 = findViewById(R.id.resposta2);
        answer3 = findViewById(R.id.resposta3);
        answer4 = findViewById(R.id.resposta4);
        actualuc = findViewById(R.id.ucatual);
        numberQuestions = findViewById(R.id.nperguntasdetperguntas);
        linearLayout = findViewById(R.id.layoutdasrespostas);
        userNameTV = findViewById(R.id.usernamequiz);
        passedUserName = getIntent().getStringExtra("keyusername");

        userNameTV.setText(passedUserName);

        ///seta o metodo de click nos botoes
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
    ///coloca o numero de perguntas do quiz
    numberQuestions.setText(currentQuestionIndex + "/" + totalquestions);
    actualuc.setText("UC " + unidadeCurricular);
        prog();
    ///carrega as perguntas
    loadNewQuestion();

    }

    private void prog() {
        progressBar = findViewById(R.id.progressbarrhquiz);
        progressBar.setProgress(currentQuestionIndex);
    }

    private void loadNewQuestion() {
        prog();
        numberQuestions.setText((currentQuestionIndex + 1) + "/" + totalquestions);

        if((currentQuestionIndex + 1) == totalquestions){
            finishQuiz();
            return;
        }
//        Toast.makeText(this, String.valueOf(score), Toast.LENGTH_SHORT).show();
        ///seta o texto das perguntas e respostas nos botoes

        if (passedQuiz.equals("quizlog")){
            question.setText(" '" + QuestionsLog.question[currentQuestionIndex] + "'");
            answer1.setText(QuestionsLog.choices[currentQuestionIndex][0]);
            answer2.setText(QuestionsLog.choices[currentQuestionIndex][1]);
            answer3.setText(QuestionsLog.choices[currentQuestionIndex][2]);
            answer4.setText(QuestionsLog.choices[currentQuestionIndex][3]);
        }else {

            question.setText(" '" + QuestionsRH.question[currentQuestionIndex] + "'");
            answer1.setText(QuestionsRH.choices[currentQuestionIndex][0]);
            answer2.setText(QuestionsRH.choices[currentQuestionIndex][1]);
            answer3.setText(QuestionsRH.choices[currentQuestionIndex][2]);
            answer4.setText(QuestionsRH.choices[currentQuestionIndex][3]);
        }

        answer1.setBackgroundColor(Color.rgb(255,230,153));
        answer2.setBackgroundColor(Color.rgb(255,230,153));
        answer3.setBackgroundColor(Color.rgb(255,230,153));
        answer4.setBackgroundColor(Color.rgb(255,230,153));

//        if (currentQuestionIndex >= 10){
//            unidadeCurricular = 2;
//        }else if (currentQuestionIndex >= 20){
//            unidadeCurricular = 3;
//        }else if (currentQuestionIndex >= 30) {
//            unidadeCurricular = 4;
//        }else if (currentQuestionIndex >= 40) {
//            unidadeCurricular = 5;
//        }else if (currentQuestionIndex >= 50) {
//            unidadeCurricular = 6;
//        }else if (currentQuestionIndex >= 60) {
//            unidadeCurricular = 7;
//        }else if (currentQuestionIndex >= 70) {
//            unidadeCurricular = 8;
//        }else if (currentQuestionIndex >= 80) {
//            unidadeCurricular = 9;
//        }else if (currentQuestionIndex >= 90) {
//            unidadeCurricular = 10;
//        }
//        actualuc.setText("UC " + unidadeCurricular);

    }

    private void finishQuiz() {
        ///mostra os pontos que a pessoa fez
        String getName = passedUserName;

        if (getName.equals(""))
            getName = "Anônimo";
        String passStatus = "";

        StudentScore studentScore = new StudentScore(getName, score, getRaFromDB());

        String id = "id" + System.currentTimeMillis();

        if (passedQuiz.equals("quizlog")){
            FirebaseDatabase.getInstance().getReference().child("rankingrhquizrh").child(id).setValue(studentScore);
        }else {
            FirebaseDatabase.getInstance().getReference().child("rankingrhquizlog").child(id).setValue(studentScore);
        }

        if(score > totalquestions*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        passScore(score);


//        new AlertDialog.Builder(this)
//                .setTitle(passStatus)
//                .setMessage("Score is " + score + "out of" + totalquestions)
//                .setPositiveButton("restart", ((dialogInterface, i) -> restartQuiz()))
//                .setCancelable(false)
//                .show();
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
        ///coloca o cor dos botoes de volta para o amarelo
        answer1.setBackgroundColor(Color.rgb(255,230,153));
        answer2.setBackgroundColor(Color.rgb(255,230,153));
        answer3.setBackgroundColor(Color.rgb(255,230,153));
        answer4.setBackgroundColor(Color.rgb(255,230,153));

        Button clickedButton = (Button) view;
        ///se selecionar a resposta certa aumenta o score

        selectedAnswer = clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.BLUE);

        if (passedQuiz.equals("quizlog")){
            if(selectedAnswer.equals(QuestionsLog.correctAnswers[currentQuestionIndex])){
                score++;
            }
        }else {
            if(selectedAnswer.equals(QuestionsRH.correctAnswers[currentQuestionIndex])){
                score++;
            }
        }
        currentQuestionIndex++;
        loadNewQuestion();
    }

    public String getRaFromDB(){
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0){
        }
        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append(res.getString(0));
//        }
        res.moveToNext();
        buffer.append(res.getString(0));

        String ra_text = buffer.toString();
        return ra_text;
    }

    public void passScore(int score){
        String getName = passedUserName;
        String scoreString = String.valueOf(score);
        if (getName.equals(""))
            getName = "Anônimo";
        Intent intent = new Intent(QuizActivity.this, ShowFinalScoreActivity.class);
        intent.putExtra("keyscore", scoreString);
        intent.putExtra("keyname", getName);
        restartQuiz();
        startActivity(intent);
    }

}