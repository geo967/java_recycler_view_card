package com.example.handson100;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private final Context context;
    List<ModelOfApi> movieList;

    public Adaptery(Context context, List<ModelOfApi> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public Adaptery.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.activity_main,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptery.MyViewHolder holder, int position) {
        holder.textView.setText(movieList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
