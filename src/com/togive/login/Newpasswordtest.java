package com.togive.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.togive.gift.R;

public class Newpasswordtest extends Activity {

	private EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);
        editText1 = (EditText)this.findViewById(R.id.editText1);
    }
    public void reset(View v){
    	SharedPreferences sharedPreferences = getSharedPreferences("mima", Context.MODE_PRIVATE); //˽������
        Editor editor = sharedPreferences.edit();//��ȡ�༭��
        editor.putString("pwd", editText1.getText().toString());
        editor.commit();//�ύ�޸�
        Intent intent = new Intent();
		intent.setClass(Newpasswordtest.this, Login.class);
		startActivity(intent);
    }

    
}
