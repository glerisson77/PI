package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.pi.models.DatabaseRA;
import com.example.pi.models.MessageDialog;

public class MainIconsActivity extends AppCompatActivity {
    DatabaseRA myDB;
    Boolean logged = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_icons);
        myDB = new DatabaseRA(this);
        String ra = getRaFromDB();
        if (ra.equals("empty")){
            logged = false;
        }else{
            logged = true;
        }
//        Toast.makeText(this, ra, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_icons, menu);
        return true;
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
//        Toast.makeText(MainIconsActivity.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
        abrirLink("https://pergamum.mg.senac.br/pergamum/biblioteca_s/php/login_usu.php");
    }
    public void cursossenac(View v){
        ///abre a tela info cursos jenifer
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
    public void openGames(View v){
        if (logged){
            Intent intent = new Intent(MainIconsActivity.this, GamesActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }

    public void about(View v){
        showUpDialogMessage("aqui vai ficar o texto sobre o app ", "Sobre o aplicativo");
    }

    public void showUpDialogMessage(String txt, String title){
        MessageDialog messageDialog = new MessageDialog(txt, title);
        messageDialog.show(getSupportFragmentManager(), "mensagem");
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

}