package com.android.tests.activities;

import java.util.List;

import com.android.tests.R;
import com.android.tests.R.layout;
import com.android.tests.R.menu;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class D_SSOActivity extends Activity{

	public static final String TAG = "sso_test";
	public static final String SSO_CLIENT_NAME = "com.motricity.verizon.ssodownloadable";
	// URI used by 3rd Party Applications
	public static final String SSO_URI = "content://com.verizon.loginclient/token";
	// URI used by Setup Wizard
	public static final String SSO_URI_SILENT = "content://com.verizon.loginclient/token/silent";
    public static final String SSO_TOKEN = "token";
    public static final String SSO_ESTABLISH_TOKEN = "com.motricity.verizon.ssoengine.ESTABLISH_TOKEN";
    public static final String SSO_ACCOUNT = "com.verizon.ACCESS_VZW_ACCOUNT";
    
	String mToken = null;
    ContentObserver mContentObserver = null;
    TextView tv1 = null;
    Button btn1 = null;
    boolean mSilent = false;
    
    // Define the handler that receives messages when content observer is notified on content change
	final Handler mContentObserverHandler = new Handler() {
		public void handleMessage(Message paramMessage) {
			// A notification of change has arrived from the Content Provider. Call again to getToken
			Log.v(TAG, "mContentObserverHandler handlMessage");
    		getToken(mSilent);
		}
	};
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sso);
		Log.v(TAG, "onCreate");
		
		//get task info
		
    	new Thread(new Runnable() {
	        public void run() {
	        	Log.v(TAG, "Thread created");
	        	Thread thread = new Thread();
	            try{
	            	thread.sleep(10*1000);

	            }catch(Exception e){e.printStackTrace();};
	        	Log.v(TAG, "Thread time 10 elapsed");
	        	Log.v(TAG, "getCallingPackage()="+getCallingPackage());
	    		ActivityManager am = (ActivityManager)D_SSOActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
	    		List<ActivityManager.RunningTaskInfo> allTasks = am.getRunningTasks(50);
	    		Log.v(TAG, "--------1.RunningTaskInfo-----------------------------");
				for(ActivityManager.RunningTaskInfo aTask : allTasks){
	    			String aPackageName = aTask.topActivity.getPackageName();
	    			if(aPackageName.equals("arabiannight.tistory.com.service")){//"com.vzw.apnservice")
	    				//	||aPackageName.equals("com.pantech.app.BackupAssistant")
	    				//	||aPackageName.equals("com.fusionone.android.sync.service")){
	    				Log.v(TAG, "----------RunningTaskInfo-----------------------------");
	    				Log.v(TAG, "aPackageName="+aPackageName);
	    				Log.v(TAG, "aTask.id="+aTask.id);
	    				Log.v(TAG, "aTask.numActivities="+aTask.numActivities);
	    				Log.v(TAG, "aTask.numRunning="+aTask.numRunning);
	    				Log.v(TAG, "aTask.baseActivity="+aTask.baseActivity.getClassName());
	    				Log.v(TAG, "aTask.topActivity="+aTask.topActivity.getClassName());
	    			}
	    		}

	    		List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses(); 
	    		Log.v(TAG, "--------2.RunningAppProcessInfo-----------------------");
				for(ActivityManager.RunningAppProcessInfo app : appList){
	    			String aProcessName = app.processName;
	    			//Log.v(TAG, "aProcessName  ="+aProcessName);
	    			//if(app.processName.equals("com.vzw.apnservice")
	    				//	||app.processName.equals("com.pantech.app.BackupAssistant")
	    				//	||app.processName.equals("com.fusionone.android.sync.service")){
	    				Log.v(TAG, "----------RunningAppProcessInfo-----------------------");
	    				Log.v(TAG, "aProcessName  ="+aProcessName);
	    				Log.v(TAG, "app.pid="+app.pid+", app.uid="+app.uid);
	    				String[] lists = app.pkgList;
	    				for(String pkg : lists){
	    					Log.v(TAG, "pkg="+pkg);
	    				}
	    			//}
	    		}
	    		List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(50);
	    		Log.v(TAG, "--------3.RunningServiceInfo------------------------------");
				for(ActivityManager.RunningServiceInfo aService : services){
	    			String aPackageName = aService.service.getPackageName();
	    			//if(aPackageName.equals("com.vzw.apnservice")
	    				//	||aPackageName.equals("com.pantech.app.BackupAssistant")
	    				//	||aPackageName.equals("com.fusionone.android.sync.service")){
		    			int aClientLabel = aService.clientLabel;
		    			String aClientPakcage = aService.clientPackage;
		    			String aProcess = aService.process;
		    			
		    			Log.v(TAG, "----------RunningServiceInfo------------------------------");
	    				Log.v(TAG, "aPackageName  ="+aPackageName);
		    			Log.v(TAG, "aProcess              ="+aProcess);
		    			Log.v(TAG, "service.getClassName()="+aService.service.getClassName());
		    			Log.v(TAG, "aService.foreground   ="+aService.foreground);
		    			Log.v(TAG, "aClientPakcage="+aClientPakcage);
		    			Log.v(TAG, "aService.flags="+aService.flags+", aClientLabel="+aClientLabel);
		    			if(aClientPakcage != null && aClientPakcage.equals("com.fusionone.android.sync.service")){
		    				Log.v(TAG, "aClientPakcage is from BUA, so, network is available");
		    			}
	    			//}
	    		}

	        }
	    }).start();


	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG, "onPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG, "onResume");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//btn1.setText(R.string.get_token);
		
		//btn1.setText(btn1.getText().toString().toCharArray());
		//ViewGroup vg = (ViewGroup)findViewById (R.layout.activity_main);
	    //vg.invalidate();
		//setContentView(R.layout.activity_main);
		//btn1.postInvalidate();
		//btn1.invalidate();
		Log.v(TAG, "onConfigurationChanged");
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.v(TAG, "onRestoreInstanceState");
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		Log.v(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onGetToken(View paramView){
		Log.v(TAG, "onGetToken");
		getToken(false);		
	}
	
	public void onGetToken_silent(View paramView){
		Log.v(TAG, "onGetToken_silent");
		getToken(true);			
	}
	
	public void onEstablishToken(View paramView){
		Log.v(TAG, "onEstablishToken");
		sendBroadcast(new Intent(SSO_ESTABLISH_TOKEN), SSO_ACCOUNT);
	}
	
	public void onInvalidateToken(View paramView){
		Log.v(TAG, "onInvalidateToken");
	    try
	    {
	      Uri uri = Uri.parse(SSO_URI);
	      getContentResolver().delete(uri, null, null);
	      mToken = null;
	      tv1.setText("");
	      return;
	    }
	    catch (SecurityException se)
	    {
			se.printStackTrace();
	    }
	    catch (Exception e)
	    {
			e.printStackTrace();
	    }
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v(TAG, "onDestroy");
		// If your app is registering the content observer in an activity and you don¡¯t want your application to receive the callback  on a notifyChange() when the Activity has been destroyed, then be sure to unregisterContentObserver() as shown below
		if(mContentObserver != null){
			getContentResolver().unregisterContentObserver(mContentObserver);
		}
	}
	
	// A method in third party application requesting content provider to get a token 
	private void getToken(boolean silent){

		PackageManager pm = getApplication().getPackageManager();
		Uri uri = null;
		
		mSilent = silent;
		
		if(silent == false)
			uri = Uri.parse(SSO_URI);
		else
			uri = Uri.parse(SSO_URI_SILENT);
		
		if (mContentObserver != null) {
			getContentResolver().unregisterContentObserver(mContentObserver);
		}

		/**
		 * The apps shall check the device type to determine the authentication method to use. 
		 * The apps shall only use SSO if the device is a type of 4G/LTE. 
		 * The app shall use AppAuth if it's a type of 3G. The following logic may be used to 
		 * determine the device type:
		 */
	    if ((pm.hasSystemFeature("com.verizon.hardware.telephony.lte")) || (pm.hasSystemFeature("com.verizon.hardware.telephony.ehrpd")) || (pm.hasSystemFeature("com.vzw.hardware.lte")) || (pm.hasSystemFeature("com.vzw.hardware.ehrpd")) || (("sdk".equals(Build.MODEL)) && ("sdk".equals(Build.PRODUCT)) && ("generic".equals(Build.DEVICE))))
	    {
	    	String pkgName = null;
	    	ProviderInfo pi = pm.resolveContentProvider("com.verizon.loginclient", 0);
	    	if(pi != null)
	    	{
	    		pkgName = pi.packageName;
	    		if(pkgName == null){
	    			// SSO Client is not installed, direct user to Marketplace to Download SSO Client
	    		}else if(pkgName.equals(SSO_CLIENT_NAME)){
	    			// Official SSO Client is installed.
	    			try{
	    				Log.v(TAG, "getToken uri="+uri);
	    	    		Cursor c = getContentResolver().query(uri, null, null, null, null);
			    		if(c != null){
			    			if(c.moveToFirst()){
			    				String token = c.getString(c.getColumnIndex(SSO_TOKEN));
			    				if(token != null){
				    				Log.v(TAG, "token="+token);
				    				mToken = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
				    				Log.v(TAG, "mToken="+mToken);
				    				tv1.setText(mToken);
				    			}else{//need to get Token from server
				    				Log.v(TAG, "token="+token+", so register contentObserver");
				    				Log.v(TAG, "the uri="+uri);
				    				mContentObserver = new MyContentObserver(mContentObserverHandler);
				    				getContentResolver().registerContentObserver(uri, false, mContentObserver);
				    			}
			    			}
			    			c.close();
			    		}
		    		}catch(SecurityException se){
		    			se.printStackTrace();
		    		}
	    		}else{
	    			// If you got here then the SSO Clients official Content Provider is registered with a rogue application. 
	    			// Verizon can dictate what action the 3rd party app should take in this case, but you should not continue to make the query to get a Token.
	    		}
	    	}	
	    }
	    else
	    {
	    	// Device is not LTE or eHRPD capable, so use AAA based AppAuth 
			// Display message below and exit
			// ¡°This device is a 3G device and can not be used to use SSO.¡±
			Log.v(TAG, "it's not a 4G device, not support");
	    }
	}
	
    class MyContentObserver extends ContentObserver{
    	private Handler handler;
    	
    	public MyContentObserver(Handler _handler){
    		super(_handler);
    		handler = _handler;
    	}
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			//super.onChange(selfChange);
			Log.v(TAG, "onChange mContentObserver="+mContentObserver);
			if(mContentObserver != null){
				Log.v(TAG, "got it!, so unregister contentObserver");
				getContentResolver().unregisterContentObserver(mContentObserver);
				if(handler != null){
					Message msg = handler.obtainMessage();
					handler.sendMessage(msg);
				}	
			}
		}
    	
    }
}
