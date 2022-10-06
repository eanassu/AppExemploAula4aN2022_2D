package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class SensoresActivity extends AppCompatActivity {
    private EditText editTextNumberX;
    private EditText editTextNumberY;
    private EditText editTextNumberZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        editTextNumberX = findViewById(R.id.editTextNumberX);
        editTextNumberY = findViewById(R.id.editTextNumberY);
        editTextNumberZ = findViewById(R.id.editTextNumberZ);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for( Sensor sensor: lista ) {
            System.out.println(sensor.getName());
        }
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                editTextNumberX.setText(Float.toString(x));
                editTextNumberY.setText(Float.toString(y));
                editTextNumberZ.setText(Float.toString(z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {}
        },sensor,SensorManager.SENSOR_DELAY_NORMAL );

    }
}