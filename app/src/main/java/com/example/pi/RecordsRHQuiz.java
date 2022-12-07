package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.adapters.ImagesAdapter;
import com.example.pi.models.DatabaseRA;
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
    DatabaseRA myDB;
    ListView studentsScoreListView;
    ArrayList<StudentScore> studentScoresSortList;
    ArrayList<Integer> myScores;
    TextView myRecord;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_rhquiz);
        databaseReference = FirebaseDatabase.getInstance().getReference("rankingrhquiz");
        myDB = new DatabaseRA(this);
        studentsScoreListView = findViewById(R.id.rankinglist);
        studentScoresSortList = new ArrayList<>();
        myScores = new ArrayList<>();
        myRecord = findViewById(R.id.minhapontuação);

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
                        if (studentScore.getRa().equals(getRaFromDB())){
//                            if (!studentScore.getStudentScorePoint().equals(""))
                            myScores.add(studentScore.getStudentScorePoint());
                            Collections.sort(myScores);
                            if (myScores.size() < 1){
                                myRecord.setText(String.valueOf(myScores.get(myScores.size() -1)));
                            }else{
                                myRecord.setText("Você ainda não pontuou");
                            }
                        }
                        studentScoresSortList.add(studentScore);
//                        list.add(cont + 1+"° " +studentScore.getStudentName() + " : " + studentScore.getStudentScorePoint());
                    }

                }
                ///organiza a lista de rankings do maior para o menor
                Collections.sort(studentScoresSortList, new Comparator<StudentScore>() {
                    @Override
                    public int compare(StudentScore studentScoret1, StudentScore studentScoret2) {
                        ///compara em ordem decrescente, inverter o objeto para or crescente
                        return Integer.compare(studentScoret2.getStudentScorePoint(), studentScoret1.getStudentScorePoint());
//                        return studentScoret2.getStudentScorePoint().compareTo(studentScoret1.getStudentScorePoint());
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