package com.togive.website;

import java.util.ArrayList;
import java.util.HashMap;

import com.togive.gift.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListShower extends Activity {
	
	private final String LIST="List";
	private final int RESULT_OK=1;
	private final String RUL="Url";
	
	private ListView m_listview; 
	private ArrayList<HashMap<String,String>> m_list=null;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.history);
		
		m_listview=(ListView)findViewById(R.id.historyList);
		m_list=(ArrayList<HashMap<String,String>>)getIntent().getSerializableExtra(LIST);
		
		SimpleAdapter adapter=new SimpleAdapter(
								this,
								m_list,						//数据来源
								R.layout.historylist,		//数据布局
								new String[]{"Title","Url"},
								new int[]{R.id.Title,R.id.Url}
								);
		
		m_listview.setAdapter(adapter);
		
		m_listview.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url=m_list.get(position).get("Url");
				
				Intent intent=new Intent();
				intent.putExtra(RUL, url);
				
				setResult(RESULT_OK, intent);
				finish();
				}
			
		});
				
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // land do nothing is ok
            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    // port do nothing is ok
            }
    }
}
