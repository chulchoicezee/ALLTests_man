package com.android.tests.activities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.android.tests.R;
import com.android.tests.R.id;
import com.android.tests.R.layout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class B_CameraActivity extends Activity{

	String[] list = new String[]{"Camera Test1", "Camera Test2", "photo resize"};
	ListView mListView = null;
	ImageView mImageView = null;
	
	public static final int RESULT_IMAGE_CAPTURE = 0;
	public static final int RESULT_VIDEO_CAPTURE = 1;
	public static final int RESULT_EMAIL_PICK = 2;
	public static final int RESULT_GALLERY_PICK = 3;
	private static final String TAG = "IntentActivity";
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private Uri fileUri;
	String addr = "http://www.naver.com";
	HttpURLConnection c = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		mListView = (ListView) findViewById(R.id.listView1);
		mImageView = (ImageView) findViewById(R.id.intent_imageView1);
		
		List<HashMap<String,String>> arrList = new ArrayList<HashMap<String, String>>();
		for(int i=0; i<list.length; i++){
			HashMap<String, String> hm= new HashMap<String, String>();
			hm.put("name", list[i]);
			hm.put("image", null);
			arrList.add(hm);
		}
		String[] from = {"image", "name"};
		int[] to = {R.id.imageView1, R.id.textView1};
		SimpleAdapter sa = new SimpleAdapter(getBaseContext(), arrList, R.layout.list_layout, from, to);
		mListView.setAdapter(sa);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView lv = (ListView) arg0;
				TextView tv = (TextView)arg0.getChildAt(arg2).findViewById(R.id.textView1);
				String tv_txt = tv.getText().toString();
				Toast.makeText(B_CameraActivity.this, tv_txt, Toast.LENGTH_SHORT).show();

				if(arg2 == 0){
					String action = android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
					fileUri = Uri.fromFile(getOutputMediaFile(MEDIA_TYPE_IMAGE));
					Log.v(TAG, "fileUri="+fileUri.toString());
					Intent i = new Intent(action);
					i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
					//i.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, (long)0); // 0->for mms
					//i.putExtra(android.provider.MediaStore.EXTRA_SIZE_LIMIT, (long)600000);
					startActivityForResult(i, RESULT_IMAGE_CAPTURE);
					//i.setAction("android.intent.action.intenttest");
					//startActivity(i);
				}else if(arg2 == 1){
					String action = android.provider.MediaStore.ACTION_VIDEO_CAPTURE;
					fileUri = Uri.fromFile(getOutputMediaFile(MEDIA_TYPE_VIDEO));
					Intent i = new Intent(action);
					i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
					i.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, CamcorderProfile.QUALITY_LOW); // 0->for mms
					i.putExtra(android.provider.MediaStore.EXTRA_SIZE_LIMIT, (long)600000);
					startActivityForResult(i, RESULT_VIDEO_CAPTURE);

				}else if(arg2 == 2){
			        Resources r = getApplicationContext().getResources();
			        DisplayMetrics outMetrics = new DisplayMetrics();
			        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
			        Log.v(TAG, "outMetrics resolution"+outMetrics.widthPixels+" "+outMetrics.heightPixels);
			        Log.v(TAG, "outMetrics resolution"+outMetrics.xdpi+" "+outMetrics.ydpi);
			        
			        Log.v(TAG, "outMetrics.density="+outMetrics.density+" densityDPI="+outMetrics.densityDpi+" scaledDensity="+outMetrics.scaledDensity);
			        //px->dp
			        int dp;
			        int px = 100;
			        
			        Log.v(TAG, "getDip(px)="+getDip(px));
			        dp = getDip(px);
			        //dp->px
			        px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
			                r.getDisplayMetrics());
			        Log.v(TAG, "px="+px);
			        //File imageFile = new File("//assets/picture.jpg");
			        //Log.v(TAG, "imageFile.length="+imageFile.length());
			        InputStream is = null;
			        try {
						is = getAssets().open("picture.jpg");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        Bitmap bmp = BitmapFactory.decodeStream(is);
			        //Log.v(TAG, "bm.getDensity()="+bmp.getDensity()+" bm.getHeight()="+bmp.getHeight()+" bm.getWidth()="+bmp.getWidth());
			        //Log.v(TAG, "bm.getByteCount()="+bmp.getByteCount()+" size="+bmp.getRowBytes()*bmp.getHeight());
			        
			        double viewHeight = 1200;
			        double width = bmp.getWidth();
			        double height = bmp.getHeight();
			        if(height > viewHeight){
			        	double percente = height/100;
			        	double scale = viewHeight/percente;
			        	width *= scale/100;
			        	height *= scale/100;
			        	Log.v(TAG, "width="+width+" height="+height);
			        }
			        width = 96*1.5;
			        height = 64*1.5;
			        Bitmap resizedBmp = Bitmap.createScaledBitmap(bmp, (int)width, (int)height, true);
			        Log.v(TAG, "resizedBmp.getDensity()="+resizedBmp.getDensity()+" resizedBmp.getHeight()="+resizedBmp.getHeight()+" resizedBmp.getWidth()="+resizedBmp.getWidth());
			        //Log.v(TAG, "resizedBmp.getByteCount()="+resizedBmp.getByteCount()+" size="+resizedBmp.getRowBytes()*resizedBmp.getHeight());
			        
			        mImageView.setImageBitmap(resizedBmp);
				}
				
			}
			
		});
	}
	
    public int getDip(int px){
    	float scale = getBaseContext().getResources().getDisplayMetrics().density;
    	return (int)(px * scale + 0.5f);
    }

    private static File getOutputMediaFile(int type){
    	File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
    	if(!mediaStorageDir.exists()){
    		if(!mediaStorageDir.mkdirs()){
    			Log.v(TAG, "failed to create directory");
    			return null;
    		}
    	}

    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	File mediaFile;
    	if(type == MEDIA_TYPE_IMAGE){
    		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    	}else if(type == MEDIA_TYPE_VIDEO){
    		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VIDEO_" + timeStamp + ".mp4");
    	}else{
    		return null;
    	}
    	Log.v(TAG, "mediaFile="+mediaFile.toString());
    	//Log.v(TAG, "mediaFile="+mediaFile);
    	return mediaFile;
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.v(TAG, "onActivityResult");
		
		switch(requestCode){
		case RESULT_IMAGE_CAPTURE:
			if(resultCode == RESULT_OK){
				Log.v(TAG, "fileUri="+fileUri);
				if(data != null){
					Uri uri = data.getData();
					Log.v(TAG, "uri="+uri.toString());
					//imgView1.setImageURI(fileUri);
				}
				mImageView.setImageURI(fileUri);
			}
			break;
		case RESULT_VIDEO_CAPTURE:
			if(resultCode == RESULT_OK){
				if(data != null){
					Uri uri = data.getData();
					Log.v(TAG, "uri="+uri.toString());
				}
			}
			break;
		case RESULT_EMAIL_PICK:
			Log.v(TAG, "RESULT_EMAIL_PICK");
			if(resultCode == RESULT_OK){
				if(data != null ){
					ArrayList<ContentValues> returnValues = data.getExtras().getParcelableArrayList("pickedItems");
					for (int i = 0; i < returnValues.size(); i++) {
						ContentValues values = returnValues.get(i);
						String name = values.getAsString("name");
						String email = values.getAsString("email");
						Log.v(TAG, "name="+name+", email="+email);
						//email = name + "[" + email + "]" + ",";
						
					}
					
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}
}
