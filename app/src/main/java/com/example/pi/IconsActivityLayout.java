package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

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
        Intent intent = new Intent(IconsActivityLayout.this, frequencyActivity.class);
        startActivity(intent);
    }
    public void mapeamento(View v){
        Intent intent = new Intent(IconsActivityLayout.this, mapeamentoActivity.class);
        startActivity(intent);
    }
    public void ava(View v){
        Intent intent = new Intent(IconsActivityLayout.this, avaActivity.class);
        startActivity(intent);
    }
    public void biblioteca(View v){
        Intent intent = new Intent(IconsActivityLayout.this, biblioActivity.class);
        startActivity(intent);
    }
    public void cursossenac(View v){
        Intent intent = new Intent(IconsActivityLayout.this, cursosSenacActivity.class);
        startActivity(intent);
    }
    public void cursosdisponiveis(View v){
        Intent intent = new Intent(IconsActivityLayout.this, cursosdisponiveis.class);
        startActivity(intent);
    }
    public void aprendizagemcomercial(View v){
        Intent intent = new Intent(IconsActivityLayout.this, aprendizagemComercialActivity.class);
        startActivity(intent);
    }
    public void redecarreiras(View v){
        Intent intent = new Intent(IconsActivityLayout.this, redeCarreirasActivity.class);
        startActivity(intent);
    }
    public void projetoint(View v){
        Intent projint = new Intent(this, projetoIntegradorActivity.class);
        startActivity(projint);
    }
    public void creditos(View v){
        Intent cred = new Intent(this, CreditsActivity.class);
        startActivity(cred);
    }


}