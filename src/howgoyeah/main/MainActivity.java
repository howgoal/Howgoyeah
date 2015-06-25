package howgoyeah.main;

import howgoyeah.game.CanvasActivity;
import howgoyeah.howgo.TouchActivity;
import howgoyeah.look.LookActivity;
import howgoyeah.media.MediaPlayerService;
import howgoyeah.media.RankActivity;
import howgoyeah.shake.ShakeActivity;
import howgoyeah.slide.SlideActivity;

import com.example.howgoyeah.R;

import android.R.menu;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements Button.OnClickListener {
	ImageButton ray;
	ImageButton outxian;
	ImageButton sin;
	ImageButton howgo;
	ImageButton allGame;
	ImageButton game_rank; 
	private SoundPool soundPool;
	private int soundId;
	private MediaPlayerService mediaPlayerService;
	private Intent intent;
 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mains);
		ray = (ImageButton) findViewById(R.id.ray);
		outxian = (ImageButton) findViewById(R.id.outxian);
		sin = (ImageButton) findViewById(R.id.sin);
		howgo = (ImageButton) findViewById(R.id.howgo);
		allGame = (ImageButton) findViewById(R.id.allGame);
		game_rank = (ImageButton) findViewById(R.id.game_rank);
		ray.setOnClickListener(this);
		outxian.setOnClickListener(this);
		sin.setOnClickListener(this);
		howgo.setOnClickListener(this);
		allGame.setOnClickListener(this);
		game_rank.setOnClickListener(this);

		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
		soundId = soundPool.load(this, R.raw.background_music, 1);
		soundPool.play(soundId, 1.0F, 1.0F, 0, 0, 1.0F);
		intent = new Intent();
		mediaPlayerService = new MediaPlayerService();
		intent.setClass(getApplicationContext(), mediaPlayerService.getClass());
		startService(intent);
	
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopService(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.allGame:
			Log.v("allGame", "allGame block");
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, CanvasActivity.class);
			startActivity(intent);
			
			break;
		case R.id.ray:
			Log.v("ray", "ray block");
			Intent intent_look = new Intent();
			intent_look.setClass(MainActivity.this, LookActivity.class);
			startActivity(intent_look);
			
			break;
		case R.id.outxian:
			Log.v("outxian", "outxian block");
			Intent intent_shake = new Intent();
			intent_shake.setClass(MainActivity.this, ShakeActivity.class);
			startActivity(intent_shake);
		
			break;
		case R.id.howgo:
			Log.v("howgo", "howgo block");
			Intent intent_howgo = new Intent();
			intent_howgo.setClass(MainActivity.this, TouchActivity.class);
			startActivity(intent_howgo);
			
			break;
		case R.id.sin:
			Log.v("sin", "sin block");
			Intent intent_slide = new Intent();
			intent_slide.setClass(MainActivity.this, SlideActivity.class);
			startActivity(intent_slide); 
			
			break;
		case R.id.game_rank:
			MainActivity.this.finish();

			break;
		default:
			break;
		}
	}

}
