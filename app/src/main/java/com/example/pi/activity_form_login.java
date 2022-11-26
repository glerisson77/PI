package com.example.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class activity_form_login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        email = findViewById(R.id.edit_ra);
        password = findViewById(R.id.edit_senha);
        login = findViewById(R.id.bt_entrar);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString() + "@senacminas.edu.br";
                String ra = "";
                Path ratxt = Paths.get("C:/Users/gleri/AndroidStudioProjects/PI/app/src/main/assets/ra.txt");
                try {
                    Toast.makeText(activity_form_login.this, Files.readAllLines(ratxt).toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String text_password = password.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(text_password)){
                    Toast.makeText(activity_form_login.this, "Um dos campos está vazio", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(txt_email, text_password);

                }
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(activity_form_login.this, "Login feito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity_form_login.this, projetoIntegradorActivity.class));
                finish();
            }
        });
    }

    public void registerScreen(View v){
        Intent intent = new Intent(activity_form_login.this, activity_form_cadastro.class);
        startActivity(intent);
    }
}