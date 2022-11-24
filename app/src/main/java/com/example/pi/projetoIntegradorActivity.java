package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pi.adapters.imagesAdapter;
import com.example.pi.models.projectInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class projetoIntegradorActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<projectInformation> list;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    imagesAdapter adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(projetoIntegradorActivity.this, IconsActivityLayout.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_integrador);

        recyclerView = findViewById(R.id.recyclerviewpi);
        databaseReference = FirebaseDatabase.getInstance().getReference("projects");
        storageReference = FirebaseStorage.getInstance().getReference("uploads/");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new imagesAdapter(this, list);
        recyclerView.setAdapter(adapter);
//        idtxt = "C:/Users/gleri/AndroidStudioProjects/PIcopy/app/src/main/res/values/idtxt.txt";
//        Toast.makeText(projetoIntegradorActivity.this, idtxt, Toast.LENGTH_SHORT).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    projectInformation projectInformation = dataSnapshot.getValue(projectInformation.class);
                    list.add(projectInformation);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void postarProjeto(View v){
        Intent intent = new Intent(projetoIntegradorActivity.this, uploadImage.class);
        startActivity(intent);
    }

    public void deleteProject(View v){
        databaseReference.child("id1669162440741").removeValue();
        storageReference.child("1669162437358.jpg").delete();

    }
}