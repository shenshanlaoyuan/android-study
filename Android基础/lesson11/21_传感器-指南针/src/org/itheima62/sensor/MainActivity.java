package org.itheima62.sensor;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;

public class MainActivity extends Activity {

	private SensorManager manager;
	private Sensor sensor;
	private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// 1. 获取 传感器的数据
			float[] values = event.values;

			// value
			// values[0]: Azimuth, angle between the magnetic north direction
			// and the y-axis, around the z-axis (0 to 359). 0=North, 90=East,
			// 180=South, 270=West

			float angle = values[0];
			if (angle == 0) {
				System.out.println("北");
			} else if (angle == 90) {
				System.out.println("东");
			} else if (angle == 180) {
				System.out.println("南");
			} else if (angle == 270) {
				System.out.println("西");
			} else if (angle > 0 && angle < 90) {
				System.out.println("东北");
			} else if (angle > 90 && angle < 180) {
				System.out.println("东南");
			} else if (angle > 180 && angle < 270) {
				System.out.println("西南");
			} else if (angle > 270 && angle < 360) {
				System.out.println("西北");
			}

		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// 1. 传感器的精确度发送改变时

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 传感器的管理者

		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);
		// for (Sensor sensor : list) {
		// System.out.println(sensor.getName());
		// }

		// 获得光的传感器

		sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// 1:监听传感器
		// 2:哪个传感器
		// 3:采样频率
		manager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();

		manager.unregisterListener(listener);
	}
}
