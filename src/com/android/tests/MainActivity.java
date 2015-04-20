package com.android.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	String[] list = new String[]{"A. Intent Test", "B. Camera Test", "C. Provider Test", "D. SSO Test","E. Cursor Loader Test", 
			"F. Misc..", "G. TabHost test", "H. language test", "I. fragment test", "J. sync service test", 
			"K. list image loading", "L. get xml from network", "M. service bind", "N. AAB Fragment Test", "socket io test"};
	ListView mListView = null;
	//private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listView1);
		
		List<HashMap<String,String>> arrList = new ArrayList<HashMap<String, String>>();
		for(int i=0; i<list.length; i++){
			HashMap<String, String> hm= new HashMap<String, String>();
			hm.put("name", list[i]);
			hm.put("image", null);
			arrList.add(hm);
		}
		String[] from = {"image", "name"};
		int[] to = {R.id.imageView1, R.id.textView1};
		SimpleAdapter sa = new SimpleAdapter(getBaseContext(), arrList, R.layout.list_layout, from, to);
		mListView.setAdapter(sa);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView lv = (ListView) arg0;
				//TextView tv = (TextView)arg0.getChildAt(arg2).findViewById(R.id.textView1);
				//String tv_txt = tv.getText().toString();
				//Toast.makeText(MainActivity.this, tv_txt, Toast.LENGTH_SHORT).show();
				throwIntent(arg2);
			}
			
		});
		
		
	}
	
	public void throwIntent(int position){
		Intent i = new Intent();
		
		i.setAction("android.intent.action.intenttest"+position);

		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onListener(){
		Log.v(TAG, "onListener");
		return false;
		
	}
}
