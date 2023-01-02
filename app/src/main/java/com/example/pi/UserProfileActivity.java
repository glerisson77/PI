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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    TextView userName, userRa, userStatus, userCourses;
    EditText userEditStatus, userEditCourses, userEditName;
    ImageView userPicture;
    String passedUserName, passedRa, passedUserID, passedUserOldProfile, imageNameToDelete;
    ArrayList<String> names;
    StorageReference storageReference;
    LinearLayout editInfoLayout;
    Boolean activeEditInfo = false;


    private static final int IMAGE_REQUEST = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        connectViews();
        makeAppearEditInfoLayout();

        names = new ArrayList<>();
        getExtra();
        getTheUsers();

    }

    private void getTheUsers() {
        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);

                    if (passedUserName.equals(userInformation.getUserName()) && passedRa.equals(userInformation.getUserRa())){
                        userName.setText(userInformation.getUserName());
                        userRa.setText("ra: " + userInformation.getUserRa());
                        userCourses.setText("cursos: " + userInformation.getCourses());
                        userStatus.setText("status: " + userInformation.getStatus());
                    }

                    storageReference = FirebaseStorage.getInstance().getReference("userspictures/" + passedRa + passedUserID + "/" +userInformation.getProfilePicture());
                    try {
                        File localfile = File.createTempFile("tempfile", ".png");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        userPicture.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            userPicture.setImageURI(imageUri);
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
            FirebaseDatabase.getInstance().getReference().child("users").child(passedUserID).child("profilePicture").setValue(imageName);
            deleteExtraImages();
            FirebaseDatabase.getInstance().getReference().child("users").child(passedUserID).child("oldProfilePicture").setValue(imageName);

            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("userspictures").child(passedRa + passedUserID).child(imageName);
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
                            Toast.makeText(UserProfileActivity.this, "Foto do perfil alterada com sucesso", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
    }

    private void putImageNameInUserInfo(String ImageName) {

        FirebaseDatabase.getInstance().getReference().child("users").child(passedUserID).child("profilePicture").setValue(ImageName);

    }

    public void editUserInfo(View v){
        if (activeEditInfo == false){
            activeEditInfo = true;
        }else {
            activeEditInfo = false;
        }
        makeAppearEditInfoLayout();
    }

    public void makeAppearEditInfoLayout(){
        if (activeEditInfo == false){
            editInfoLayout.setVisibility(View.GONE);
        }else {
            editInfoLayout.setVisibility(View.VISIBLE);
        }
    }

    public void deleteExtraImages(){
        FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UserInformation userInformation = snapshot1.getValue(UserInformation.class);

                    if (userInformation.getUserId().equals(passedUserID)){
                        if (userInformation.getOldProfilePicture().equals(userInformation.getProfilePicture())){

                        }else{
                            FirebaseStorage.getInstance().getReference().child("userspictures").child(userInformation.getProfilePicture()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void connectViews(){
        userName = findViewById(R.id.studentname);
        userRa = findViewById(R.id.studentra);
        userStatus = findViewById(R.id.studentstats);
        userCourses = findViewById(R.id.studentcourses);
        userPicture = findViewById(R.id.studentpicture);
        editInfoLayout = findViewById(R.id.editinfolayout);
        userEditStatus = findViewById(R.id.editprofilestats);
        userEditCourses = findViewById(R.id.editprofilecourses);
        userEditName = findViewById(R.id.editprofilename);
    }

    public void getExtra(){
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

        if (getIntent().getBooleanExtra("keyuseroldprofilepic", false) == true){
            passedUserOldProfile = "None";
        }else{
            passedUserOldProfile = getIntent().getStringExtra("keyuseroldprofilepic");
        }
    }

    public void saveInformations(View v){
        if (userEditStatus.getText().toString().matches("")) {

        }else {
            FirebaseDatabase.getInstance().getReference().child("users/").child(passedUserID + "/").child("status").setValue(userEditStatus.getText().toString());
        }

        if (userEditCourses.getText().toString().matches("")) {

        }else {
            FirebaseDatabase.getInstance().getReference().child("users/").child(passedUserID + "/").child("courses").setValue(userEditCourses.getText().toString());
        }

        if (userEditName.getText().toString().matches("")) {

        }else {
            FirebaseDatabase.getInstance().getReference().child("users/").child(passedUserID + "/").child("userName").setValue(userEditName.getText().toString());
        }
        getTheUsers();

    }
}