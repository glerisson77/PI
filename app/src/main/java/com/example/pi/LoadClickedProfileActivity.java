package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class LoadClickedProfileActivity extends AppCompatActivity {

    String userName = "", userRa = "", userCourses = "", userStatus = "", userProfilePicture = "";
    TextView userNametv, userCousestv, userStatustv;
    ImageView userProfilePictureiv;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_clicked_profile);

        checkPassedValues();
        connectViews();
        setValuesonViews();

    }

    public void checkPassedValues(){
        if (getIntent().getBooleanExtra("keyusername", false) == true){
            userName = "None";
        }else{
            userName = getIntent().getStringExtra("keyusername");
        }

        if (getIntent().getBooleanExtra("keyuserra", false) == true){
            userRa = "None";
        }else{
            userRa = getIntent().getStringExtra("keyuserra");
        }

        if (getIntent().getBooleanExtra("keyusercourses", false) == true){
            userCourses = "None";
        }else{
            userCourses = getIntent().getStringExtra("keyusercourses");
        }

        if (getIntent().getBooleanExtra("keyuserprofilepicture", false) == true){
            userProfilePicture = "None";
        }else{
            userProfilePicture = getIntent().getStringExtra("keyuserprofilepicture");
        }
    }

    public void connectViews(){
        userNametv = findViewById(R.id.studentnamecp);
        userCousestv = findViewById(R.id.studentcoursescp);
        userStatustv = findViewById(R.id.studentstatscp);
        userProfilePictureiv = findViewById(R.id.studentpicturecp);
    }

    public void setValuesonViews(){
        userNametv.setText(userName);
        userCousestv.setText("cursos" +userCourses);
        userStatustv.setText("status: " + "implement after");

        storageReference = FirebaseStorage.getInstance().getReference("userspictures/" + userRa + userName + "/" + userProfilePicture);
        try {
            File localfile = File.createTempFile("tempfile", ".png");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            userProfilePictureiv.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoadClickedProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}