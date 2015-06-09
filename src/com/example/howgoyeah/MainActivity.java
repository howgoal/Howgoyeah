package com.example.howgoyeah;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements Button.OnClickListener {
	Button ray ;
	Button outxian;
	Button sin;
	Button howgo;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ray = (Button) findViewById(R.id.ray);
		outxian = (Button) findViewById(R.id.outxian);
		sin = (Button) findViewById(R.id.sin);
		howgo = (Button) findViewById(R.id.howgo);
		
		ray.setOnClickListener(this);
		outxian.setOnClickListener(this);
		sin.setOnClickListener(this);
		howgo.setOnClickListener(this);
		
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ray:
			Log.v("ray", "ray block");
			break;
		case R.id.outxian:
			Log.v("outxian", "outxian block");
			break;
		case R.id.howgo:
			Log.v("howgo", "howgo block");
			break;
		case R.id.sin:
			Log.v("sin", "sin block");
			break;
		default:
			break;
		}
	}
}
