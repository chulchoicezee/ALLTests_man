package com.android.tests.activities;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.tests.R;
import com.android.tests.aab.AABSoapDoneFragment;
import com.android.tests.aab.AABSoapingFragment;
import com.android.tests.aab.AABSoapingFragment.OnButtonListener;

public class N_AABFragTestActivity extends Activity implements OnButtonListener{

	private static final String TAG = "N_AABFragTestActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_syncinfo);
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		String title = "aab title";
		String body = "it's body";
		AABSoapingFragment mFragment = new AABSoapingFragment(this, 1);
		/*ft.replace(R.id.frag1, mFragment, null);
		ft.commit();*/
		ft.add(R.id.frag1, mFragment).commit();
		Log.v(TAG, "onCreate");
		
	}

	@Override
	public void onButtonClick(int param) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		String title = "aab title";
		String body = "it's body";
		if(param == 1){
			AABSoapDoneFragment mFragment = new AABSoapDoneFragment(this, 1, 0, false, body, 0, 0);
			ft.add(R.id.frag1, mFragment);
			ft.addToBackStack(null);
			ft.commit();
		}else{
			Toast.makeText(this, "merong", Toast.LENGTH_SHORT).show();
		}
		
	}


}
