package howgoyeah.game;

import java.util.ArrayList;

import junit.framework.Test;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

	private ModeGame currentGameMode = null;
	Paint paint;
	private static CanvasView canvasViewInstance = null;

	public CanvasView(Context _context) {
		super(_context);

	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(currentGameMode != null) {
			currentGameMode.OnDraw(canvas);
		}
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(currentGameMode != null) {
			currentGameMode.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}	
	public void setCurrentModeGame(ModeGame modeGame) {
		currentGameMode = modeGame;
	}
	
	

}
