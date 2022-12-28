package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.models.DatabaseRA;
import com.example.pi.models.MessageDialog;
import com.example.pi.models.UserInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainIconsActivity extends AppCompatActivity {
    DatabaseRA myDB;
    DatabaseReference databaseReference;
    Boolean logged = true;
    ImageView credits, ava, aprendizagem, biblio, cursosDisponiveis, cursosSenac, games, mapeamento, pi, frequency, redeCarreiras;
    String passedRa = "empty", passedUserName = "None", passedUserID = "None";
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_icons);

        credits = findViewById(R.id.iconcreditos);
        ava = findViewById(R.id.iconava);
        frequency = findViewById(R.id.iconfrequencia);
        mapeamento = findViewById(R.id.iconmapeamento);
        games = findViewById(R.id.icongame);
        aprendizagem = findViewById(R.id.iconaprendizagemcoemrcial);
        biblio = findViewById(R.id.iconbiblioteca);
        cursosDisponiveis = findViewById(R.id.iconcursosdisponiveis);
        cursosSenac  = findViewById(R.id.iconcursosenac);
        pi  = findViewById(R.id.iconpi);
        redeCarreiras  = findViewById(R.id.iconredecarreiras);
        passedRa = getIntent().getStringExtra("keyra");
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userName = findViewById(R.id.username);

        myDB = new DatabaseRA(this);
        String ra = getRaFromDB();
        if (ra.equals("empty")){
            logged = false;
        }else{
            logged = true;
        }
//        Toast.makeText(this, ra, Toast.LENGTH_SHORT).show();
        getUserFromFB();
    }
    @Override
    protected void onResume() {
        super.onResume();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        getUserFromFB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_icons, menu);
        return true;
    }

    public void frequencia(View v){
        frequency.setImageResource(R.drawable.frequenciapressed);
        abrirLink("https://www.mg.senac.br/ambienteacademico/detalheCurso");
    }
    public void mapeamento(View v){
//        mapeamento.setImageResource(R.drawable.mapeamento);
        Toast.makeText(MainIconsActivity.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
    }
    public void ava(View v){
        ava.setImageResource(R.drawable.ambientevirtualpressed);
        abrirLink("https://ava.mg.senac.br/edu/");
    }
    public void biblioteca(View v){
        biblio.setImageResource(R.drawable.bibliotecapressed);
        abrirLink("https://pergamum.mg.senac.br/pergamum/biblioteca_s/php/login_usu.php");
    }
    public void cursossenac(View v){
//        cursosSenac.setImageResource(R.drawable.cursossenacpressed);
        ///abre a tela info cursos jenifer
        Toast.makeText(MainIconsActivity.this, "Recurso ainda não implementado", Toast.LENGTH_SHORT).show();
    }
    public void cursosdisponiveis(View v){
        cursosDisponiveis.setImageResource(R.drawable.cursosdisponiveispressed);
        abrirLink("https://www.mg.senac.br/programasenacdegratuidade/vagas.aspx");
    }
    public void aprendizagemcomercial(View v){
        aprendizagem.setImageResource(R.drawable.aprendizagemcomercailpressed);
        abrirLink("https://www.mg.senac.br/Paginas/aprendizagem-comercial.aspx");
    }
    public void redecarreiras(View v){
        redeCarreiras.setImageResource(R.drawable.redecarreiraspressed);
        abrirLink("https://www.mg.senac.br/Paginas/rededecarreiras.aspx");
    }
    public void projetoint(View v){
        if (logged){
            pi.setImageResource(R.drawable.projetointegradorpressed);
            Intent projint = new Intent(this, PiPostsActivity.class);
            projint.putExtra("keyusername", passedUserName);
            projint.putExtra("keyra", passedRa);
            startActivity(projint);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }
    public void creditos(View v){
        credits.setImageResource(R.drawable.creditospressed);
        Intent cred = new Intent(this, CreditsActivity.class);
        startActivity(cred);
//        credits.setImageResource(R.drawable.creditos);

    }

    public void abrirLink(String link){
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    public void openGames(View v){
        if (logged){
            games.setImageResource(R.drawable.jogospressed);
            Intent intent = new Intent(MainIconsActivity.this, GamesActivity.class);
            intent.putExtra("keyra", passedRa);
            intent.putExtra("keyusername", passedUserName);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }

    public void openStudentProfile(View v){
        if (logged){
            games.setImageResource(R.drawable.jogospressed);
            Intent intent = new Intent(MainIconsActivity.this, UserProfileActivity.class);
            intent.putExtra("keyra", passedRa);
            intent.putExtra("keyusername", passedUserName);
            intent.putExtra("keyuserid", passedUserID);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Você deve estar logado para usar esta ferramenta", Toast.LENGTH_SHORT).show();
        }
    }

    public void openUsersPost(View v){
        if (logged){
            Intent intent = new Intent(MainIconsActivity.this, UsersPostsActivity.class);
            intent.putExtra("keyra", passedRa);
            intent.putExtra("keyusername", passedUserName);
            intent.putExtra("keyuserid", passedUserID);
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
    @Override
    protected void onPostResume() {
        super.onPostResume();
        credits.setImageResource(R.drawable.creditos);
        ava.setImageResource(R.drawable.ambientevirtual);
        aprendizagem.setImageResource(R.drawable.aprendizagemcomercial);
        biblio.setImageResource(R.drawable.biblioteca);
        cursosDisponiveis.setImageResource(R.drawable.cursosdisponiveis);
        cursosSenac.setImageResource(R.drawable.cursossenac);
        games.setImageResource(R.drawable.jogos);
        mapeamento.setImageResource(R.drawable.mapeamento);
        pi.setImageResource(R.drawable.projetointegrador);
        frequency.setImageResource(R.drawable.frequencia);
        redeCarreiras.setImageResource(R.drawable.rededecarreiras);
    }

    public void getUserFromFB(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);
                    if (userInformation.getUserRa().equals(getRaFromDB())){
                        userName.setText(userInformation.getUserName());
                        passedUserName = userInformation.getUserName();
                        passedUserID = userInformation.getUserId();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}