package com.example.sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Accelerometer_2 extends AppCompatActivity implements SensorEventListener {
    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_2);
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        xText=(TextView)findViewById(R.id.xtext);
        yText=(TextView)findViewById(R.id.ytext);
        zText=(TextView)findViewById(R.id.ztext);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int)(sensorEvent.values[0]);
        int y = (int)(sensorEvent.values[1]);
        int z = (int)(sensorEvent.values[2]);

        //do what you want with the sensor values here. Perform some actions, change activity, call, toast etc.
        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);


        if(y==-9 || y==9)
        {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            Toast toast = Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT); toast.show();


        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
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
