package com.android.tests.activities;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tests.R;

public class H_LangActivity extends Activity{

	private static final String TAG = "LangActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lang);
		
		Log.v(TAG, "onCreate rootview1 = "+getWindow().getDecorView().getRootView());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.v(TAG, "onConfigurationChanged newConfig="+newConfig.fontScale);
		//ViewGroup vg = (ViewGroup)getWindow().getDecorView().getRootView();
		//vg.invalidate();
		setRefreshViewGroup(this, (ViewGroup)getWindow().getDecorView().getRootView());
	}

	public void setRefreshViewGroup(Context context, ViewGroup root){
		
		for(int i=0; i<root.getChildCount(); i++){
			View child = root.getChildAt(i);
			if(child instanceof TextView){
				if(child.getTag() != null){
					if(((TextView)child).getText() != null && ((TextView)child).getText().toString().length() > 0){
						int stringID = getResourceID(context, child.getTag());
						((TextView)child).setText(stringID);
						Log.v(TAG, "(TextView)child).getText()="+((TextView)child).getText());
					}
					if(((TextView)child).getHint() != null && ((TextView)child).getHint().toString().length() > 0){
						int hintID = getResourceID(context, child.getTag());
						((TextView)child).setText(hintID);
						Log.v(TAG, "(TextView)child).getHint()="+((TextView)child).getHint());
					}
				}
			}else if(child instanceof ViewGroup){
				setRefreshViewGroup(context, (ViewGroup)child);
			}
		}
	}

	public int getResourceID(Context ctx, Object tag){
		return ctx.getResources().getIdentifier((String)tag, "string", ctx.getPackageName());
	}
}
