package com.togive.website;

import java.util.ArrayList;
import java.util.HashMap;

import com.togive.gift.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main extends Activity {
    /** Called when the activity is first created. */
	private WebView m_webview=null;
	private String m_url=null;
	private String  m_homePage=null;
	private ProgressDialog m_progressDlg=null;
	private Handler m_handler=null;			//用于下载线程与UI间的通讯
	private Bookmark m_bookmark=null;
	
	private final int HIDE=0;
	private final int SHOW=1;
	
	private final int RESULT_OK=1;
	private final String LIST="List";
	private final String URL="Url";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.websitemain);
        
        m_bookmark=new Bookmark();   
        m_homePage=getResources().getString(R.string.home_page);
        
        m_bookmark.initDB(this);
        initWebView();
        initHandler();
        initDlg();
        
        m_homePage="https://www.baidu.com/";
        loadUrl(m_webview,m_homePage);
        
        Log.d("MAIN","ONCREATE");
    }
    
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if(keyCode==KeyEvent.KEYCODE_BACK && m_webview.canGoBack())
		{
			m_webview.goBack();
			Log.d("MAIN", "ONKEYDOWN1");
			return true;
		}
		else
		{
			Log.d("MAIN", "ONKEYDOWN2");
		}
		return super.onKeyDown(keyCode, event);
	}
    
	@SuppressLint("HandlerLeak") private void initHandler()
	{
		m_handler=new Handler()
		{

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what)
				{
				case HIDE:
					m_progressDlg.hide();
					break;
				case SHOW:
					m_progressDlg.show();
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
			
		};
	}
	
    @SuppressLint("SetJavaScriptEnabled") private void initWebView()
    {
    	m_webview=(WebView)findViewById(R.id.web_engine);
    	 
    	 //设置多点触控放点
    	m_webview.getSettings().setSupportZoom(true);
    	m_webview.getSettings().setBuiltInZoomControls(true);
    	 
    	m_webview.getSettings().setJavaScriptEnabled(true); 
    	m_webview.setScrollBarStyle(0);//设置滚动条风格，为0表示不给滚动条留空间
         
    	m_webview.setWebViewClient(new WebViewClient()
        {
        	//用WebView载入页面
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				loadUrl(view,url);
				return true;
			}
        	 
         }
         );
         
    	 m_webview.setWebChromeClient(new WebChromeClient() 
         {
        	//载入进度改变而被触发
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if(newProgress==100)
				{
					m_handler.sendEmptyMessage(HIDE);
				}
				super.onProgressChanged(view, newProgress);
			}
        	 
         });
    }
    
    private void initDlg()
    {
    	m_progressDlg=new ProgressDialog(this);
    	m_progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	m_progressDlg.setMessage("页面跳转中...");
    }
    
    private void loadUrl(final WebView view,final String url)
    {
    	new Thread()	
    	{
			@Override
			public void run() {
				m_handler.sendEmptyMessage(SHOW);
				view.loadUrl(url);
			}
    	}.start();
    }
    

    //创建menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	//设置菜单触发事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.forward:
			if(m_webview.canGoForward())
			{
				m_webview.goForward();
			}
			break;
		case R.id.refresh:
			m_webview.reload();
			break;
		case R.id.bookmark:
			addToBookmark();
			break;
		case R.id.showHistory:
			showHistory();
			break;
		case R.id.clearHistory:
			m_webview.clearHistory();
			break;	
		case R.id.showBookmark://查看书签
			showBookmark();
			break;
		case R.id.exit:
			System.exit(0);
		default:
			break;
		}
		return true;
	}
	
	public void addToBookmark()
	{
		String Title =m_webview.getTitle();
		String Url = m_webview.getUrl();
		
		m_bookmark.openMyBookmark(this);
		m_bookmark.insert(Title, Url);
		m_bookmark.closeMyBookmark();
	}
	
	public void showHistory()
	{
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		WebBackForwardList backlist=m_webview.copyBackForwardList();
		
		for(int i=0;i<backlist.getSize();i++)
		{
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("Title", backlist.getItemAtIndex(i).getTitle());
			map.put("Url", backlist.getItemAtIndex(i).getUrl());

			list.add(map);
		}
		
		Intent intent=new Intent(this,ListShower.class);
		Bundle  bundler=new Bundle();
		
		bundler.putSerializable(LIST, list);
		intent.putExtras(bundler);
		
		startActivityForResult(intent,1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		// TODO Auto-generated method stub
		switch(resultCode)
		{
		case RESULT_OK:
			m_url=data.getStringExtra(URL);
			loadUrl(m_webview,m_url);
			break;
		default:
			break;
		}
	}
	
	//查看书签
	private void showBookmark()
	{
		ArrayList<HashMap<String,String>> list=null;
		
		m_bookmark.openMyBookmark(this);
		list=m_bookmark.getList();
		m_bookmark.closeMyBookmark();
		
		Intent intent=new Intent(this,ListShower.class);
		Bundle  bundler=new Bundle();
		
		bundler.putSerializable(LIST, list);
		intent.putExtras(bundler);
		
		startActivityForResult(intent,1);
		
	}
	
	//支持横屏切换
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // land do nothing is ok
            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    // port do nothing is ok
            }
    }
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 * 设置监听返回键，实现网页的回退
	 */

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		/*if(m_webview.canGoBack())
		{
			m_webview.goBack();
		}
		else
		{
			Log.d("MAIN", "ONBACKPRESSED");
		}*/
		
		m_webview.goBack();
		Log.d("MAIN", "ONBACKPRESSED");
	}
	
	
	
}