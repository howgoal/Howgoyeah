package howgoyeah.look;

import howgoyeah.game.CanvasActivity;
import howgoyeah.howgo.TouchActivity;

import com.example.howgoyeah.R;

import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LookActivity extends Activity {
	
	// ¬õ0 ¾í1 ¶À2 ºñ3 ÂÅ4 µµ5 ¶Â6 ¥Õ7 ¦Ç8 ´Ä9		­I´º0  ¦rªºÃC¦â1  ¦rªº·N«ä2
	
	int question[][] = {
			{3,2,5},{6,7,8},{4,7,2},{5,9,0},{1,6,3}	
	};
	
	int answer = 100;
	int guest = 101;
	int correct = 0;
	
	TextView timerview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_look);
		
		/*ImageView QuesPicture = (ImageView) findViewById(R.id.QuesPicture);
        QuesPicture.setImageResource(R.drawable.look_three);*/
        
        ImageButton button_red = (ImageButton) findViewById(R.id.button_red);
        ImageButton button_orange = (ImageButton) findViewById(R.id.button_orange);
        ImageButton button_yellow = (ImageButton) findViewById(R.id.button_yellow);
        ImageButton button_green = (ImageButton) findViewById(R.id.button_green);
        ImageButton button_blue = (ImageButton) findViewById(R.id.button_blue);
        ImageButton button_purple = (ImageButton) findViewById(R.id.button_purple);
        ImageButton button_black = (ImageButton) findViewById(R.id.button_black);
        ImageButton button_white = (ImageButton) findViewById(R.id.button_white);
        ImageButton button_gray = (ImageButton) findViewById(R.id.button_gray);
        ImageButton button_brown = (ImageButton) findViewById(R.id.button_brown);
        
        button_red.setOnClickListener(blistener);
        button_orange.setOnClickListener(blistener);
        button_yellow.setOnClickListener(blistener);
        button_green.setOnClickListener(blistener);
        button_blue.setOnClickListener(blistener);
        button_purple.setOnClickListener(blistener);
        button_black.setOnClickListener(blistener);
        button_white.setOnClickListener(blistener);
        button_gray.setOnClickListener(blistener);
        button_brown.setOnClickListener(blistener);
        
        setquestion();
        
        timerview = (TextView)findViewById(R.id.timerview);
        new CountDownTimer(5000,1000){

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				timerview.setText("seconds remaining:"+millisUntilFinished/1000);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
				Intent returnIntent = new Intent();
            	returnIntent.putExtra("result",Integer.toString(correct));
            	//Log.v("touch_grade1", Integer.toString(touch_number_count));
            	setResult(RESULT_OK,returnIntent);
				
				LookActivity.this.finish();
			}
        	
        }.start();
        
	}
	
	private OnClickListener blistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button_red:
				guest = 0;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_orange:
				guest = 1;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_yellow:
				guest = 2;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_green:
				guest = 3;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_blue:
				guest = 4;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_purple:
				guest = 5;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_black:
				guest = 6;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_white:
				guest = 7;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_gray:
				guest = 8;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
			case R.id.button_brown:
				guest = 9;
				if(answer == guest){
					correct ++;
					setquestion();
				}
				break;
				default:
					
				break;
			}
		}
	};
	
	private void setquestion() {
		int ques_picture = (int)(Math.random()* 5);
		int ques_choice = (int)(Math.random()* 3);
		
		
		ImageView QuesPicture = (ImageView) findViewById(R.id.QuesPicture);
		ImageView QuesChoice = (ImageView) findViewById(R.id.QuesChoice);
		TextView countcorrect = (TextView) findViewById(R.id.countcorrect);
        countcorrect.setText(""+correct+"");
		
		switch (ques_picture) {
		case 0:
	        QuesPicture.setImageResource(R.drawable.look_zero);
			break;
		case 1:
			QuesPicture.setImageResource(R.drawable.look_one);
			break;
		case 2:
			QuesPicture.setImageResource(R.drawable.look_two);
			break;
		case 3:
			QuesPicture.setImageResource(R.drawable.look_three);
			break;
		case 4:
			QuesPicture.setImageResource(R.drawable.look_four);
			break;

		default:
			break;
		}
		
		switch (ques_choice) {
		case 0:
	        QuesChoice.setImageResource(R.drawable.backcolor);
			break;
		case 1:
			QuesChoice.setImageResource(R.drawable.textcolor);
			break;
		case 2:
			QuesChoice.setImageResource(R.drawable.textmean);
			break;

		default:
			break;
		}

		answer = question[ques_picture][ques_choice];
		

		Toast.makeText(LookActivity.this,"Yeah"+ques_picture+"aaa"+ques_choice+"ha"+answer, Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
}
