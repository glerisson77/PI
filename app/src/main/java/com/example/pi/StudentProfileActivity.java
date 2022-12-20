package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pi.models.StudentScore;
import com.example.pi.models.UserInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentProfileActivity extends AppCompatActivity {

    TextView studentInfo;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        studentInfo = findViewById(R.id.studentinfo);
        names = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);
                    names.add(userInformation.getUserName());
                    for (String name : names){
                        studentInfo.setText(studentInfo.getText() + name);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}