package com.example.myrecyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.recyclerview);
        MyListData[] myListData=new MyListData[]{
                new MyListData("Amrita",R.drawable.apple),
                new MyListData("Amrita1",R.drawable.apple),
                new MyListData("Amrita2",R.drawable.apple),
                new MyListData("Amrita3",R.drawable.apple),
                new MyListData("Amrita4",R.drawable.apple),
                new MyListData("Amrita5",R.drawable.apple),
                new MyListData("Amrita6",R.drawable.apple),
                new MyListData("Amrita7",R.drawable.apple),
                new MyListData("Amrita8",R.drawable.apple),
        };
        rv.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter=new MyAdapter(this,myListData);
        rv.setAdapter(adapter);

    }
}