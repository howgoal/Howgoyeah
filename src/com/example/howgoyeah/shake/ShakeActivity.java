package com.example.howgoyeah.shake;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;

import com.example.howgoyeah.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShakeActivity extends Activity{
	private SensorManager mSensorManager; // 擃��(Sensor)雿輻蝞∠��
	private Sensor mSensor; // 擃��(Sensor)憿
	private float mLastX; // x頠賊���(Sensor)��宏
	private float mLastY; // y頠賊���(Sensor)��宏
	private float mLastZ; // z頠賊���(Sensor)��宏
	private double mSpeed; // �����摨�
	private long mLastUpdateTime; // 閫貊����
	
	//private TextView show;
	private int counter = 0;
	public static int condition = 0;

	// �����摨西身摰�� (��潸�之������之����潸��������孛�)
	private static final int SPEED_SHRESHOLD = 4000;

	// 閫貊������
	private static final int UPTATE_INTERVAL_TIME = 70;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShakeCanvas canvas = new ShakeCanvas(this);
		setContentView(canvas);
		//setContentView(R.layout.activity_shake);	

		// ������(Sensor)���蝙�甈��
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);

		// �����ensor����身摰�
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// 閮餃����(Sensor)���孛�Listener
		mSensorManager.registerListener(SensorListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);
		
		//show = (TextView) findViewById(R.id.show);
	}
	

	private SensorEventListener SensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent mSensorEvent) {
			// ���孛�����
			long mCurrentUpdateTime = System.currentTimeMillis();

			// 閫貊������ = ���孛����� - 銝活閫貊����
			long mTimeInterval = mCurrentUpdateTime - mLastUpdateTime;

			// �閫貊������< 70 ��eturn;
			if (mTimeInterval < UPTATE_INTERVAL_TIME)
				return;

			mLastUpdateTime = mCurrentUpdateTime;

			// ���yz擃��(Sensor)��宏
			float x = mSensorEvent.values[0];
			float y = mSensorEvent.values[1];
			float z = mSensorEvent.values[2];

			// ����宏�漲 = xyz擃��(Sensor)��宏 - 銝活xyz擃��(Sensor)��宏
			float mDeltaX = x - mLastX;
			float mDeltaY = y - mLastY;
			float mDeltaZ = z - mLastZ;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			// 擃��(Sensor)������漲�撘�
			mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ
					* mDeltaZ)
					/ mTimeInterval * 10000;

			// �擃��(Sensor)����漲憭扳蝑���身摰�澆��脣 (���������漲)
			if (mSpeed >= SPEED_SHRESHOLD) {
				// ����������������
				counter++;
				//Log.e(String.valueOf(counter), "shake");
				//show.setText(String.valueOf(counter));
				condition = counter;
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	
	@Override
	protected void onDestroy() 
	{
	        super.onDestroy();
	        //�蝔����宏�擃��(Sensor)閫貊
	        mSensorManager.unregisterListener(SensorListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
