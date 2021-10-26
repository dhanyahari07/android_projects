package com.example.mylighttempproximityapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements
        SensorEventListener {

    // System sensor manager instance.
    private SensorManager mSensorManager;
    // Proximity and light sensors, as retrieved from the sensor manager.
    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorTemp;

    // TextViews to display current sensor values.
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all view variables.
        mTextSensorLight = (TextView) findViewById(R.id.label_light);
        mTextSensorTemp = (TextView) findViewById(R.id.label_temp);
        mTextSensorProximity = (TextView) findViewById (R.id.label_proximity);
        // Get an instance of the sensor manager.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Get light, temp and proximity sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor is not available on the device.
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor (Sensor.TYPE_LIGHT);
        mSensorTemp = mSensorManager.getDefaultSensor (Sensor.TYPE_AMBIENT_TEMPERATURE);
        // Get the error message from string resources.
        String sensor_error = getResources().getString(R.string.error_no_sensor);
        // If either mSensorLight or mSensorProximity are null, those sensors are not available in the device.  Set the text to the error message
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorTemp == null) {
            mTextSensorTemp.setText(sensor_error);
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onPause().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL)
        if (mSensorProximity != null) {
            mSensorManager.registerListener((SensorEventListener) this,mSensorProximity,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener((SensorEventListener) this, mSensorLight,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorTemp != null) {
            mSensorManager.registerListener((SensorEventListener) this, mSensorTemp,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener((SensorEventListener) this);

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // The sensor type (as defined in the Sensor class).
        int sensorType = event.sensor.getType();
        // The new data value of the sensor.  The light, temp and proximity
        // sensors report one value at a time, which is always the first
        // element in the values array.
        float currentValue = event.values[0];
        switch (sensorType) {
        // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
            // Set the light sensor text view to the light sensor string
            // from the resources, with the placeholder filled in.
                mTextSensorLight.setText(getResources().
                        getString(R.string.label_light,
                                currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
            // Set the proximity sensor text view to the light sensor
            // string from the resources, with the placeholder filled in.
                mTextSensorProximity.setText(getResources().
                        getString(R.string.label_proximity,
                                currentValue));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            // Set the proximity sensor text view to the light sensor
            // string from the resources, with the placeholder filled in.
                mTextSensorTemp.setText(getResources().
                        getString(R.string.label_temp,
                                currentValue));
                break;
            default:
// do nothing
        }


    }
}