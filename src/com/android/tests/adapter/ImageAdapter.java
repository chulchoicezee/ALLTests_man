package com.android.tests.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tests.R;

public class ImageAdapter extends ArrayAdapter<String>{
	private static final String TAG = "ImageAdapter";
	String[] imageURLs;
	LayoutInflater inflater;
	ViewHolder viewHolder = null;
	ImageView mIV;
	HashMap mMap = new HashMap<String, Bitmap>();
	int mCurrentPos;
	
	public ImageAdapter(Context ctx, int itemLayout, String[] urls){
		super(ctx, itemLayout, urls);
		
		inflater = ((Activity)ctx).getLayoutInflater();
		imageURLs = urls;
	}
	private static class ViewHolder{
		ImageView iv;
		TextView tv;
		int position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		mCurrentPos = position;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.iv = (ImageView)convertView.findViewById(R.id.imageView1);
			viewHolder.tv = (TextView)convertView.findViewById(R.id.textView1);
			convertView.setTag(viewHolder);
		}
			
		viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.position = position;
		Log.v(TAG, "getView = "+position);
		if(mMap.containsKey(String.valueOf(position))){
			Bitmap bm = (Bitmap)mMap.get(String.valueOf(position));
			viewHolder.iv.setImageBitmap(bm);
			Log.v(TAG, "mMap.containsKey position="+String.valueOf(position));
		}else{
			ImageAsyncTask mAsyncTask = new ImageAsyncTask(position, viewHolder, imageURLs[position]);
			mAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, imageURLs[position]);

		}
		
		return convertView;
	}
	
	class ImageAsyncTask extends AsyncTask<String, Void, Bitmap>{

		int position;
		ViewHolder mHolder;
		String url;
		
		public ImageAsyncTask(int position, ViewHolder viewHolder, String url){
			this.position = position;
			this.mHolder = viewHolder;
			this.url = url;
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap imageBitmap = null;
			boolean invalidate = position >= mCurrentPos - 10 && position <= mCurrentPos + 10;
			Log.v(TAG, "doInBackground invalidate="+invalidate);
			if(invalidate){
				try {
					URL imageURL = new URL(params[0]);
					imageBitmap = BitmapFactory.decodeStream(imageURL.openStream());
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			
			return imageBitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//if(mHolder.position == this.position)
			boolean invalidate = position >= mCurrentPos - 10 && position <= mCurrentPos + 10;
			Log.v(TAG, "onPostExecute invalidate="+invalidate);
			if(mHolder.position == position){
				mHolder.iv.setImageBitmap(result);
				mMap.put(String.valueOf(position), result);
			}
			
		}
		
	}
}
