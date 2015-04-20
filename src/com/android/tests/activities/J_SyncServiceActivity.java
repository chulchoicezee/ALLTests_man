package com.android.tests.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.tests.R;
import com.pantech.aabsync.service.aidl.ISyncService;

public class J_SyncServiceActivity extends Activity{

	static public String TAG = "SyncServiceActivity";
	public ISyncService mService;
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
				try {
					mService.startSync();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mService.stopSync();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mService.cancelSync();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		bindService();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(mServiceConn);
	}

	void bindService(){
		Intent i = new Intent();
		i.setAction("com.pantech.sync.syncService");
		boolean result = bindService(i, mServiceConn, Context.BIND_AUTO_CREATE);
		//Toast.makeText(this, "start binding=>"+result, Toast.LENGTH_SHORT).show();
	}
	
	private ServiceConnection mServiceConn = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = ISyncService.Stub.asInterface(service);
			//Toast.makeText(SyncServiceActivity.this, "onServiceConnected=>"+mService, Toast.LENGTH_SHORT).show();
			
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			//Toast.makeText(SyncServiceActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
		}
		

	};

}

