package com.example.sensor_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

public class Shake_1 extends AppCompatActivity implements ShakeDetector.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_1);
        SensorManager SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector SD = new ShakeDetector(this);
        SD.start(SM);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void hearShake() {
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        String emgNumber = "12345";
        intentCall.setData(Uri.parse("tel:" + emgNumber));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED) {
            //if permission is not granted, requests for permission
            ActivityCompat.requestPermissions(Shake_1.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            //After granting permission checks again and makes the call
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),"Calling the Emergency Number", Toast.LENGTH_LONG).show();
                startActivity(intentCall);
            }
        }
        else{
            //if permission is granted, then makes the call
            Toast.makeText(getApplicationContext(),"Calling the Emergency Number", Toast.LENGTH_LONG).show();
            startActivity(intentCall);
        }

    }
}
