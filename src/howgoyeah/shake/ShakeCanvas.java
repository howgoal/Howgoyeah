package howgoyeah.shake;

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

	//private int gasGame = 500;
	private int mode = 0;
	private Paint paint;

	public ShakeCanvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		paint = new Paint();
		paint.setAntiAlias(true); // remove edge effect	
	}
	

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
		Bitmap back = BitmapFactory.decodeResource(res, R.drawable.orange_back);
		
		canvas.drawBitmap(back, 0, 0, paint);

		if (mode < 50) {
			canvas.drawBitmap(power1, canvas.getWidth()/2-160, canvas.getHeight()/10, paint);
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
		
		Paint notePaint = new Paint();
		notePaint.setColor(Color.BLACK);
		notePaint.setTextSize(20);
		
		canvas.drawText("Passing time: " + String.valueOf(ShakeActivity.tmp_seconds), 70, 400, counterPaint);
		canvas.drawText("Shake times: " + String.valueOf(mode), 70, 500, counterPaint);
		canvas.drawText("快來幫皮卡丘充飽電><", 30, 50, notePaint);
		
		
		//Log.i("second", String.valueOf(seconds));
		
		if(ShakeActivity.stopTimer == false) {
			invalidate();
		}
	}

}
