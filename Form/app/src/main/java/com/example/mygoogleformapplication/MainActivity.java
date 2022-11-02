package com.example.mygoogleformapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final MediaType FORM_DATA_TYPE=
            MediaType.parse("application/x-www-form-urlencoded;" +
                    " charset=utf-8");
    public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSdQ4l6B4p2w_F-bxB4yKI46gJ-hhbmpQAi_7fc3B3WKRafiHw/formResponse";

  //  public static final String NAME_KEY="entry_1470004185";
   // public static final String ROLL_KEY="entry_301820120";
    private final Context context=this;
    private EditText edtname;
    private EditText edtroll;
    private TextView txtresp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = findViewById(R.id.submit);
        edtname = findViewById(R.id.name);
        edtroll = findViewById(R.id.roll);
        txtresp = findViewById(R.id.response);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty
                        (edtname.getText().toString())
                        ||TextUtils.isEmpty(
                                edtroll.getText().toString())) {
                    Toast.makeText(context, "All fields are mandatory.", Toast.LENGTH_LONG).show();
                    return;
                }
                try {

                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void run() throws IOException{
        OkHttpClient client = new OkHttpClient();
        String name = edtname.getText().toString();
        String roll = edtroll.getText().toString();
        String postBody = "";

      //  https://docs.google.com/forms/d/e/1FAIpQLSdQ4l6B4p2w_F-bxB4yKI46gJ-hhbmpQAi_7fc3B3WKRafiHw/viewform?usp=pp_url&entry.1470004185=dhanya&entry.301821020=001
        //  https://docs.google.com/forms/d/e/1FAIpQLSdQ4l6B4p2w_F-bxB4yKI46gJ-hhbmpQAi_7fc3B3WKRafiHw/formResponse&entry.1470004185=dhanya&entry.301821020=001
        //Create the request body with the help of Media Type
       // RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
        RequestBody formBody = new FormBody.Builder()
                .add("entry.1470004185", name)
                .add("entry.301821020", roll)
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();
        Log.d("MainActivity",request+"");
        Log.d("MainActivity",postBody+"");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity","Call Failed");
                txtresp.setText(e.getLocalizedMessage());
                e.printStackTrace();
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                Log.d("MainActivity",
                        response.code()+"");
                //Log.d("MainActivity","Post Response : \n "+myResponse);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     //  txtresp.setText(myResponse);
                    }
                });

            }
        });

    }
}