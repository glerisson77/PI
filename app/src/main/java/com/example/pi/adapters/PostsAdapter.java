package com.example.pi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi.R;
import com.example.pi.UsersPostsActivity;
import com.example.pi.models.userPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>{

    String passedName;
    String passedRa;
    Context context;
    ArrayList<userPost> list;
    StorageReference storageReference;

    public PostsAdapter(Context context, ArrayList<userPost> list, String passedName, String passedRa) {
        this.context = context;
        this.list = list;
        this.passedName = passedName;
        this.passedRa = passedRa;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.posts_item, parent, false);
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

        if (passedName.equals(userPost.getUserName()) && passedRa.equals(userPost.getUserRa())){
            holder.deletePost.setVisibility(View.VISIBLE);
        }else {
            holder.deletePost.setVisibility(View.INVISIBLE);
        }
        holder.deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passedName.equals(userPost.getUserName()) && passedRa.equals(userPost.getUserRa()))
                FirebaseDatabase.getInstance().getReference("usersposts/").child(userPost.getPostID()).removeValue();
            }

        });

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
        Button deletePost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.usernameitem);
            userCourses = itemView.findViewById(R.id.usercoursesitem);
            content = itemView.findViewById(R.id.postcontentitem);
            currentdate = itemView.findViewById(R.id.currentdateitem);
            imageView = itemView.findViewById(R.id.userpictureitem);
            deletePost = itemView.findViewById(R.id.deletepostbt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    userName.setTextColor(Color.RED);
                    return true;
                }
            });
        }


    }
}
