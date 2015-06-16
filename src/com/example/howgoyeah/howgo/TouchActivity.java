package com.example.howgoyeah.howgo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.howgoyeah.R;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TouchActivity extends Activity {

	ImageButton button1, button2, button3, button4, button5, button6, button7,
			button8, button9, button0;

	TextView phone_number;
	StringBuffer sb = new StringBuffer("Test");
	private Timer timer;
	private int gasGame = 500;

	ArrayList<Integer> number = new ArrayList<Integer>();
	String total = "";

	Random randomGenerator = new Random();
	Random rand = new Random(System.currentTimeMillis());
	int pos = (int)(rand.nextDouble()*10);

	int randomInt = randomGenerator.nextInt();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);
		init();
		number.add(3);
		number.add(9);
		number.add(6);

		timer = new Timer();
		timer.schedule(timerTaskGame, 0, gasGame);
	}

	private TimerTask timerTaskGame = new TimerTask() {

		@Override
		public void run() {
			Random rand = new Random(System.currentTimeMillis());
			int pos = (int)(rand.nextDouble()*10);
			number.add(pos);
			canvasHandler.sendMessage(new Message());
			// TODO Auto-generated method stub

		}

	};
	private Handler canvasHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			phone_number.setText(create_string());
		}
	};

	public String create_string() {
		total = "";
		for (int i = 0; i < number.size(); i++) {
			total += number.get(i);
		}
		return total;
	}

	public void check(int press) {
		if (press == number.get(0)) {
			number.remove(0);
			// return true;
		} else {
			// return false;
		}
	}

	public void init() {
		button1 = (ImageButton) findViewById(R.id.imageButton1);
		button2 = (ImageButton) findViewById(R.id.imageButton2);
		button3 = (ImageButton) findViewById(R.id.imageButton3);
		button4 = (ImageButton) findViewById(R.id.imageButton4);
		button5 = (ImageButton) findViewById(R.id.imageButton5);
		button6 = (ImageButton) findViewById(R.id.imageButton6);
		button7 = (ImageButton) findViewById(R.id.imageButton7);
		button8 = (ImageButton) findViewById(R.id.imageButton8);
		button9 = (ImageButton) findViewById(R.id.imageButton9);
		button0 = (ImageButton) findViewById(R.id.imageButton0);

		phone_number = (TextView) findViewById(R.id.show_phone_number);

		button1.setOnClickListener(btnListener);
		button2.setOnClickListener(btnListener);
		button3.setOnClickListener(btnListener);
		button4.setOnClickListener(btnListener);
		button5.setOnClickListener(btnListener);
		button6.setOnClickListener(btnListener);
		button7.setOnClickListener(btnListener);
		button8.setOnClickListener(btnListener);
		button9.setOnClickListener(btnListener);
		button0.setOnClickListener(btnListener);

	}

	private OnClickListener btnListener = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imageButton1:
				// phone_number.setText("1");
				// sb.append(String.valueOf(randomInt));
				check(1);
				// phone_number.setText(create_string());
				// /phone_number.setVisibility(View.VISIBLE);
				break;
			case R.id.imageButton2:
				check(2);
				// phone_number.setVisibility(View.VISIBLE);
				// phone_number.setVisibility(View.VISIBLE);
				break;
			case R.id.imageButton3:
				check(3);
				// phone_number.setVisibility(View.VISIBLE);
				// phone_number.setVisibility(View.VISIBLE);
				break;
			case R.id.imageButton4:
				check(4);
				break;
			case R.id.imageButton5:
				check(5);
				break;
			case R.id.imageButton6:
				check(6);
				break;
			case R.id.imageButton7:
				check(7);
				break;
			case R.id.imageButton8:
				check(8);
				break;
			case R.id.imageButton9:
				check(9);
				break;
			case R.id.imageButton0:
				check(0);
				break;
			}
		}
	};

}
