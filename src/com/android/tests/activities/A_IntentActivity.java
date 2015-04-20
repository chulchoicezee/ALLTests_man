package com.android.tests.activities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
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
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class A_IntentActivity extends Activity{

	String[] list = new String[]{"twitter(https://mobile.twitter.com)", "twitter(https://twitter.com)","twitter(twitter://)", "email pick", "gallery pick", "contact test", "contact mimetype add", "intent test"};
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
		setContentView(R.layout.activity_intent);
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
				Toast.makeText(A_IntentActivity.this, tv_txt, Toast.LENGTH_SHORT).show();

				throwIntent(arg2);
			}
			
		});
	}
	
	public void throwIntent(int position){
		Intent i = new Intent();
		Intent mIntent;
		Uri uri = null;
		
		switch(position){
		case 0 :
			i.setAction(Intent.ACTION_VIEW);
			uri = Uri.parse("http://mobile.twitter.com/home?status=Sages+Restaurant,+15916+NE+83rd+St,+Redmond,+WA+98052,+from+YPmobile+app+for+Android");
			i.setData(uri);
			startActivity(i);
			break;
		case 1 :
			i.setAction(Intent.ACTION_VIEW);
			uri = Uri.parse("https://twitter.com/share?text=Sages+Restaurant,+15916+NE+83rd+St,+Redmond,+WA+98052,+from+YPmobile+app+for+Android");
			i.setData(uri);
			startActivity(i);
			break;
		case 2 :
			i.setAction(Intent.ACTION_VIEW);
			uri = Uri.parse("twitter://post?text=Sages+Restaurant,+15916+NE+83rd+St,+Redmond,+WA+98052,+from+YPmobile+app+for+Android");
			i.setData(uri);
			startActivity(i);
			break;
		case 3 :
			i.setAction("com.pantech.app.contacts.action.ACTION_PICK_AND_SELECT_SUBITEM");
			i.setType(android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE); 
			i.putExtra("multiselect", 1);
			i.putExtra( "freeCount", 300 );
			startActivityForResult(i,RESULT_EMAIL_PICK);
			break;
		case 4 :
	        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
	        /*final String photoFile = ContactPhotoUtils
	                .generateTempPhotoFileName();
	        final String croppedPhotoPath = ContactPhotoUtils
	                .pathForCroppedPhoto(
	                		mContext,
	                        photoFile);
	        final Uri croppedPhotoUri = Uri.fromFile(new File(
	                croppedPhotoPath));*/
	        /*intent.setType("image/*");
	        //intent.putExtra("scale", true);
	        //intent.putExtra("scaleUpIfNeeded", true);
	        intent.putExtra("crop", "true");
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);
	        intent.putExtra("outputX", 720);
	        intent.putExtra("outputY", 720);*/
	        //intent.putExtra("return-data", true);
	        //intent.putExtra(MediaStore.EXTRA_OUTPUT, "file:///storage/sdcard0/Android/data/com.android.contacts/cache/tmp/ContactPhoto-IMG_20130114_060207.jpg");

	        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
	        intent.setType("image/*");
	        intent.putExtra("crop", "true");
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);
	        intent.putExtra("outputX", 200);
	        intent.putExtra("outputY", 200);
	        intent.putExtra("return-data", true);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			
			startActivityForResult(intent,RESULT_GALLERY_PICK);
			break;
		case 5 :
			/*final String aab = "content://com.android.contacts/aabGroups_tracking";
			Cursor c = getContentResolver().query(Uri.parse(aab), null, "_add > 0 or _modify > 0 or _delete > 0", null, null);
			if(c != null){
				Log.v(TAG, "aabGroups_tracking getCount()="+c.getCount());
			}*/
			String phone = "33333333";
			Uri contactUri = Uri.parse(String.format("tel: %s", phone));
			i = new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, contactUri);
			startActivity(i);
			
			/*uri = Uri.parse("content://com.android.contacts/contacts/6");
			mIntent = new Intent(Intent.ACTION_VIEW, uri);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			startActivity(mIntent);*/
			break;
		case 6 :
			uri = Uri.parse("content://com.android.contacts/contacts/6");
			mIntent = new Intent(Intent.ACTION_VIEW, uri);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
			startActivity(mIntent);
	        break;
		case 7 :
			/*i = new Intent("android.intent.action.intenttestsub");
			i.putExtra("box", "get");
			startActivity(i);*/
			uri = Uri.parse("content://com.android.contacts/contacts/6");
			mIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(mIntent);
	        break;
		default:
			break;
		}
		
	}
	

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.v(TAG, "onActivityResult");
		
		switch(requestCode){

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
	
	public boolean onListener(){
		Log.v(TAG, "onListener");
		return false;
		
	}

}
