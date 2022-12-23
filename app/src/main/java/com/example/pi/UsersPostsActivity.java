package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.pi.adapters.ImagesAdapter;
import com.example.pi.adapters.PostsAdapter;
import com.example.pi.models.ProjectInformation;
import com.example.pi.models.userPost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class UsersPostsActivity extends AppCompatActivity {

    String passedUserName = "None", passedRa = "None" , passedUserID = "None";
    RecyclerView recyclerView;
    ArrayList<userPost> list;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    PostsAdapter adapter;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);

        recyclerView = findViewById(R.id.recyclerviewposts);
        databaseReference = FirebaseDatabase.getInstance().getReference("usersposts/");
        storageReference = FirebaseStorage.getInstance().getReference("postsuploads/");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostsAdapter(this, list);
        recyclerView.setAdapter(adapter);
        calendar = Calendar.getInstance();

        passedUserName = getIntent().getStringExtra("keyusername");
        passedRa = getIntent().getStringExtra("keyra");
        passedUserID = getIntent().getStringExtra("keyuserid");

        if (getIntent().getBooleanExtra("keyusername", false) == true){
            passedUserName = "None";
        }else{
            passedUserName = getIntent().getStringExtra("keyusername");
        }

        if (getIntent().getBooleanExtra("keyra", false) == true){
            passedRa = "None";
        }else{
            passedRa = getIntent().getStringExtra("keyra");
        }

        if (getIntent().getBooleanExtra("keyuserid", false) == true){
            passedUserID = "None";
        }else{
            passedUserID = getIntent().getStringExtra("keyuserid");
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    userPost userPost = dataSnapshot.getValue(userPost.class);
                    list.add(userPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void createPost(View v){
        String postid = String.valueOf(System.currentTimeMillis());
        dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        userPost userPost = new userPost(passedUserName, date, "1671822463874", "testando", "ti", passedRa);
        FirebaseDatabase.getInstance().getReference().child("usersposts/").child(passedRa + postid).setValue(userPost);

    }
}