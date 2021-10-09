package com.example.myrecyclerviewapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends  RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.tv);
        imageView=itemView.findViewById(R.id.iv);
    }

}
