package com.example.pi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi.R;
import com.example.pi.models.userPost;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>{

    Context context;
    ArrayList<userPost> list;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView projectName, professorName, projectResume, projectContact, uploaderUser;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.nomeprojeto);
            professorName = itemView.findViewById(R.id.professor);
            projectResume = itemView.findViewById(R.id.informationproject);
            projectContact = itemView.findViewById(R.id.contatoprojeto);
            uploaderUser = itemView.findViewById(R.id.useruploader);
            imageView = itemView.findViewById(R.id.projectimage);
        }
    }
}
