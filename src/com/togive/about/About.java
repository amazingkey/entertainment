package com.togive.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.togive.gift.MainActivity;
import com.togive.gift.R;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
      
    }
    public void about(View v){
    	Intent i = new Intent(About.this,MainActivity.class);
    	startActivity(i);
    	finish();
    }
    
}
