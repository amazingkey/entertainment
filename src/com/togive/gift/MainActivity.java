package com.togive.gift;

import java.util.ArrayList;
import java.util.List;

import com.togive.about.About;
import com.togive.games.Games;
import com.togive.joke.Joke;
import com.togive.login.Login;
import com.togive.truewords.Truewordshome;
import com.togive.website.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private int index = 0;
	private boolean isContinue = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_m);
		initViewPager();
	}

	public void ab(View v) {
		switch (v.getId()) {
		case R.id.funtime:
			Intent in = new Intent();
			in.setClass(MainActivity.this, Joke.class);
			startActivity(in);
			// 强制关闭，杀死进程，直接destroy，不能继续循环
			// android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
			break;
		case R.id.gamestime:
			Intent ia = new Intent(MainActivity.this, Games.class);
			startActivity(ia);
			break;
		case R.id.truewordsicon:
			Intent ib = new Intent(MainActivity.this, Truewordshome.class);
			startActivity(ib);
			break;
		case R.id.searchicon:
			Intent ic = new Intent(MainActivity.this, Main.class);
			startActivity(ic);
			break;
		case R.id.abouticon:
			Intent id = new Intent(MainActivity.this, About.class);
			startActivity(id);
			break;
		case R.id.exit:
			Intent ie = new Intent(MainActivity.this, Login.class);
			startActivity(ie);
			finish();
			break;
		default:
			break;
		}
	}

	private void initViewPager() {
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		final List<View> advPics = new ArrayList<View>();
		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.lover);
		advPics.add(img1);
		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.baiyang);
		advPics.add(img2);
		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.jinniu);
		advPics.add(img3);
		ImageView img4 = new ImageView(this);
		img4.setBackgroundResource(R.drawable.shuangzi);
		advPics.add(img4);
		ImageView img5 = new ImageView(this);
		img5.setBackgroundResource(R.drawable.juxie);
		advPics.add(img5);
		ImageView img6 = new ImageView(this);
		img6.setBackgroundResource(R.drawable.shizi);
		advPics.add(img6);
		ImageView img7 = new ImageView(this);
		img7.setBackgroundResource(R.drawable.chunv);
		advPics.add(img7);
		ImageView img8 = new ImageView(this);
		img8.setBackgroundResource(R.drawable.tiancheng);
		advPics.add(img8);
		ImageView img9 = new ImageView(this);
		img9.setBackgroundResource(R.drawable.tianxie);
		advPics.add(img9);
		ImageView img10 = new ImageView(this);
		img10.setBackgroundResource(R.drawable.sheshou);
		advPics.add(img10);
		ImageView img11 = new ImageView(this);
		img11.setBackgroundResource(R.drawable.moxie);
		advPics.add(img11);
		ImageView img12 = new ImageView(this);
		img12.setBackgroundResource(R.drawable.shuiping);
		advPics.add(img12);
		ImageView img13 = new ImageView(this);
		img13.setBackgroundResource(R.drawable.shuangyu);
		advPics.add(img13);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		imageViews = new ImageView[advPics.size()];
		for (int i = 0; i < advPics.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			// 设置子父控件之间的距离
			imageView.setPadding(20, 0, 20, 0);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_focus);
			} else {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_blur);
			}
			group.addView(imageViews[i]);
		}
		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		Runnable r = new Runnable() {

			@Override
			public void run() {
				if (isContinue) {
					index++;
					if (index >= advPics.size()) {
						index = 0;
					}
					viewHandler.sendEmptyMessage(index);
					viewHandler.postDelayed(this, 3000);
				}
			}

		};
		viewHandler.postDelayed(r, 3000);

		// advPager.setCurrentItem(0);
	}

	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(index);
			super.handleMessage(msg);
		}

	};

	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.banner_dian_focus);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
			}

		}

	}

	private final class AdvAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdvAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// 删除页卡
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			// 返回页卡的数量
			// System.out.println("叶卡的数量"+views.size());
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// 添加页卡
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// 官方提示
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