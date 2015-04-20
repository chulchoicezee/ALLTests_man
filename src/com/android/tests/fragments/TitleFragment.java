package com.android.tests.fragments;

import com.android.tests.DummyContent;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleFragment extends ListFragment{
	
	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	
	private OnListener mListener = null;
	
	public interface OnListener{
		void OnFragListItemClick(int pos);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mListener = (OnListener)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ArrayAdapter<DummyContent.DummyItem> adapter = new ArrayAdapter<DummyContent.DummyItem>(  
			     getActivity(), android.R.layout.simple_list_item_1,  
			     DummyContent.LISTITEM);  
		setListAdapter(adapter);  
		return super.onCreateView(inflater, container, savedInstanceState);  
				   
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);


	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		mListener.OnFragListItemClick(position);
	}

}
