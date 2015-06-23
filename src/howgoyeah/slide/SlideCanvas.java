package howgoyeah.slide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class SlideCanvas extends View {

	
	public SlideCanvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint= new Paint();
		paint.setColor(Color.RED);
		canvas.drawLine(200, 200, 200, 8000, paint);
	}

}
