package com.example.myviewmodelapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
   // private String TAG=this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
       // MainActivityDataGenerator myData=new MainActivityDataGenerator();
       MainActivityDataGenerator viewModel= new ViewModelProvider(this)
               .get(MainActivityDataGenerator.class);
       // String myRandomNumber=myData.getNumber();
        String myRandomNumber=viewModel.getNumber();
        tv.setText(myRandomNumber);
        //Log.i(TAG,"random number set");
    }
}