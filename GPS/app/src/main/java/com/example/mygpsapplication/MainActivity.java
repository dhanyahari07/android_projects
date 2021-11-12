package com.example.mygpsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView txtLat;
    protected LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = findViewById(R.id.textview1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates
                (LocationManager.GPS_PROVIDER, 1000, 0, (LocationListener) this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        txtLat = findViewById(R.id.textview1);

        Log.d("MainActivity", "Latitude:" + location.getLatitude() + ", Longitude:" +
                location.getLongitude());

        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" +
                location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.d("Latitude",
                "enable");

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.d("Latitude",
                "disable");

    }
}