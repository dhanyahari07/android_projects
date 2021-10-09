package com.example.mylivedataapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button random_nu;
    private String TAG=this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        random_nu=findViewById(R.id.random_no);


        //MainActivityDataGenerator myData=new MainActivityDataGenerator();
        MainActivityViewModel viewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
        //String myRandomNumber=myData.getNumber();
        LiveData<String> myRandomNumber=viewModel.getNumber();
        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });
        random_nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createNumber();
            }
        });

        Log.i(TAG,"random number set");

    }
}