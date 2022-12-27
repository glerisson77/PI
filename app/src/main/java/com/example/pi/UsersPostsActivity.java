package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.adapters.ImagesAdapter;
import com.example.pi.adapters.PostsAdapter;
import com.example.pi.models.ProjectInformation;
import com.example.pi.models.UserInformation;
import com.example.pi.models.userPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class UsersPostsActivity extends AppCompatActivity {

    String passedUserName = "None", passedRa = "None" , passedUserID = "None", date, profilePictureString = "None", userCourses = "None";
    RecyclerView recyclerView;
    ArrayList<userPost> list;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    PostsAdapter adapter;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    TextView usernameTv;
    EditText postContent;
    ImageView userProfilePictureIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);

        recyclerView = findViewById(R.id.recyclerviewposts);
        databaseReference = FirebaseDatabase.getInstance().getReference("usersposts/");
        storageReference = FirebaseStorage.getInstance().getReference("postsuploads/");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendar = Calendar.getInstance();

        passedUserName = getIntent().getStringExtra("keyusername");
        passedRa = getIntent().getStringExtra("keyra");
        passedUserID = getIntent().getStringExtra("keyuserid");

        usernameTv = findViewById(R.id.usernametv);
        postContent = findViewById(R.id.postcontentet);
        userProfilePictureIv = findViewById(R.id.userpictureiv);

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

        adapter = new PostsAdapter(this, list, passedUserName, passedRa);
        recyclerView.setAdapter(adapter);

        usernameTv.setText(passedUserName);

        getTheUsers();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    userPost userPost = dataSnapshot.getValue(userPost.class);

//                    verifica se o nome e ra do usuario é igual a de um dos posts, se for igual ele vai permitir excluir

                    list.add(userPost);
                    for (userPost up : list){
                        if (up.getUserName().equals(passedUserName)){
                            up.setUserProfilePicture(profilePictureString);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    public void createPost(View v){

        if (postContent.getText().toString().matches("")){
            Toast.makeText(this, "Você não pode fazer um post vazio", Toast.LENGTH_SHORT).show();
        }else {
            String postContentString = postContent.getText().toString();

            String postid = String.valueOf(System.currentTimeMillis());
            dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
            date = dateFormat.format(calendar.getTime());
            userPost userPost = new userPost(passedUserName, date, profilePictureString, postContentString, "ti", passedRa, passedRa + postid);
            FirebaseDatabase.getInstance().getReference().child("usersposts/").child(passedRa + postid).setValue(userPost);
        }
    }
    private void getTheUsers() {
        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);

                    if (passedUserName.equals(userInformation.getUserName()) && passedRa.equals(userInformation.getUserRa())){
                        profilePictureString = userInformation.getProfilePicture();
                        userCourses = userInformation.getCourses();
//                        studentStatus.setText("status: " + userInformation.getStatus());
                    }

                    storageReference = FirebaseStorage.getInstance().getReference("userspictures/" + passedRa + passedUserName + "/" +userInformation.getProfilePicture());
                    try {
                        File localfile = File.createTempFile("tempfile", ".png");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        userProfilePictureIv.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(UsersPostsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deletePost(View v){
        for (userPost up : list){
            if (up.getUserName().equals(passedUserName) && up.getUserRa().equals(passedRa)){
                FirebaseDatabase.getInstance().getReference("usersposts/").child(up.getPostID()).removeValue();
            }
        }
    }

    public void onPostLongClick(int position){
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }
}