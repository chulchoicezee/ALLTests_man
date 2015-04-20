package com.android.tests.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class G3_TabtestSub3Activity  extends Activity
{
        private static final String TAG = "TabtestSub3Activity";

		@Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            Log.v(TAG, "onCreate");
            
            TextView  tv=new TextView(this);
            tv.setTextSize(25);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setText("This Is Tab3 Activity");
            
            setContentView(tv);
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuItem item = menu.add(0, 1, 0, "프로그램 메뉴얼");
            //item.setIcon(R.drawable.manual);
            menu.add(0, 2, 0, "관리자에게 글남기기");
            menu.add(0, 3, 0, "공지사항");
            return true;
     
        }
     
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
            Log.v(TAG, "onPause");

		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
            Log.v(TAG, "onResume");
		}

		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
            Log.v(TAG, "onDestroy");
		}

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
            case 1:
            	Log.v(TAG, "");
            	return true;
            case 2:
            	Log.v(TAG, "");
                return true;
            case 3:
            	Log.v(TAG, "");
                return true;
            }
            return false;
        }
}
