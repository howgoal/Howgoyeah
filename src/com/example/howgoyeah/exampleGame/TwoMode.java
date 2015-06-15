package com.example.howgoyeah.exampleGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.howgoyeah.game.GameController;
import com.example.howgoyeah.game.ModeGame;

public class TwoMode extends ModeGame{
	Paint paint;
	GameController gameController;
	int x,y=0;
	public TwoMode() {
		// TODO Auto-generated constructor stub
		gameController = GameController.getInstance();
		
		paint = new Paint();
		
		paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        y=10;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void OnDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawText("twoMode Example", x, y, paint);
	}

}
