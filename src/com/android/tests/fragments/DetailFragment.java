package com.android.tests.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.tests.R;

public class DetailFragment extends Fragment{

	FragmentManager fm;
	FragmentTransaction ft;

	public interface OnButtonListener{
		public void onButtonClick();
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_detail,container,false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Button btn1 = (Button)getActivity().findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DetailFragment detailF = new DetailFragment();
				ft.add(R.id.frag1, detailF);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
		fm = getFragmentManager();
		ft = fm.beginTransaction();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	 public void setPos(int pos) {
		    TextView view = (TextView) getView().findViewById(R.id.textView1);
		    view.setText(Integer.toString(pos));
		 } 
		 


}
