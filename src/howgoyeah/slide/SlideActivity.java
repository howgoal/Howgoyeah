package howgoyeah.slide;

import com.example.howgoyeah.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SlideActivity extends Activity {

	ScrollView scrollView;
	LinearLayout linearLayout;
	TextView textTime, textDis;
	SoundPool soundPool;
	int soundId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		// getActionBar().hide();
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		init();
		textDis = (TextView) findViewById(R.id.textDis);
		textTime = (TextView) findViewById(R.id.textTime);
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
		soundId = soundPool.load(this, R.raw.run, 1);
		new CountDownTimer(30000, 1000) {

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				textTime.setText("Done!");

				Intent returnIntent = new Intent();
				int x = scrollView.getScrollY();
				x = x / 5000;
				returnIntent.putExtra("result_slide", Integer.toString(x));
				// Log.v("touch_grade1", Integer.toString(touch_number_count));
				setResult(RESULT_OK, returnIntent);

				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				textTime.setText("時間倒數: " + millisUntilFinished / 1000);
			}

		}.start();
	}

	public void init() {
		for (int i = 0; i <= 2000; i++) {
			TextView text = new TextView(this);
			text.setText(String.valueOf(i * 5 + "M"));
			text.setMinHeight(500);
			text.setTextColor(Color.WHITE);
			text.setTextSize(30);
			linearLayout.addView(text);
		}
		scrollView.setOnTouchListener(scroll);
	}

	public OnTouchListener scroll = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			int x = scrollView.getScrollY();
			x = x / 5000;
			textDis.setText("目前分數: " + x);
			soundPool.play(soundId, 1.0F, 1.0F, 0, 0, 1.0F);
			return false;
		}
	};

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