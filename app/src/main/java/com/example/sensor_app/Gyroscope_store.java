package com.example.sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Gyroscope_store extends AppCompatActivity implements SensorEventListener {
    private TextView xGyro,yGyro,zGyro;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_store);
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        xGyro=(TextView)findViewById(R.id.xGyro);
        yGyro=(TextView)findViewById(R.id.yGyro);
        zGyro=(TextView)findViewById(R.id.zGyro);
        reff= FirebaseDatabase.getInstance().getReference().child("Member");

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        xGyro.setText("X: " + (int)x);
        yGyro.setText("Y: " + (int)y);
        zGyro.setText("Z: " + (int)z);
        if(y<0.00)
        {
            Member member=new Member();
            member.setX(x);
            member.setY(y);
            member.setZ(z);
            reff.push().setValue(member);
            Toast.makeText(Gyroscope_store.this,"DATA INSERTED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Gyroscope_store.this,MainActivity.class);
            startActivity(intent);


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }

}
