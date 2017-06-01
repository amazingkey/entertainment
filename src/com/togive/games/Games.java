package com.togive.games;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.dodowaterfall.MainActivity;
import com.togive.fruit.WelActivity;
import com.togive.gift.R;

public class Games extends Activity implements View.OnClickListener {


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("MainMenu", "onCreate called");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainmenu);
		findViewById(R.id.picturewall).setOnClickListener(this);
		findViewById(R.id.lianliankan).setOnClickListener(this);
		findViewById(R.id.exit).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.picturewall: {
			Intent intent = new Intent(Games.this,
					MainActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.lianliankan: {
			Intent intent = new Intent(Games.this,
					WelActivity.class);
			startActivity(intent);
			break;
		}
		
		case R.id.exit:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v("MainMenu", "onPause called");
	
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("MainMenu", "onResume called");
		
	}

}