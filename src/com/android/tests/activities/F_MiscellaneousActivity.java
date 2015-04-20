package com.android.tests.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tests.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class F_MiscellaneousActivity extends Activity{

	static public String TAG = "MiscellaneousActivity";
	ActionBar mActionBar = null;
	private AdView adView;
	private final String AD_UNIT_ID = "ca-app-pub-3347146965060335/4793330801";
	
	private Socket socket;
	BufferedReader socket_in;
	PrintWriter socket_out;
	String data;
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail);
		mActionBar = getActionBar();
		mActionBar.setCustomView(null);
    	mActionBar.setDisplayShowCustomEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
		//mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        tv1 = (TextView) findViewById(R.id.textView1);
		
        Intent i = new Intent();
		if(getIntent() != null)
			Log.v(TAG, "not null");
		else
			Log.v(TAG, "is a null");
		
		ATree Atree = new PineTree();
		
		BTree Btree = new PineTree();
		
		Animal animal = new Animal();
		//Animal.Poyuryu po = animal.new Poyuryu();
		
		animal.move();
		Log.v(TAG, "onCreated");
		Dog dog = new Dog();
		dog.move();
		
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		SimpleDateFormat curFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
		String strNow = curFormat.format(date);
		Log.v(TAG, strNow);
		
		//����
		adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
		layout.addView(adView);

		// Create an ad request. Check logcat output for the hashed device ID to get test ads on a
	    // physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
	        .build();

	 // Start loading the ad in the background.
	    adView.loadAd(adRequest);

		Thread worker = new Thread() {
			public void run() {
				try {
					socket = new Socket("chulchoice.cafe24app.com", 80);
					socket_out = new PrintWriter(socket.getOutputStream(), true);
					socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					socket_out.append("merong");
					socket_out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					while (true) {
						data = socket_in.readLine();
						tv1.post(new Runnable() {
							public void run() {
								tv1.setText(data);
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		worker.start();
	}

	class Animal {
		Button btn = null;
		Animal(){
			Log.v(TAG, "[S]Animal created = "+this);
			move();//�ڽĲ� ȣ��
			this.move();//�ڽĲ� ȣ��
			Log.v(TAG, "[E]Animal created = "+this);
			
		}
		void move(){
			Log.v(TAG, "Animal moved");
			this.move1();
		}
		void move1(){
			Log.v(TAG, "Animal moved1");
		}
		class Poyuryu{
			Poyuryu(){
				Log.v(TAG, "Poyuryu created = "+this);
			}
		}
	}
	
	class Dog extends Animal{
		Dog(){
			Log.v(TAG, "[S]Dog created = "+this);//�θ��� this�� �ڽ��� this ��� ������ �ڽ��ν��Ͻ���.
			move();
			Log.v(TAG, "[E]Dog created = "+this.toString());//this�� super toString() ��� ���� ����
			Log.v(TAG, "[E]Dog created = "+super.toString());
		}
		void move(){
			Log.v(TAG, "Dog moved");
			this.move1();//����ȣ��
			super.move();//�θ� ȣ��
			//btn.setTag(null);
		}
		void move1(){
			Log.v(TAG, "Dog moved1");
		}
		
	}
	//��, �����, �ڽ� �ν��Ͻ� ���� ���, �θ��� override�� api�� ȣ���Ϸ��� �ڽĿ��� super.api()�� �ϴ� ���ۿ� ���.
	interface ATree{
		void AleafShape();
	}

	interface BTree{
		void BleafShape();
	}

	class PineTree implements ATree, BTree{
		public void AleafShape(){
			Log.v(TAG, "AleafShape");
		}

		@Override
		public void BleafShape() {
			// TODO Auto-generated method stub
			Log.v(TAG, "BleafShape");
		}
	}

}

