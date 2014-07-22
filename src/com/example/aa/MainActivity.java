package com.example.aa;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends Activity {

	private SensorManager sensorManager= null;
	private Sensor acc =null;
	private TextView t1;
	private TextView t2;
	private TextView t3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		t1 = (TextView) findViewById(R.id.text);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	final SensorEventListener mySensorListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
						if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
							return;
			}

			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			t1.setText("X: "+x);
			t2.setText("Y: "+y);
			t3.setText("Z: "+z);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(mySensorListener, acc, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(mySensorListener, acc);
	}

}
