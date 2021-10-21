package com.example.mymapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etSourse,etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSourse=findViewById(R.id.src);
        etDestination=findViewById(R.id.desti);
        btTrack=findViewById(R.id.track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get value from edit text
                String sSourse=etSourse.getText().toString().trim();
                String sDestination=etDestination.getText().toString().trim();

                //check

                if(sSourse.equals("")&&sDestination.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"enter both location",Toast.LENGTH_LONG).show();

                }
                else
                {
                    //when both vale display track
                    DisplayTrack(sSourse,sDestination);


                }

            }
        });
    }

    private void DisplayTrack(String sSourse, String sDestination) {
        //if device does not have a map installed then redirect to play store
        try{
            //when google map is installed
            //initiate uri
            Uri uri= Uri.parse("http://www.google.co.in/maps/dir/"+sSourse+"/"+sDestination);
            //init intent with action view

            Intent intent=new Intent(Intent.ACTION_VIEW,uri);

            //set package

            intent.setPackage("com.google.android.apps.maps");

            //set flag

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start acti
            startActivity(intent);

        }
        catch (ActivityNotFoundException e)
        {
            //when google mao is not insalled

            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            //init intent withaction

            Intent intent=new Intent(Intent.ACTION_VIEW,uri);

            //set package

            intent.setPackage("com.google.android.apps.maps");

            //set flag

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start acti
            startActivity(intent);
        }

    }
}