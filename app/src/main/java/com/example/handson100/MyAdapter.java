package com.example.handson100;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    List<Model> list;
    ArrayList<Integer> movieList;

    public MyAdapter(Context context, List<Model> list,ArrayList<Integer> movieList) {
        this.context = context;
        this.list = list;
        this.movieList=movieList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

            holder.imageView.setImageResource(list.get(position).getImage());
//            holder.textView.setText(String.valueOf(movieList.get(position)));

            holder.itemView.setOnClickListener(v -> Toast.makeText(context,"Position"+position,Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.text_below_image);
        }
    }
}
