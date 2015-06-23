package howgoyeah.game;

import howgoyeah.exampleGame.GameOverMode;
import howgoyeah.exampleGame.OneMode;
import howgoyeah.exampleGame.TwoMode;
import howgoyeah.howgo.TouchActivity;
import howgoyeah.look.LookActivity;
import howgoyeah.main.MainActivity;
import howgoyeah.shake.ShakeActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.example.howgoyeah.R;
import com.example.howgoyeah.R.layout;
import com.example.howgoyeah.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		timer.schedule(timerTaskGame, 0,gasGame);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode , Intent data) {

	    if (requestCode == 4) {
	        if(resultCode == RESULT_OK){
	            String result= data.getStringExtra("result");
	            Log.v("touch_grade2", result);
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    
	    }else if(requestCode==2){
	        if(resultCode == RESULT_OK){
	            String result= data.getStringExtra("result");
	            Log.v("lookscore", result);
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }else if(requestCode==3){
	        if(resultCode == RESULT_OK){
	            String result= String.valueOf(ShakeActivity.condition);
	            Log.v("lookscore", result);
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}//onActivityResult
	
	private TimerTask  timerTaskGame = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			switch (mode) {
			case 1:
				gameController.setCurrentModeGame(new OneMode());
				Log.v("setOneMode","oneMode");
				mode+=1;
				break;
			case 2:
				gameController.setCurrentModeGame(new TwoMode());
				
				Log.v("set2","2Mode");
				//Bundle sendscore = getIntent().getExtras();
				//int lookscore = sendscore.getInt("lookscore");
				//Log.v("lookscore",Integer.toString(lookscore));
				//Toast.makeText(CanvasActivity.this,""+lookscore+"", Toast.LENGTH_SHORT).show();
				mode+=1;
				Intent lookscore = new Intent();
				lookscore.setClass(CanvasActivity.this, LookActivity.class);
				startActivityForResult(lookscore, 2);
				break;
			case 3:
				Log.v("set3","3Mode");
				
//				int shake_times = ShakeActivity.condition;
//				Log.v(String.valueOf(shake_times), "shake_times");
				mode+=1;
				
				Intent shakescore = new Intent();
				shakescore.setClass(CanvasActivity.this, ShakeActivity.class);
				startActivityForResult(shakescore, 3);
				break;
			case 4:
				Log.v("set4","4Mode");
				mode+=1;
				Intent touch = new Intent();
				touch.setClass(CanvasActivity.this, TouchActivity.class);
				startActivityForResult(touch, 4);
				
				break;
			case 5:
				gameController.setCurrentModeGame(new GameOverMode());
				Log.v("GameOver","GameOver");
				timer.cancel();
				break;
			default:
				
				break;
			}
			
			gameController.sendHandlerMessage(new Message());
		}
	};

}
