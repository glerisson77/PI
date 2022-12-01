package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pi.adapters.ImagesAdapter;
import com.example.pi.models.ProjectInformation;
import com.example.pi.models.StudentScore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecordsRHQuiz extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView studentsScoreListView;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_rhquiz);
        databaseReference = FirebaseDatabase.getInstance().getReference("rankingrhquiz");
        studentsScoreListView = findViewById(R.id.rankinglist);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.records_list_item, list);
        studentsScoreListView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                cont = 0;
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    if (cont < 10){
                        StudentScore studentScore = snapshot1.getValue(StudentScore.class);
                        list.add(cont + 1+"° " +studentScore.getStudentName() + " : " + studentScore.getStudentScorePoint());
                    }
                    cont++;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}