package com.togive.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.togive.gift.R;
public class Oldpasswordtest extends Activity {
	private EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.oldtest);
        editText1 = (EditText)findViewById(R.id.editText1);
    }
    public void oldtest(View view){
    	SharedPreferences sharedPreferences = getSharedPreferences("mima", Activity.MODE_PRIVATE); //Ë½ÓÐÊý¾Ý
        String pwd = sharedPreferences.getString("pwd", "");
        if("you are angel".equals(editText1.getText().toString())){
        	Intent intent = new Intent();
			intent.setClass(Oldpasswordtest.this,Newpasswordtest.class);
			startActivity(intent);
        }
    	if(pwd.equals(editText1.getText().toString())){
			Intent intent = new Intent();
			intent.setClass(Oldpasswordtest.this,Newpasswordtest.class);
			startActivity(intent);
    	}else{
    		Toast.makeText(Oldpasswordtest.this, "µÇÂ¼Ê§°Ü£¬ÇëºË¶ÔÕËºÅÃÜÂë£¡",
				Toast.LENGTH_LONG).show();
	}
}
    
}

