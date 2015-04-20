/*
 * Copyright (C) 2010 - 2012 Myriad Group AG.
 */

package com.android.tests.aab;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.tests.R;

public class AABSoapDoneFragment extends Fragment implements OnClickListener{

	private Context mContext;
	private View mLayout;
	private String mTitle;
	private String mBody;
	private int mBtn1;
	private int mBtn2;
	private int mErrorCode;
	private boolean mExistingUser;
	private String mErrorString;
	
	//private int mFragmentID;
	
	public interface onButtonLisntener{
		public void onButtonClick();
	}
	
    private static final String TAG = "AABSoapDoneFragment";

	public interface OnSpannableListener{
		void onSpannableClick(int pos);
	}
	
	public OnSpannableListener onSpannableListener = null;
	
    public AABSoapDoneFragment(Context ctx, int fragID, int errorCode, boolean existingUser, String errorString, int btn1, int btn2){
    	
    	mContext = ctx;
    	mErrorCode = errorCode;
    	mExistingUser = existingUser;
    	mErrorString = errorString;
    	mBtn1 = btn1;
    	mBtn2 = btn2;
    }
    
   
    
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = activity;
		//onSpannableListener = (OnSpannableListener)activity;
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
		
		mLayout = inflater.inflate(R.layout.fragment_syncinfo, null);
		
		return mLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Resources res = mContext.getResources();
		
		String body1 = null;
		String body2 = null;
		String body3 = null;
		TextView info_title_tv = (TextView)((Activity)mContext).findViewById(R.id.info_title_tv);
		info_title_tv.setText("aab title");
		
		TextView info_body_tv1 = (TextView)((Activity)mContext).findViewById(R.id.info_body_tv1);
		TextView info_body_tv2 = (TextView)((Activity)mContext).findViewById(R.id.info_body_tv2);
		TextView info_body_tv3 = (TextView)((Activity)mContext).findViewById(R.id.info_body_tv3);
		Button btn1 = (Button)getActivity().findViewById(R.id.info_btn1);
		
		

		
		if(body1 != null)
			info_body_tv1.setText(body1);
		if(body2 != null){
			info_body_tv2.setText(body2);
			info_body_tv2.setVisibility(View.VISIBLE);
		}
		
/*		//SpannableStringBuilder ssb_main = new SpannableStringBuilder();
		String body3_1 = res.getString(R.string.new_customer_register_succeed_learn_more);
		SpannableStringBuilder ssb1 = new SpannableStringBuilder(body3_1.substring(0, 11));
		ssb1.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View widget) {
				// TODO Auto-generated method stub
				//Intent i = new Intent("com.pantech.aabsync.client.learnmore");
				//startActivity(i);
			}
		}, 0, 5, 0);
		ssb1.append(text.substring(5, 11));
		//String body1_1 = res.getString(R.id.new_customer_register_succeed_second_content);
		
		SpannableStringBuilder ssb2 = new SpannableStringBuilder(body3_1.substring(11, 15));
		ssb2.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View widget) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ACTION.LEARN_MORE);
				startActivity(i);
			}
		}, 0, 4, 0);
		ssb1.append(ssb2);
		ssb1.append("\n\n");
		
		if(mErrorCode == ERROR.SUCCESS){
			String tmp1 = res.getString(R.string.new_customer_register_succeed_second_content);
			int idx = tmp1.indexOf("Terms");
			Log.v(TAG, "idx="+idx);
			ssb1.append(tmp1.substring(0, idx));
			SpannableStringBuilder ssb3 = new SpannableStringBuilder(tmp1.substring(idx));
			ssb3.setSpan(new ClickableSpan() {
				
				@Override
				public void onClick(View widget) {
					// TODO Auto-generated method stub
					Intent i = new Intent(ACTION.SHOW_TNC);
					startActivity(i);
				}
			}, 0, 18, 0);
			ssb1.append(ssb3);
		}else{
			ssb1.append(res.getString(R.string.content_registration_fail_dial));
		}
		
		info_body_tv3.setText(ssb1);
		info_body_tv3.setMovementMethod(LinkMovementMethod.getInstance());*/
		
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
		} else if (id == R.id.button1){
			//mCallback.onButtonLisntener();
		}
	}




}
