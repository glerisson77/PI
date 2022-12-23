package com.example.pi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi.R;
import com.example.pi.models.userPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>{

    Context context;
    ArrayList<userPost> list;
    StorageReference storageReference;

    public PostsAdapter(Context context, ArrayList<userPost> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_posts_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.MyViewHolder holder, int position) {
        userPost userPost = list.get(position);
        holder.userName.setText(userPost.getUserName());
        holder.userCourses.setText("cursos: " + userPost.getUserCourses());
        holder.content.setText(userPost.getPostContent());
        String postDate = userPost.getPosteDate().replace("T", " ");
        holder.currentdate.setText(userPost.getPosteDate());

        String imageID = userPost.getUserProfilePicture();

//        storageReference = FirebaseStorage.getInstance().getReference("uploads/" +imageID + ".png");
        storageReference = FirebaseStorage.getInstance().getReference("userspictures/" + userPost.getUserRa() + userPost.getUserName() + "/" +imageID);
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
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
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

        TextView userName, userCourses, content, currentdate;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.usernameitem);
            userCourses = itemView.findViewById(R.id.usercoursesitem);
            content = itemView.findViewById(R.id.postcontentitem);
            currentdate = itemView.findViewById(R.id.currentdateitem);
            imageView = itemView.findViewById(R.id.userpictureitem);

        }
    }
}
