package com.example.sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

public class Proximity_Sensor extends AppCompatActivity  implements SensorEventListener{
    private TextView xtext;
    private Sensor mySensor;
    private SensorManager SM;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity__sensor);
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor=SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        xtext=(TextView)findViewById(R.id.txt_3);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int)(sensorEvent.values[0]);
        xtext.setText("X: " + x);
     if(x<=10)
     {
    vibrator.vibrate(500);
     }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume()
    {
        //restart listening again to the listener when the app is resumed or loaded again from RAM
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }

    @Override
    protected void onPause() {

        //pause listening to sensor values when the app is closed, but it's still in RAM
        super.onPause();
        SM.unregisterListener(this);
    }
}
