package com.android.tests.activities;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.tests.ConfigVariable;
import com.android.tests.R;
import com.android.tests.fragments.DetailFragment;
import com.android.tests.fragments.TitleFragment;
import com.android.tests.fragments.TitleFragment.OnListener;

public class I_FragTestTitleActivity extends Activity implements com.android.tests.fragments.TitleFragment.OnListener{

	private static final String TAG = "FragmentTestActivity";
	private boolean mTwoPane;
	FragmentManager fm;
	FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		/*if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			mTwoPane = false;
		}else{
			mTwoPane = true;
		}*/
		DetailFragment detailFrag = (DetailFragment)getFragmentManager().findFragmentById(R.id.details);
		if(detailFrag == null){
			mTwoPane = false;
		}else{// || detailFrag.isInLayout()){
			mTwoPane = true;
		}
		
		Log.v(TAG, "mTwoPane="+mTwoPane);
		
		fm = getFragmentManager();
		ft = fm.beginTransaction();
	}

	@Override
	public void OnFragListItemClick(int pos) {
		// TODO Auto-generated method stub
		if(mTwoPane){
			DetailFragment detailFrag = (DetailFragment)getFragmentManager().findFragmentById(R.id.details);
			if(detailFrag != null){
				detailFrag.setPos(pos);
				/*Bundle arg = new Bundle();
				arg.putInt("id", pos);
				detailFrag.setArguments(arg);
				getFragmentManager().beginTransaction().commit();*/
			}
		}else{
			/*Intent i = new Intent(this, I_FragTestDetailActivity.class);
            i.putExtra(ConfigVariable.ID, pos);
            startActivity(i);*/
			DetailFragment detailF = new DetailFragment();
			ft.add(R.id.titles, detailF, "me");
			ft.addToBackStack("me");
			ft.commit();
		}
	}


}
