package com.android.tests.activities;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class M_Service extends Service{

	private final IBinder mBinder = new LocalBinder();
	private final Random rand = new Random();
	
	public class LocalBinder extends Binder{
		M_Service getService(){
			return M_Service.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	public int getRandomNumber(){
		return rand.nextInt(1000);
	}
	
}

