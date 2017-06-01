package com.togive.login;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class LineEditText extends EditText {
	private Paint paint;
	
	public LineEditText(Context context,AttributeSet attrs){
		super(context,attrs);
		paint = new Paint();
		//Ãè±ß²¢Ìî³ä
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setColor(Color.GRAY);
		//¿ªÆô¿¹¾â³Ý£¬½ÏºÄÄÚ´æ
		paint.setAntiAlias(true);
	}
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawLine(0,this.getHeight(), this.getWidth(), this.getHeight(),paint);
	}
}
