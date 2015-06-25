package howgoyeah.game;

import howgoyeah.exampleGame.GameOverMode;
import howgoyeah.exampleGame.OneMode;
import howgoyeah.exampleGame.TwoMode;
import howgoyeah.howgo.TouchActivity;
import howgoyeah.look.LookActivity;
import howgoyeah.main.MainActivity;
import howgoyeah.shake.ShakeActivity;
import howgoyeah.slide.SlideActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.example.howgoyeah.R;
import com.example.howgoyeah.R.layout;
import com.example.howgoyeah.R.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
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
	private int gasGame = 30000;
	private int mode = 1;
	private int point = 0;

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
		dialogShow();

	}

	public void dialogShow() {

		AlertDialog.Builder builder = new Builder(CanvasActivity.this);
		builder.setMessage("1.向前跑! \n2.睜大眼睛!! \n3.趕快搖!!! \n4.快撥電話!!!! ");
		builder.setTitle("提示");
		builder.setPositiveButton("Let's GO", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				timer = new Timer();
				timer.schedule(timerTaskGame, 0, gasGame);
			}
		});
		builder.create().show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String result_slide = data.getStringExtra("result_slide");
				point+= Integer.valueOf(result_slide);
				Log.v("result_slide", result_slide);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}

		} else if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				String result_look = data.getStringExtra("result_look");
				Log.v("result_look", result_look);
				point+= Integer.valueOf(result_look);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		} else if (requestCode == 3) {
			if (resultCode == RESULT_OK) {
				String result_shake = data.getStringExtra("result_shake");
				Log.v("result_shake", result_shake);
				point+= Integer.valueOf(result_shake);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		} else if (requestCode == 4) {
			if (resultCode == RESULT_OK) {
				String result_touch = data.getStringExtra("result_touch");
				Log.v("result_touch", result_touch);
				point+= Integer.valueOf(result_touch);
				canvasHandler.sendMessage(new Message());
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}
	}// onActivityResult

	private TimerTask timerTaskGame = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			switch (mode) {
			case 1:
				
				Log.v("setOneMode", "oneMode");
				mode += 1;
				Intent slidescore = new Intent();
				slidescore.setClass(CanvasActivity.this, SlideActivity.class);
				startActivityForResult(slidescore, 1);
				break;
			case 2:
				gameController.setCurrentModeGame(new TwoMode());

				Log.v("set2", "2Mode");
				// Bundle sendscore = getIntent().getExtras();
				// int lookscore = sendscore.getInt("lookscore");
				// Log.v("lookscore",Integer.toString(lookscore));
				// Toast.makeText(CanvasActivity.this,""+lookscore+"",
				// Toast.LENGTH_SHORT).show();
				mode += 1;
				Intent lookscore = new Intent();
				lookscore.setClass(CanvasActivity.this, LookActivity.class);
				startActivityForResult(lookscore, 2);
				break;
			case 3:
				Log.v("set3", "3Mode");

				// int shake_times = ShakeActivity.condition;
				// Log.v(String.valueOf(shake_times), "shake_times");
				mode += 1;

				Intent shakescore = new Intent();
				shakescore.setClass(CanvasActivity.this, ShakeActivity.class);
				startActivityForResult(shakescore, 3);
				break;
			case 4:
				Log.v("set4", "4Mode");
				mode += 1;
				Intent touch = new Intent();
				touch.setClass(CanvasActivity.this, TouchActivity.class);
				startActivityForResult(touch, 4);

				break;
			case 5:
				
				
				timer.cancel();
				break;
			default:

				break;
			}

		}
	};
	public void resultShow() {

		AlertDialog.Builder builder = new Builder(CanvasActivity.this);
		builder.setMessage("恭喜你獲得的分數為："+point);
		builder.setTitle("遊戲結束");
		builder.setPositiveButton("返回主選單", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				CanvasActivity.this.finish();
				
			}
		});
		builder.create().show();

	}
	private Handler canvasHandler = new Handler(){
	    
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        resultShow();
	    }  
	};
}
