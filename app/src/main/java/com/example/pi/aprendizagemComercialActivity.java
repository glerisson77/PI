package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class aprendizagemComercialActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendizagem_comercial);
        webView = findViewById(R.id.webview);
        String url = "https://www.mg.senac.br/Paginas/aprendizagem-comercial.aspx";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}