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
        String url = "https://www.mg.senac.br/ambienteacademico/detalheCurso";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void mapeamento(View v){
        String url = "https://www.google.com/maps/place/Senac/@-21.684343,-45.2637301,20z/data=!4m5!3m4!1s0x94cadd299037c0c1:0x1b4dc77c7d066faa!8m2!3d-21.684343!4d-45.2635";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void ava(View v){
        String url = "https://ava.mg.senac.br/edu/";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void biblioteca(View v){
        String url = "mg.senac.br/faculdade/Paginas/biblioteca-nova.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void cursossenac(View v){
        String url = "https://www.mg.senac.br/faculdade/Paginas/default.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void cursosdisponiveis(View v){
        String url = "https://www.mg.senac.br/programasenacdegratuidade/vagas.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void aprendizagemcomercial(View v){
        String url = "https://www.mg.senac.br/Paginas/aprendizagem-comercial.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    public void redecarreiras(View v){
        String url = "https://www.mg.senac.br/Paginas/rededecarreiras.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
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