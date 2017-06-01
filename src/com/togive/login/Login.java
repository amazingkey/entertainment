package com.togive.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.togive.gift.MainActivity;
import com.togive.gift.R;

public class Login extends Activity {
	private EditText editTextx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.logina);
		editTextx = (EditText) this.findViewById(R.id.editText1);

	}

	public void login(View v) {
		SharedPreferences sharedPreferences = getSharedPreferences("mima",
				Activity.MODE_PRIVATE); // Ë½ÓÐÊý¾Ý
		String pwd = sharedPreferences.getString("pwd", "");
		if (pwd == null && editTextx.getText().toString() == "fff") {
			Intent intent = new Intent();
			intent.setClass(Login.this, MainActivity.class);
			startActivity(intent);
		} else if (pwd.equals(editTextx.getText().toString())) {
			Intent intent = new Intent();
			intent.setClass(Login.this, MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(Login.this, "µÇÂ¼Ê§°Ü£¬ÇëºË¶ÔÕËºÅÃÜÂë£¡", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void setting(View view) {
		Intent intent = new Intent();
		intent.setClass(Login.this, Oldpasswordtest.class);
		startActivity(intent);
	}

	public void exit(View v) {
		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		home.addCategory(Intent.CATEGORY_HOME);
		startActivity(home);
		System.exit(0);
	}

}
