/*
 * Copyright (C) 2010 - 2012 Myriad Group AG.
 */

package com.android.tests.aab;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.tests.R;

public class AABSoapingFragment extends Fragment implements OnClickListener{

	private Context mContext;
	private View mLayout;

	
    private static final String TAG = "AABSoapingFragment";

	public interface OnButtonListener{
		public void onButtonClick(int param);
	}

	public OnButtonListener onButtonLisntener;
    public AABSoapingFragment(Context ctx, int fragID){
    	mContext = ctx;
    }
    
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = activity;
		onButtonLisntener = (OnButtonListener)activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		
		mLayout = inflater.inflate(R.layout.fragment_soaping, null);
		
		return mLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Button btn = (Button)getActivity().findViewById(R.id.button1);
		btn.setOnClickListener(this);
		Button btn1 = (Button)getActivity().findViewById(R.id.button2);
		btn1.setOnClickListener(this);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == R.id.info_btn1) {
			;//mCallback.onLeftButtonClick(getCurrentID());
		} else if (id == R.id.info_btn2) {
			;//mCallback.onRightButtonClick(getCurrentID());
		} else if(id == R.id.button1){
			onButtonLisntener.onButtonClick(1);
		} else if(id == R.id.button2){
			onButtonLisntener.onButtonClick(2);
		}
	}

}
