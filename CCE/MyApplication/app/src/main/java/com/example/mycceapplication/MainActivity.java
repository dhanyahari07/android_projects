package com.example.mycceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){
            Sensor acceleroSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(acceleroSensor!=null){
                sensorManager.registerListener(this,acceleroSensor,SensorManager.SENSOR_DELAY_NORMAL);

            }
        }else {
            Toast.makeText(this, "Sensor not detecetd", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.sensorStatusTV)).setText("x : "+event.values[0]+"y"+event.values[1]+"z"+event.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}