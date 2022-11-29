package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pi.models.DabaseRA;
import com.example.pi.models.DataBaseHelper;

public class MainIconsActivity extends AppCompatActivity {
    DabaseRA myDB;
    Boolean logged = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_icons);
        myDB = new DabaseRA(this);
//        String ra = getRaFromDB();
//        if (ra.equals("empty")){
//            logged = false;
//        }else{
//            logged = true;
//        }
//        Toast.makeText(this, ra, Toast.LENGTH_SHORT).show();
    }
    public void frequencia(View v){
        abrirLink("https://www.mg.senac.br/ambienteacademico/detalheCurso");
    }
    public void mapeamento(View v){
        Toast.makeText(MainIconsActivity.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
    }
    public void ava(View v){
        abrirLink("https://ava.mg.senac.br/edu/");
    }
    public void biblioteca(View v){
        ///corrigir link que está crashando o app
        abrirLink("mg.senac.br/faculdade/Paginas/biblioteca-nova.aspx");
    }
    public void cursossenac(View v){
        ///abre a tela info cursos
        Toast.makeText(MainIconsActivity.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
    }
    public void cursosdisponiveis(View v){
        abrirLink("https://www.mg.senac.br/programasenacdegratuidade/vagas.aspx");
    }
    public void aprendizagemcomercial(View v){
        abrirLink("https://www.mg.senac.br/Paginas/aprendizagem-comercial.aspx");
    }
    public void redecarreiras(View v){
        abrirLink("https://www.mg.senac.br/Paginas/rededecarreiras.aspx");
    }
    public void projetoint(View v){
        if (logged){
            Intent projint = new Intent(this, PiPostsActivity.class);
            startActivity(projint);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }
    public void creditos(View v){
        Intent cred = new Intent(this, CreditsActivity.class);
        startActivity(cred);
    }

    public void abrirLink(String link){
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    public void postarQuiz(View v){
        if (logged){
            Intent intent = new Intent(MainIconsActivity.this, QuizRhActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }

//    public String getRaFromDB(){
//        Cursor res = myDB.getAllData();
//        if (res.getCount() == 0){
//        }
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append(res.getString(0));
//        }
////        res.moveToNext();
////        buffer.append(res.getString(0));
//
//        String ra_text = buffer.toString();
//        return ra_text;
//    }

}