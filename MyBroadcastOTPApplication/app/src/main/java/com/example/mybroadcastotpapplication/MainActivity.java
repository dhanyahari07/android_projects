package com.example.mybroadcastotpapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements SMSListener {
    private OTPReceiver broadcastReceiver;
    public static final String OTP_REGEX = "[0-9]{1,6}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OTPReceiver.bindListener(this);

        broadcastReceiver = new OTPReceiver();

    }

    @Override
    public void messageReceived(String messageText, String senderno) {

        Log.e("MainActivity","Message Received : " + messageText + "  from : "+senderno);
        Toast.makeText(MainActivity.this,"Message: "+
                messageText + " From : " + senderno, Toast.LENGTH_LONG).show();
        Pattern pattern = Pattern.compile(OTP_REGEX);
        Matcher matcher = pattern.matcher(messageText);
        String otp = "XXXXX";
        while (matcher.find())
        {
            otp = matcher.group();
        }
        Toast.makeText(MainActivity.this,"OTP Received is: "+ otp ,Toast.LENGTH_LONG).show();
        TextView t = findViewById(R.id.tv);
        t.setText(otp);



    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter
                ("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);

    }
}