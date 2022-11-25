package com.example.pi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi.R;
import com.example.pi.models.Questions;
import com.example.pi.models.projectInformation;
import com.example.pi.quizActivity;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class questionsAdapter extends RecyclerView.Adapter<questionsAdapter.MyViewHolder>{

    StorageReference storageReference;
    Context context;
    ArrayList<Questions> listquiz;

    public questionsAdapter(){}

    public questionsAdapter(Context context, ArrayList<Questions> listquiz){
        this.context = context;
        this.listquiz = listquiz;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull questionsAdapter.MyViewHolder holder, int position) {

        Questions pergunta = listquiz.get(position);
        holder.pergunta.setText(pergunta.getPergunta());
        holder.resposta1.setText(pergunta.getResposta1());
        holder.resposta2.setText(pergunta.getResposta2());
        holder.resposta3.setText(pergunta.getResposta3());
        holder.resposta4.setText(pergunta.getResposta4());
    }

    @Override
    public int getItemCount() {
        return listquiz.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pergunta;
        RadioButton resposta1, resposta2, resposta3, resposta4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pergunta = itemView.findViewById(R.id.perguntaqi);
            resposta1 = itemView.findViewById(R.id.resposta1rb);
            resposta2 = itemView.findViewById(R.id.resposta2rb);
            resposta3 = itemView.findViewById(R.id.resposta3rb);
            resposta4 = itemView.findViewById(R.id.resposta4rb);
        }

    }
}
