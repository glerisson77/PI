package com.example.pi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi.R;
import com.example.pi.models.ProjectInformation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {

    StorageReference storageReference;
    Context context;
    ArrayList<ProjectInformation> list;

    public ImagesAdapter(){}

    public ImagesAdapter(Context context, ArrayList<ProjectInformation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.MyViewHolder holder, int position) {
        ProjectInformation projectInformation = list.get(position);
        holder.projectName.setText(projectInformation.getName());
        holder.professorName.setText(projectInformation.getProfessorName());
        holder.projectResume.setText(projectInformation.getProjectResume());
        holder.projectContact.setText(projectInformation.getProjectContact());
        String imageID = projectInformation.getImageName();
        storageReference = FirebaseStorage.getInstance().getReference("uploads/" +imageID);
        try {
            File localfile = File.createTempFile("tempfile", ".png");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            holder.imageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView projectName, professorName, projectResume, projectContact;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.nomeprojeto);
            professorName = itemView.findViewById(R.id.professor);
            projectResume = itemView.findViewById(R.id.informationproject);
            projectContact = itemView.findViewById(R.id.contatoprojeto);
            imageView = itemView.findViewById(R.id.projectimage);
        }
    }
}
