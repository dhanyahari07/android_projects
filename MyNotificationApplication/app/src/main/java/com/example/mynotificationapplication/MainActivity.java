package com.example.mynotificationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnShow, btnClear;
    NotificationManager manager;
    Notification myNotification;
    int notifyID =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = (Button) findViewById(R.id.showNoti);
        btnClear = (Button) findViewById(R.id.clearNoti);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","Executing Notification without channels");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                //PendingIntent.FLAG_UPDATE_CURRENT - Flag indicating that if the described PendingIntent already exists,
                // then keep it but replace its extra data with what is in this new Intent.
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setAutoCancel(true);
                builder.setContentTitle("App Notification");
                builder.setContentText("You have a new message");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setContentIntent(pendingIntent);
                builder.setOngoing(true);
                builder.setSubText("This is subtext...");   //API level 16
                //builder.setNumber(100);
                myNotification= builder.build();
                //  myNotication = builder.getNotification();
                // myNotication.flags = Notification.FLAG_AUTO_CANCEL;
                manager.notify(notifyID,myNotification);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.cancel(notifyID);

            }
        });
    }
}