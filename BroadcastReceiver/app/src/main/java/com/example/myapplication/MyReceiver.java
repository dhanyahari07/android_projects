package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
     /*String actionString=intent.getAction();
String timezone=intent.getStringExtra("time-zone");
Log.d("broadcast",actionString+" "+timezone);

boolean isOn=intent.getBooleanExtra("state",false);
Log.d("broadcast","Airplane mode is"+isOn);*/

        Log.d("broadcast","SMS Received");

        Bundle bundle = intent.getExtras();
        SmsMessage smsMessage;

        if (Build.VERSION.SDK_INT >= 19) { //KITKAT
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            smsMessage = msgs[0];
        } else {
            Object pdus[] = (Object[]) bundle.get("pdus");
            String format = bundle.getString("format");
//smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
            smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0], format);
        }
        String messageBody = smsMessage.getMessageBody();
        Toast.makeText(context, messageBody,Toast.LENGTH_LONG).show();

    }
}