package com.android.tests.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.tests.R;
import com.android.tests.activities.M_Service.LocalBinder;

public class M_ServiceBindingActivity extends Activity{

	static public String TAG = "M_ServiceBindingActivity";
	public M_Service mService;
	public Button mBtn1;
	public Button mBtn2;
	public Button mBtn3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
		
		mBtn1 = (Button)findViewById(R.id.button1);
		mBtn2 = (Button)findViewById(R.id.button2);
		mBtn3 = (Button)findViewById(R.id.button3);
		mBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v(TAG, "mService.getRandomNumber() = "+mService.getRandomNumber());
			}
		});
		mBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent i = new Intent(this, M_Service.class);
		bindService(i, mServiceConn, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(mServiceConn);
	}

	
	private ServiceConnection mServiceConn = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			LocalBinder binder = (LocalBinder)service;
			mService = binder.getService();
			//Toast.makeText(SyncServiceActivity.this, "onServiceConnected=>"+mService, Toast.LENGTH_SHORT).show();
			
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			//Toast.makeText(SyncServiceActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
		}
		

	};

}

