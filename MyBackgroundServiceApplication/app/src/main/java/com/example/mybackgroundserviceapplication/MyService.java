package com.example.mybackgroundserviceapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }
    MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this,"onCreate",
                Toast.LENGTH_SHORT).show();

        myPlayer = MediaPlayer.create(this,R.raw.sample3);  //  ← Give your music file name //without extension
        myPlayer.setLooping(false); // Set looping

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);

        Toast.makeText(this,"onStartCommand",
                Toast.LENGTH_SHORT).show();
        myPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"OnDestroy",
                Toast.LENGTH_SHORT).show();
        myPlayer.stop();

    }
}