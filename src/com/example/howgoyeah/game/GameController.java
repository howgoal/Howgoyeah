package com.example.howgoyeah.game;

import java.util.Timer;
import java.util.TimerTask;

import com.example.howgoyeah.exampleGame.GameOverMode;
import com.example.howgoyeah.exampleGame.OneMode;
import com.example.howgoyeah.exampleGame.TwoMode;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GameController {
	private Context context;
	private CanvasView canvasView;
	private static GameController gameControllerInstance = null;
	

	public static GameController getInstance() {
		if (gameControllerInstance == null) {
			gameControllerInstance = new GameController();
		}
		return gameControllerInstance;
	}

	private GameController() {
		// TODO Auto-generated constructor stub

	}
	
	public void initial(Context _context) {
		canvasView = new CanvasView(_context);
		context = _context;
	}
	
	public CanvasView getCanvasView() {
		return this.canvasView;
	}

	public Context getContext() {
		return this.context;
	}
	public void cavasInvalidate() {
		canvasView.invalidate();
	}
	
	public void setCurrentModeGame(ModeGame modeGame) {
		canvasView.setCurrentModeGame(modeGame);
	}
	
	public void sendHandlerMessage(Message mag) {
		canvasHandler.sendMessage(mag);
	}
	// set mode
	
	private Handler canvasHandler = new Handler(){
	    
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        canvasView.invalidate();
	    }  
	};
	

}
