package com.togive.truewords;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import beans.Cuns;
import data.MyDataBase;

import com.togive.gift.MainActivity;
import com.togive.gift.R;

public class Truewords extends Activity {

	EditText ed1, ed2;
	Button bt1;
	MyDataBase myDatabase;
	Cuns cun;
	int ids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.truewords);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		bt1 = (Button) findViewById(R.id.button1);
		myDatabase = new MyDataBase(this);

		Intent intent = this.getIntent();
		ids = intent.getIntExtra("ids", 0);
		// Ĭ��Ϊ0����Ϊ0,��Ϊ�޸�����ʱ��ת������
		if (ids != 0) {
			cun = myDatabase.getTiandCon(ids);
			ed1.setText(cun.getTitle());
			ed2.setText(cun.getContent());
		}
		// ���水ť�ĵ���¼������ͷ��ذ�ť��һ���Ĺ��ܣ����Զ�����isSave()������
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isSave();
			}
		});
	}

	/*
	 * ���ذ�ť���õķ�����
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
//		SimpleDateFormat formatter = new SimpleDateFormat(
//				"yyyy.MM.dd  HH:mm:ss");
//		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
//		String times = formatter.format(curDate);
//		String title = ed1.getText().toString();
//		String content = ed2.getText().toString();
//		// ��Ҫ�޸�����
//		if (ids != 0) {
//			cun = new Cuns(title, ids, content, times);
//			myDatabase.toUpdate(cun);
//			Intent intent = new Intent(Truewords.this, MainActivity.class);
//			startActivity(intent);
//			Truewords.this.finish();
//		}
//		// �½��ռ�
//		else {
//			if (title.equals("") && content.equals("")) {
//				Intent intent = new Intent(Truewords.this, MainActivity.class);
//				startActivity(intent);
//				Truewords.this.finish();
//			} else {
//				cun = new Cuns(title, content, times);
//				myDatabase.toInsert(cun);
//				Intent intent = new Intent(Truewords.this, MainActivity.class);
//				startActivity(intent);
//				Truewords.this.finish();
//			}
//
//		}
		System.exit(0);
	}

	@SuppressLint("SimpleDateFormat")
	private void isSave() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy.MM.dd  HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String times = formatter.format(curDate);
		String title = ed1.getText().toString();
		String content = ed2.getText().toString();
		// ��Ҫ�޸�����
		if (ids != 0) {
			cun = new Cuns(title, ids, content, times);
			myDatabase.toUpdate(cun);
			Intent intent = new Intent(Truewords.this, Truewordshome.class);
			startActivity(intent);
			Truewords.this.finish();
		}
		// �½��ռ�
		else {
			cun = new Cuns(title, content, times);
			myDatabase.toInsert(cun);
			Intent intent = new Intent(Truewords.this, Truewordshome.class);
			startActivity(intent);
			Truewords.this.finish();
		}
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.abouticon:
//			Intent intent = new Intent(Intent.ACTION_SEND);
//			intent.setType("text/plain");
//			intent.putExtra(Intent.EXTRA_TEXT, "���⣺" + ed1.getText().toString()
//					+ "    ���ݣ�" + ed2.getText().toString());
//			startActivity(intent);
//			break;
//
//		default:
//			break;
//		}
//		return false;
//	}

}