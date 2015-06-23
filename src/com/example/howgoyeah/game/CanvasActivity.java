package com.example.howgoyeah.game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.example.howgoyeah.MainActivity;
import com.example.howgoyeah.R;
import com.example.howgoyeah.R.layout;
import com.example.howgoyeah.R.menu;
import com.example.howgoyeah.exampleGame.GameOverMode;
import com.example.howgoyeah.exampleGame.OneMode;
import com.example.howgoyeah.exampleGame.TwoMode;
import com.example.howgoyeah.howgo.TouchActivity;
import com.example.howgoyeah.shake.ShakeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CanvasActivity extends Activity {
	LinearLayout layout;
	CanvasView canvasView;
	GameController gameController;

	private Timer timer;
	private int gasGame = 3000;
	private int mode = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		layout = new LinearLayout(this);
		setContentView(layout);
		// get canvasView memory address
		gameController = GameController.getInstance();
		gameController.initial(getApplicationContext());

		layout.addView(gameController.getCanvasView());

		timer = new Timer();
		timer.schedule(timerTaskGame, 0, gasGame);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	private TimerTask timerTaskGame = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			switch (mode) {
			case 1:
				// Intent intent_shake = new Intent();
				// intent_shake.setClass(CanvasActivity.this,
				// ShakeActivity.class);
				// startActivity(intent_shake);
				Message msMessage = new Message();
				msMessage.what = 1;
				canvasHandler.sendMessage(msMessage);
				mode += 1;
				break;
			case 2:
				Message msMessage1 = new Message();
				msMessage1.what = 2;
				canvasHandler.sendMessage(msMessage1);
				mode += 1;
				break;
			case 3:
				Log.v("set3", "3Mode");
				Message msMessage3 = new Message();
				msMessage3.what = 3;
				canvasHandler.sendMessage(msMessage3);
				mode += 1;
				break;
			case 4:
				Log.v("set4", "4Mode");
				Message msMessage4 = new Message();
				msMessage4.what = 4;
				canvasHandler.sendMessage(msMessage4);
				mode += 1;
				break;
			case 5:

				timer.cancel();
				break;
			default:

				break;
			}

			gameController.sendHandlerMessage(new Message());
		}
	};
	private Handler canvasHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				setContentView(R.layout.activity_main);
				break;
			case 2:
				setContentView(R.layout.activity_touch);
				break;
			case 3:
				setContentView(R.layout.activity_main);
				break;
			case 4:
				setContentView(R.layout.activity_touch);
				break;
			default:
				break;
			}

		}
	};

}
