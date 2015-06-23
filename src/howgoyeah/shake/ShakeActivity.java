package howgoyeah.shake;

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
	private SensorManager mSensorManager; // é«”æ??(Sensor)ä½¿ç”¨ç®¡ç??
	private Sensor mSensor; // é«”æ??(Sensor)é¡åˆ¥
	private float mLastX; // xè»¸é?”æ??(Sensor)??ç§»
	private float mLastY; // yè»¸é?”æ??(Sensor)??ç§»
	private float mLastZ; // zè»¸é?”æ??(Sensor)??ç§»
	private double mSpeed; // ?”©??•å?›é?“æ•¸åº?
	private long mLastUpdateTime; // è§¸ç™¼??‚é??
	
	//private TextView show;
	private int counter = 0;
	public static int condition = 0;

	// ?”©??•å?›é?“æ•¸åº¦è¨­å®šå?? (?•¸?¼è?Šå¤§???”©??•è?Šå¤§??›ï?Œæ•¸?¼è?Šå?è?•è?•ç”©??•å³??ƒè§¸?™¼)
	private static final int SPEED_SHRESHOLD = 4000;

	// è§¸ç™¼??“é?”æ?‚é??
	private static final int UPTATE_INTERVAL_TIME = 70;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ShakeCanvas canvas = new ShakeCanvas(this);
		setContentView(canvas);
		//setContentView(R.layout.activity_shake);	

		// ??–å?—é?”æ??(Sensor)??å?™ä½¿?”¨æ¬Šé??
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);

		// ??–å?—æ?‹æ?ŸSensor????‹è¨­å®?
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// è¨»å?Šé?”æ??(Sensor)?”©??•è§¸?™¼Listener
		mSensorManager.registerListener(SensorListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);
		
		//show = (TextView) findViewById(R.id.show);
	}
	

	private SensorEventListener SensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent mSensorEvent) {
			// ?•¶??è§¸?™¼??‚é??
			long mCurrentUpdateTime = System.currentTimeMillis();

			// è§¸ç™¼??“é?”æ?‚é?? = ?•¶??è§¸?™¼??‚é?? - ä¸Šæ¬¡è§¸ç™¼??‚é??
			long mTimeInterval = mCurrentUpdateTime - mLastUpdateTime;

			// ?‹¥è§¸ç™¼??“é?”æ?‚é??< 70 ??‡return;
			if (mTimeInterval < UPTATE_INTERVAL_TIME)
				return;

			mLastUpdateTime = mCurrentUpdateTime;

			// ??–å?—xyzé«”æ??(Sensor)??ç§»
			float x = mSensorEvent.values[0];
			float y = mSensorEvent.values[1];
			float z = mSensorEvent.values[2];

			// ?”©??•å?ç§»?Ÿåº¦ = xyzé«”æ??(Sensor)??ç§» - ä¸Šæ¬¡xyzé«”æ??(Sensor)??ç§»
			float mDeltaX = x - mLastX;
			float mDeltaY = y - mLastY;
			float mDeltaZ = z - mLastZ;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			// é«”æ??(Sensor)?”©??•å?›é?“é?Ÿåº¦?…¬å¼?
			mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ
					* mDeltaZ)
					/ mTimeInterval * 10000;

			// ?‹¥é«”æ??(Sensor)?”©??•é?Ÿåº¦å¤§æ–¼ç­‰æ–¼?”©??•è¨­å®šå?¼å?‡é?²å…¥ (??”åˆ°?”©??•å?›é?“å?Šé?Ÿåº¦)
			if (mSpeed >= SPEED_SHRESHOLD) {
				// ??”åˆ°??–ä???–ç”©??•å?Œè?å?šç?„ä?‹æ??
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
	        //?œ¨ç¨‹å?é?œé?‰æ?‚ç§»?™¤é«”æ??(Sensor)è§¸ç™¼
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
