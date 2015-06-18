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
	private SensorManager mSensorManager; // 體感(Sensor)使用管理
	private Sensor mSensor; // 體感(Sensor)類別
	private float mLastX; // x軸體感(Sensor)偏移
	private float mLastY; // y軸體感(Sensor)偏移
	private float mLastZ; // z軸體感(Sensor)偏移
	private double mSpeed; // 甩動力道數度
	private long mLastUpdateTime; // 觸發時間
	
	//private TextView show;
	private int counter = 0;
	public static int condition = 0;

	// 甩動力道數度設定值 (數值越大需甩動越大力，數值越小輕輕甩動即會觸發)
	private static final int SPEED_SHRESHOLD = 4000;

	// 觸發間隔時間
	private static final int UPTATE_INTERVAL_TIME = 70;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ShakeCanvas canvas = new ShakeCanvas(this);
		setContentView(canvas);
		//setContentView(R.layout.activity_shake);	

		// 取得體感(Sensor)服務使用權限
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);

		// 取得手機Sensor狀態設定
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// 註冊體感(Sensor)甩動觸發Listener
		mSensorManager.registerListener(SensorListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);
		
		//show = (TextView) findViewById(R.id.show);
	}
	

	private SensorEventListener SensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent mSensorEvent) {
			// 當前觸發時間
			long mCurrentUpdateTime = System.currentTimeMillis();

			// 觸發間隔時間 = 當前觸發時間 - 上次觸發時間
			long mTimeInterval = mCurrentUpdateTime - mLastUpdateTime;

			// 若觸發間隔時間< 70 則return;
			if (mTimeInterval < UPTATE_INTERVAL_TIME)
				return;

			mLastUpdateTime = mCurrentUpdateTime;

			// 取得xyz體感(Sensor)偏移
			float x = mSensorEvent.values[0];
			float y = mSensorEvent.values[1];
			float z = mSensorEvent.values[2];

			// 甩動偏移速度 = xyz體感(Sensor)偏移 - 上次xyz體感(Sensor)偏移
			float mDeltaX = x - mLastX;
			float mDeltaY = y - mLastY;
			float mDeltaZ = z - mLastZ;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			// 體感(Sensor)甩動力道速度公式
			mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ
					* mDeltaZ)
					/ mTimeInterval * 10000;

			// 若體感(Sensor)甩動速度大於等於甩動設定值則進入 (達到甩動力道及速度)
			if (mSpeed >= SPEED_SHRESHOLD) {
				// 達到搖一搖甩動後要做的事情
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
	        //在程式關閉時移除體感(Sensor)觸發
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
