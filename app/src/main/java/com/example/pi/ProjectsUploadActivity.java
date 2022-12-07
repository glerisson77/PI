package com.example.pi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pi.models.DatabaseRA;
import com.example.pi.models.MessageDialog;
import com.example.pi.models.ProjectInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class ProjectsUploadActivity extends AppCompatActivity {

    Button uploadImagebt;
    EditText projectName;
    EditText professorName;
    EditText projectResume;
    EditText projectContact;
    DatabaseRA myDB;

    private static final int IMAGE_REQUEST = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_upload);

        myDB = new DatabaseRA(this);
        projectName = findViewById(R.id.nomeprojetoet);
        professorName = findViewById(R.id.professoret);
        projectResume = findViewById(R.id.informationprojectet);
        projectContact = findViewById(R.id.contatoprojetoet);
        uploadImagebt = findViewById(R.id.postarprojetobt);

        uploadImagebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean projectNameCheck = projectName.getText().toString().matches("");
                Boolean professorNameCheck = professorName.getText().toString().matches("");
                Boolean projectResumeCheck = projectResume.getText().toString().matches("");
                Boolean projectContactCheck = projectContact.getText().toString().matches("");
                if (projectNameCheck == true || professorNameCheck == true || projectResumeCheck == true || projectContactCheck == true){
                    Toast.makeText(ProjectsUploadActivity.this, "Um ou mais campos estao vazios", Toast.LENGTH_SHORT).show();
                }else{
                    openImage();
                }
            }
        });
    }

    private void openImage() {
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
            uploadImage();
        }
    }
    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Upload");
        pd.show();

        if (imageUri != null){
            String imageName = String.valueOf(System.currentTimeMillis());

            ///storage the imagename to exclude after #implement
            String projectNameS = projectName.getText().toString();
            String professorNameS = professorName.getText().toString();
            String projectResumeS = projectResume.getText().toString();
            String projectContactS = projectContact.getText().toString();

            ///the ra will be picked from here using sql lite and inserted in a string to be put inside the projectinformation object

            ///storage the image
//            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(imageName + "." + getFileExtension(imageUri));
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(imageName);
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    ///store the name of the file on the database to after retrieving
                    String ra = getRaFromDB();
                    ProjectInformation projectInformation = new ProjectInformation(projectNameS, professorNameS, projectResumeS, projectContactS, imageName, ra);
//                    FirebaseDatabase.getInstance().getReference().child("imagesnames").child("id" + System.currentTimeMillis()).setValue(imageName);
                    String projectid = "id" + imageName;
                    ///storage the project id to exclude after #implement
                    FirebaseDatabase.getInstance().getReference().child("projects").child(projectid).setValue(projectInformation);
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            Log.d("DownloadUrl", url);
                            pd.dismiss();
                            Toast.makeText(ProjectsUploadActivity.this, "O projeto foi postado", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }
    public String getRaFromDB (){
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0){
            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append(res.getString(0));
        }
        String ra_text = buffer.toString();
        return ra_text;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_icons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sobre) {
            showUpDialogMessage("Aqui você pode publicar o projeto da sua turma, digite as informações nos campos e escolha uma imagem representativa sobre o projeto", "informações");

        }
        return super.onOptionsItemSelected(item);
    }

    public void showUpDialogMessage(String txt, String title) {
        MessageDialog messageDialog = new MessageDialog(txt, title);
        messageDialog.show(getSupportFragmentManager(), "mensagem");
    }
}