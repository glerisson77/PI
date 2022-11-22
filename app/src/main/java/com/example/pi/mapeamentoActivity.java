package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class mapeamentoActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapeamento);
        webView.findViewById(R.id.webview);
        String url = "https://www.google.com/maps/place/Senac/@-21.684343,-45.2637301,20z/data=!4m5!3m4!1s0x94cadd299037c0c1:0x1b4dc77c7d066faa!8m2!3d-21.684343!4d-45.2635";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}