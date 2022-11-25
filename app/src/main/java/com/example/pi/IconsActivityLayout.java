package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IconsActivityLayout extends AppCompatActivity {
    Button btOpenUrl;
    EditText editText;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons_layout);
    }
    public void frequencia(View v){
        abrirLink("https://www.mg.senac.br/ambienteacademico/detalheCurso");
    }
    public void mapeamento(View v){
        Toast.makeText(IconsActivityLayout.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(IconsActivityLayout.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
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
        Intent projint = new Intent(this, projetoIntegradorActivity.class);
        startActivity(projint);
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
        Intent intent = new Intent(IconsActivityLayout.this, quizActivity.class);
        startActivity(intent);
    }

}