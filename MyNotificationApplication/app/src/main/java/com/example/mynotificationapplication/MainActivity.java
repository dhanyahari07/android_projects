package com.example.mynotificationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationHelper notificationHelper;

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
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("MainActivity","Executing Notification with channels");
                    String channel_Id = "my_channel_01";// The id of the channel.
                    CharSequence channelName = "NotifChannel";// The user-visible name of the channel.
                    //https://developer.android.com/training/notify-user/channels#importance
                    int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

                    manager =    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    // Create a notification and set the notification channel.
                    NotificationChannel channel = new NotificationChannel(channel_Id, channelName,
                            channelImportance);
                    manager.createNotificationChannel(channel);

                    //Create the intent that’ll fire when the user taps the notification//
                    //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")); //implicit intent
                    Intent intent = new Intent(MainActivity.this, NotificationActivity.class); //explicit intent
                    //PendingIntent.FLAG_UPDATE_CURRENT - Flag indicating that if the described PendingIntent already exists,
                    // then keep it but replace its extra data with what is in this new Intent.
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,
                            intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification  = new Notification.Builder(MainActivity.this,channel_Id)
                            .setContentTitle("New Notification Message")
                            .setAutoCancel(false)
                            //.setOngoing(true)
                            .setContentText("Browse the content")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentIntent(pendingIntent)
                            .build();

                    manager.notify(notifyID, myNotification);
                }
                else{
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
//                notificationHelper.sendHighPriorityNotification("this is title", "this is some awesome notificaiton. wow i learnt it the easy way.", MainActivity.class);
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