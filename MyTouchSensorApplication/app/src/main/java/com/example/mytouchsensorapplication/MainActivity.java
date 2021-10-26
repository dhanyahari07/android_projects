package com.example.mytouchsensorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  View.OnTouchListener{

    private String TAG = "MainActivity";
    float initialX, initialY;
    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cl = findViewById(R.id.clayout);
        cl.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            initialX = event.getX();
            initialY = event.getY();
            Log.d(TAG, "initial " + initialX + "" + initialY);
            // Toast.makeText(this,""+initialX+""+initialY,Toast.LENGTH_SHORT).show();
        }
        if (action == MotionEvent.ACTION_UP) {
            float finalX = event.getX();
            float finalY = event.getY();
            Log.d(TAG, "final" + finalX + "" + finalY);
            // Toast.makeText(this,""+finalX+""+finalY,Toast.LENGTH_SHORT).show();
            if (initialX < finalX) {
                Log.d(TAG, "Left to Right swipe performed");
            }
            if (initialX > finalX) {
                Log.d(TAG, "Right to Left swipe performed");
            }
            if (initialY < finalY) {
                Log.d(TAG, "Up to Down swipe performed");
            }
            if (initialY > finalY) {
                Log.d(TAG, "Down to Up swipe performed");
            }
        }
        if (action == MotionEvent.ACTION_MOVE)
            Log.d(TAG, "Action was MOVE");
        if (action == MotionEvent.ACTION_CANCEL)
            Log.d(TAG, "Action was CANCEL");
        if (action == MotionEvent.ACTION_OUTSIDE)
            Log.d(TAG, "Movement occurred outside bounds of current screen element");
        // return super.onTouchEvent(event);
        return true;
    }


    }
