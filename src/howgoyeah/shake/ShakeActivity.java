package howgoyeah.shake;

import howgoyeah.howgo.TouchActivity;

import com.example.howgoyeah.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShakeActivity extends Activity {
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float mLastX;
	private float mLastY;
	private float mLastZ;
	private double mSpeed;
	private long mLastUpdateTime;

	// private TextView show;
	private int counter = 0;
	public static int condition = 0;
	public static boolean stopTimer = false;
	public static int tmp_seconds = 30;
	private Long startTime;
	public Long seconds;
	private Handler handler = new Handler();
	
	private TextView shake;
	private TextView time;
	private ImageView pika;	
	
	SoundPool soundPool;
	int soundId;

	private static final int SPEED_SHRESHOLD = 4000;

	private static final int UPTATE_INTERVAL_TIME = 70;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();

		ShakeCanvas canvas = new ShakeCanvas(this);
		//setContentView(canvas);
		setContentView(R.layout.activity_shake);

		
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);

		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		
		mSensorManager.registerListener(SensorListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);

		time = (TextView) findViewById(R.id.time_data);
		shake = (TextView) findViewById(R.id.shake_data);
		pika = (ImageView) findViewById(R.id.pika);
		

		// setTimer();
		startTime = System.currentTimeMillis();
		handler.postDelayed(updateTimer, 1000); // start timer
		
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
		soundId = soundPool.load(this, R.raw.chu, 1);
	}
	

	private Runnable updateTimer = new Runnable() {
		public void run() {
			Long spentTime = System.currentTimeMillis() - startTime;
			seconds = (spentTime / 1000) % 60;

			if (seconds > 30) {
				handler.removeCallbacks(updateTimer); // stop timer
//				new AlertDialog.Builder(ShakeActivity.this)
//			    .setTitle("休息�?�?")
//			    .setMessage("總�?��?��?�次?��?��: " + String.valueOf(condition) + "\n3秒�?�即?��??��?��?��?��?��?�戰??�~")
//			    .show();
//				if(seconds > 33) {
//					stopTimer = true;
//					ShakeActivity.this.finish();
//					Log.i("><", "123456");
//				}	
				Intent returnIntent = new Intent();
            	returnIntent.putExtra("result_shake",Integer.toString(condition));
            	//Log.v("touch_grade1", Integer.toString(touch_number_count));
            	setResult(RESULT_OK,returnIntent);
				ShakeActivity.this.finish();
			} else {
				tmp_seconds = 30 - Integer.parseInt(seconds.toString());
				time.setText(String.valueOf(tmp_seconds));
				shake.setText(String.valueOf(condition));
				// invalidate(); // do onDraw()
				stopTimer = false;
				checkImage();
				// Log.i("run", "><");
				handler.postDelayed(this, 1000);
				if(seconds % 5 == 1) {
					soundPool.play(soundId, 1.0F, 1.0F, 0, 0, 1.0F);
				}
			}
		}
	};
	
	private void checkImage() {
		if(condition < 50) {
			
		} else if(condition < 100) {
			pika.setImageResource(R.drawable.power_two);
		} else if(condition < 150) {
			pika.setImageResource(R.drawable.power_three);
		} else {
			pika.setImageResource(R.drawable.power_four);
		}
	}

	private SensorEventListener SensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent mSensorEvent) {

			long mCurrentUpdateTime = System.currentTimeMillis();

			long mTimeInterval = mCurrentUpdateTime - mLastUpdateTime;

			if (mTimeInterval < UPTATE_INTERVAL_TIME)
				return;

			mLastUpdateTime = mCurrentUpdateTime;

			float x = mSensorEvent.values[0];
			float y = mSensorEvent.values[1];
			float z = mSensorEvent.values[2];

			float mDeltaX = x - mLastX;
			float mDeltaY = y - mLastY;
			float mDeltaZ = z - mLastZ;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ
					* mDeltaZ)
					/ mTimeInterval * 10000;


			if (mSpeed >= SPEED_SHRESHOLD) {
				
				counter++;
				// Log.e(String.valueOf(counter), "shake");
				// show.setText(String.valueOf(counter));
				condition = counter;
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	
	protected void onDestroy() 
	{
	        super.onDestroy();
	        
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
