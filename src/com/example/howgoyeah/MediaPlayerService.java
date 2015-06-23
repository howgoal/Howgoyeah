package com.example.howgoyeah;

import android.R.bool;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.MediaController.MediaPlayerControl;
import android.media.*;


public class MediaPlayerService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener,
		AudioManager.OnAudioFocusChangeListener {

	private MediaPlayer mMediaPlayer = null;
	private boolean isRepeat = true;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mMediaPlayer = MediaPlayer.create(this, R.raw.background_music);
		
		mMediaPlayer.setLooping(true);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		mMediaPlayer.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMediaPlayer.stop();
		mMediaPlayer.release();
	}

	public MediaPlayerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if (isRepeat == false) {
			mMediaPlayer.setLooping(false);
			mMediaPlayer.stop();
			
		}
	}

	@Override
	public void onAudioFocusChange(int focusChange) {
		// TODO Auto-generated method stub

	}

	public void setBackgroundMusicFalse() {
		isRepeat = false;
	}

}
