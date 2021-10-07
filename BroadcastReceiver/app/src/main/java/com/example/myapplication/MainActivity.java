package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //MyReceiver myReceiver =new MyReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
@Override
protected void onStart() {
super.onStart();
IntentFilter intentFilter=new IntentFilter();
intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
this.registerReceiver(myReceiver,intentFilter);

}

@Override
protected void onStop() {
super.onStop();
this.unregisterReceiver(myReceiver);
}*/

}