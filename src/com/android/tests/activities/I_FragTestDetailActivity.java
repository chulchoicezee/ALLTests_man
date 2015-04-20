package com.android.tests.activities;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.tests.R;
import com.android.tests.fragments.DetailFragment.OnButtonListener;

public class I_FragTestDetailActivity extends Activity implements OnButtonListener{

	private static final String TAG = "FragmentTestActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Log.v(TAG, "onCreate");
		
	}

	@Override
	public void onButtonClick() {
		// TODO Auto-generated method stub
		
	}


}
