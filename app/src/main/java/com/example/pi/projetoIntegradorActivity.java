package com.example.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Arrays;

public class projetoIntegradorActivity extends AppCompatActivity {

    StorageReference storageReference;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10,imageView11, imageView12, imageView13, imageView14;
    TextView projectname1, projectname2, projectname3, projectname4, projectname5, projectname6, projectname7, projectname8, projectname9, projectname10, projectname11, projectname12, projectname13, projectname14;
    ArrayList<ImageView> imagesList;
    Button getImagebt;

    ArrayList<String> imagesname = new ArrayList<>();
    private Integer imagesCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_integrador);

        imageView1 = findViewById(R.id.imageView1);imageView2 = findViewById(R.id.imageView2);imageView3 = findViewById(R.id.imageView3);imageView4 = findViewById(R.id.imageView4);imageView5 = findViewById(R.id.imageView5);imageView6 = findViewById(R.id.imageView6);imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);imageView9 = findViewById(R.id.imageView9);imageView10 = findViewById(R.id.imageView10);imageView11 = findViewById(R.id.imageView11);imageView12 = findViewById(R.id.imageView12);imageView13 = findViewById(R.id.imageView13);imageView14 = findViewById(R.id.imageView14);



        imagesList = new ArrayList<>(Arrays.asList(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10,imageView11, imageView12, imageView13, imageView14));

        imagesCont = 0;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("imagesnames");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    String name = snapshot.getValue().toString();
//                    Toast.makeText(showImagesActivity.this, name, Toast.LENGTH_SHORT).show();
                    if (imagesCont < imagesList.toArray().length)
                        getImages(name, imagesCont);
                    imagesCont++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getImages(String imageID, Integer imagesCont){

//                String imageID = binding.imagenameet.getText().toString();

        storageReference = FirebaseStorage.getInstance().getReference("uploads/" +imageID);

        try {
            File localfile = File.createTempFile("tempfile", ".png");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
//                            imageView1.setImageBitmap(bitmap);
                            imagesList.get(imagesCont).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(projetoIntegradorActivity.this, "Failed to retrieve image", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void postarProjeto(View v){
        Intent intent = new Intent(projetoIntegradorActivity.this, uploadImage.class);
        startActivity(intent);
    }
}