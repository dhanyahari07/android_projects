package com.example.mythreadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    private Button buttonStartThread;
    private Handler mainHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartThread=findViewById(R.id.button_start_thread);
    }
    public void  startThread(View v)
    {
      //  ExampleThread thread=new ExampleThread(10);
       // thread.start();
        ExampleRunnable runnable=new ExampleRunnable(10);
        new Thread(runnable).start();
    }
    public void stopThread(View v)
    {

    }
    class ExampleThread extends Thread{

        int seconds;
        ExampleThread(int seconds){
            this.seconds=seconds;
        }
        @Override
        public void run() {
            for(int i=0;i<seconds;i++)
            {
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class ExampleRunnable implements Runnable{
        int seconds;
        ExampleRunnable(int seconds){
            this.seconds=seconds;
        }

        @Override
        public void run() {

            for(int i=0;i<seconds;i++)
            {
                if(i==5)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                }
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}