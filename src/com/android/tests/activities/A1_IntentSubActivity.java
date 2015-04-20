package com.android.tests.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class A1_IntentSubActivity extends Activity{

	static public String TAG = "IntentSubActivity";
	ActionBar mActionBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mActionBar = getActionBar();
		mActionBar.setCustomView(null);
    	mActionBar.setDisplayShowCustomEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
		//mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        Intent i = getIntent();
        if(i != null){
			Log.v(TAG, "not null"+getIntent().toString());
			Toast.makeText(this, "onCreate "+i.getStringExtra("box"), Toast.LENGTH_SHORT).show();
        }else
			Log.v(TAG, "is a null");
		}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Toast.makeText(this, "onNewIntent "+intent.getStringExtra("box"), Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
        Intent i = getIntent();
        if(i != null){
			Log.v(TAG, "not null"+getIntent().toString());
			Toast.makeText(this, "onResume "+i.getStringExtra("box"), Toast.LENGTH_SHORT).show();
        }
	}

}

