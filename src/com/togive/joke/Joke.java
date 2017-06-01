package com.togive.joke;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.togive.gift.MainActivity;
import com.togive.gift.R;


public class Joke extends Activity {
	private ViewPager advPager = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.joke);
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		initViewPager();
	}   
	public void lfy(View view){
		advPager.arrowScroll(1);
	}
	public void glp(View view){
		advPager.arrowScroll(2);
	}
	public void tj(View view){
		Intent joke = new Intent(this,MainActivity.class);
		startActivity(joke);
		System.exit(0);
	}
	private void initViewPager() {
		//往集合中添加需要加载的图片资源
	    List<View> advPics = new ArrayList<View>();
		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.ainia);
		advPics.add(img1);   
		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.ainib);
		advPics.add(img2);
		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.ainic);
		advPics.add(img3);
		ImageView img4 = new ImageView(this);
		img4.setBackgroundResource(R.drawable.ainid);
		advPics.add(img4);
		ImageView img5 = new ImageView(this);
		img5.setBackgroundResource(R.drawable.ainie);
		advPics.add(img5);
		ImageView img6 = new ImageView(this);
		img6.setBackgroundResource(R.drawable.ainif);
		advPics.add(img6);
		ImageView img7 = new ImageView(this);
		img7.setBackgroundResource(R.drawable.ainia);
		advPics.add(img7);
		ImageView img8 = new ImageView(this);
		img8.setBackgroundResource(R.drawable.ainib);
		advPics.add(img8);
		ImageView img9 = new ImageView(this);
		img9.setBackgroundResource(R.drawable.ainic);
		advPics.add(img9);
		advPager.setAdapter(new AdvAdapter(advPics));
	}
	
	private final class AdvAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdvAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			//删除页卡
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			//返回页卡的数量  
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			//添加页卡 
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			//官方提示
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

	}

}
