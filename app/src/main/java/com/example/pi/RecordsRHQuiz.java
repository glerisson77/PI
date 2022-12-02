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
import java.util.Collections;
import java.util.Comparator;

public class RecordsRHQuiz extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView studentsScoreListView;
    ArrayList<StudentScore> studentScoresSortList;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_rhquiz);
        databaseReference = FirebaseDatabase.getInstance().getReference("rankingrhquiz");
        studentsScoreListView = findViewById(R.id.rankinglist);
        studentScoresSortList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.records_list_item, list);
        studentsScoreListView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                cont = 0;
                ///obtem os valores do database e gera objeto do tipo studentscore a partir dos valores
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    if (cont < 10){
                        StudentScore studentScore = snapshot1.getValue(StudentScore.class);
                        studentScoresSortList.add(studentScore);
//                        list.add(cont + 1+"° " +studentScore.getStudentName() + " : " + studentScore.getStudentScorePoint());
                    }

                }
                ///organiza a lista de rankings do maior para o menor
                Collections.sort(studentScoresSortList, new Comparator<StudentScore>() {
                    @Override
                    public int compare(StudentScore studentScoret1, StudentScore studentScoret2) {
                        ///compara em ordem decrescente, inverter o objeto para or crescente
                        return studentScoret2.getStudentScorePoint().compareTo(studentScoret1.getStudentScorePoint());
                    }
                });
                for (StudentScore studentScore : studentScoresSortList){
                    ///adiciona o nome e pontos da pessoa na listview
                    list.add(cont + 1+"° " +studentScore.getStudentName() + " : " + studentScore.getStudentScorePoint());
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