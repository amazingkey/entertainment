package com.togive.games;

import com.togive.gift.R;
import com.togive.login.Login;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.Window;

public class Picturewall extends Activity {
	
	private static final int SPLASH_TIME = 3500;
	@SuppressLint("HandlerLeak") private Handler splashHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == Constants.SPLASH_STOP_MSG_ID)
			{
		        MyLog.v("handle stop splash message");
				Picturewall.this.stopSplash();
				this.removeMessages(Constants.SPLASH_STOP_MSG_ID);
			}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loginicon);
        splashHandler.sendEmptyMessageDelayed(Constants.SPLASH_STOP_MSG_ID, Picturewall.SPLASH_TIME);
        MyLog.v("Send stop with delay");
    }
    
    private void stopSplash()
    {
		Intent intent = new Intent(Picturewall.this, Login.class);
		startActivity(intent);
		finish();
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
	        splashHandler.sendEmptyMessage(Constants.SPLASH_STOP_MSG_ID);
			return true;
		}
		return super.onTouchEvent(event);
	}

}