package howgoyeah.game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.SlidingDrawer.OnDrawerCloseListener;

public abstract class ModeGame {

	public ModeGame() {
		// TODO Auto-generated constructor stub
	}
	public abstract boolean onTouchEvent(MotionEvent event);
	public abstract void OnDraw(Canvas canvas);
}
