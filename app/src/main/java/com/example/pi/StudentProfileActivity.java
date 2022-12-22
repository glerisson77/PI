package com.example.pi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.models.ProjectInformation;
import com.example.pi.models.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StudentProfileActivity extends AppCompatActivity {

    TextView studentName, studentRa, studentStatus, studentCourses;
    ImageView studenPicture;
    String passedUserName, passedRa, passedUserID;
    ArrayList<String> names;
    StorageReference storageReference;

    private static final int IMAGE_REQUEST = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        studentName = findViewById(R.id.studentname);
        studentRa = findViewById(R.id.studentra);
        studentStatus = findViewById(R.id.studentstats);
        studentCourses = findViewById(R.id.studentcourses);
        studenPicture = findViewById(R.id.studentpicture);
        names = new ArrayList<>();

        passedUserName = getIntent().getStringExtra("keyusername");
        passedRa = getIntent().getStringExtra("keyra");
        passedRa = getIntent().getStringExtra("keyuserid");

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

        getTheUsers();
    }

    private void getTheUsers() {
        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);

                    if (passedUserName.equals(userInformation.getUserName()) && passedRa.equals(userInformation.getUserRa())){
                        studentName.setText(userInformation.getUserName());
                        studentRa.setText(studentRa.getText() + userInformation.getUserRa());
                        studentCourses.setText(studentCourses.getText() + userInformation.getCourses());
                        studentStatus.setText(studentStatus.getText() + userInformation.getStatus());
                    }

                    storageReference = FirebaseStorage.getInstance().getReference("userspictures/" +userInformation.getProfilePicture());
                    try {
                        File localfile = File.createTempFile("tempfile", ".png");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        studenPicture.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(StudentProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void openImage(View v) {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK) {
            imageUri = data.getData();
            studenPicture.setImageURI(imageUri);
            uploadImage();
        }
    }

    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Upload");
        pd.show();

        if (imageUri != null){
            String imageName = String.valueOf(System.currentTimeMillis());

            ///storage the image
//            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(imageName + "." + getFileExtension(imageUri));
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("userspictures").child(imageName);
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

//                    FirebaseDatabase.getInstance().getReference().child("imagesnames").child("id" + System.currentTimeMillis()).setValue(imageName);
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            putImageNameInUserInfo(imageName);
                            String url = uri.toString();
                            Log.d("DownloadUrl", url);
                            pd.dismiss();
                            Toast.makeText(StudentProfileActivity.this, "O projeto foi postado", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
    }

    private void putImageNameInUserInfo(String ImageName) {

        FirebaseDatabase.getInstance().getReference().child("users").child(passedUserID).child("profilePicture").setValue(ImageName);

//        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot snapshot1: snapshot.getChildren()){
//                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);
//
//                    if (passedUserName.equals(userInformation.getUserName()) && passedRa.equals(userInformation.getUserRa())){
//                        FirebaseDatabase.getInstance().getReference().child("users").child(userInformation.getUserId()).child("profilePicture").setValue(ImageName);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    public void editUserInfo(View v){

    }
}