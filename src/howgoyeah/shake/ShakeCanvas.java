package howgoyeah.shake;

import java.util.Timer;
import java.util.TimerTask;

import com.example.howgoyeah.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class ShakeCanvas extends View {

	private Long startTime;
	public Long seconds;
	private Handler handler = new Handler();
	//private int gasGame = 500;
	private int mode = 0;
	private Paint paint;

	public ShakeCanvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		paint = new Paint();
		paint.setAntiAlias(true); // remove edge effect
		
		//setTimer();
		startTime = System.currentTimeMillis();
		handler.postDelayed(updateTimer, 1000); // start timer
	}

	
	private Runnable updateTimer = new Runnable() {
        public void run() {
            Long spentTime = System.currentTimeMillis() - startTime;
            //θ¨η?—η›®??ε·²??η?’ζ•Έ
            seconds = (spentTime/1000) % 60;
            
            if(seconds > 30) {
            	handler.removeCallbacks(updateTimer); // stop timer
            } else {
            	invalidate(); // do onDraw()
                //Log.i("run", "><");
                handler.postDelayed(this, 1000);
            } 
        }
    };
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated constructor stub
		super.onDraw(canvas);

		mode = ShakeActivity.condition;
		//Log.i("mode", String.valueOf(mode));

		Resources res = getResources();
		Bitmap power1 = BitmapFactory.decodeResource(res, R.drawable.power_one);
		Bitmap power2 = BitmapFactory.decodeResource(res, R.drawable.power_two);
		Bitmap power3 = BitmapFactory.decodeResource(res,
				R.drawable.power_three);
		Bitmap power4 = BitmapFactory
				.decodeResource(res, R.drawable.power_four);

		if (mode < 50) {
			canvas.drawBitmap(power1, 0, 0, paint);
		} else if (mode < 100) {
			canvas.drawBitmap(power2, 0, 0, paint);

		} else if (mode < 150) {
			canvas.drawBitmap(power3, 0, 0, paint);
		} else {
			canvas.drawBitmap(power4, 0, 0, paint);
		}
		
		Paint counterPaint = new Paint();
		counterPaint.setColor(Color.BLACK);
		counterPaint.setTextSize(50);
		
		canvas.drawText("η¶“ι?ζ?‚ι??: " + String.valueOf(seconds), 60, 400, counterPaint);
		canvas.drawText("??–ε?•ζ¬΅?•Έ: " + String.valueOf(mode), 60, 500, counterPaint);
		//Log.i("second", String.valueOf(seconds));

	}

}
