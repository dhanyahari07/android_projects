package com.example.mybackgroundserviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textview);

    }

    public void start(View v)
    {
        Intent i=new Intent(this,
                MyService.class);
        startService(i);
        textView.setText("Service Status : Service Started");


    }
    public void stop(View v)
    {

        Intent i=new Intent(this,MyService.class);
        stopService(i);
        textView.setText("Service Status : Service Stopped");

    }


}