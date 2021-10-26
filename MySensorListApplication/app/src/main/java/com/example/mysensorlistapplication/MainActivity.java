package com.example.mysensorlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // The sensor manager is a system service that lets you access the device sensors.

        mSensorManager = (SensorManager) getSystemService
                (Context.SENSOR_SERVICE);

        //The Sensor.TYPE_ALL constant indicates all the available sensors.
        List<Sensor> sensorList  =  mSensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList ) {
            sensorText.append(currentSensor.getName()).append(
                    System.getProperty("line.separator"));


            TextView sensorTextView = findViewById
                    (R.id.sensor_list);
            sensorTextView.setText(sensorText);
        }
    }

}
