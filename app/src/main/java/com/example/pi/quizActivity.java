package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pi.adapters.imagesAdapter;
import com.example.pi.adapters.questionsAdapter;
import com.example.pi.models.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class quizActivity extends AppCompatActivity {

    RecyclerView recyclerViewquiz;
    ArrayList<Questions> listquiz;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    questionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        recyclerViewquiz = findViewById(R.id.recyclerviewquiz);
        databaseReference = FirebaseDatabase.getInstance().getReference("quiz");
        listquiz = new ArrayList<Questions>();
        recyclerViewquiz.setLayoutManager(new LinearLayoutManager(this));
        adapter = new questionsAdapter(quizActivity.this, listquiz);
        recyclerViewquiz.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listquiz.clear();
                int perguntaCont = 1;
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Questions pergunta = dataSnapshot.getValue(Questions.class);
                    pergunta.setId(perguntaCont);
                    listquiz.add(pergunta);
                    perguntaCont++;
                    Toast.makeText(quizActivity.this, String.valueOf(pergunta.getId()).toString(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void postarPergunta(View v){
        Intent intent = new Intent(quizActivity.this, UploadQuestions.class);
        startActivity(intent);
    }
}