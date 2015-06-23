package howgoyeah.slide;

import howgoyeah.game.CanvasView;

import com.example.howgoyeah.R;
import com.example.howgoyeah.R.id;
import com.example.howgoyeah.R.layout;
import com.example.howgoyeah.R.menu;

import android.R.integer;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.AutoCompleteTextView.Validator;

public class SlideActivity extends Activity {

	LinearLayout innerView;
	ScrollView scrollView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		init();
	}
	
	public void init(){
		innerView = (LinearLayout)findViewById(R.id.innerView);
		scrollView = (ScrollView)findViewById(R.id.scrollView);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			int x = scrollView.getScrollY();
			Toast.makeText(SlideActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
