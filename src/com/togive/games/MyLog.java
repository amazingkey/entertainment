package com.togive.games;



import android.util.Log;

public class MyLog {
	private static final String TAG_GENERAL = "General";
	public static void v(String s)
	{
		Log.v(TAG_GENERAL, s);
	}
	public static void w(String s) {
		Log.w(TAG_GENERAL, s);
	}
}
