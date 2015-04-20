package com.android.tests.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.android.tests.R;

public class G_TabtestActivity extends TabActivity 
{
            private static final String TAG = "TabtestActivity";

			/** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_tab);
                    
            		final ActionBar ab = getActionBar();
            		
            		// set defaults for logo & home up
            		ab.setDisplayHomeAsUpEnabled(true);
            		ab.setDisplayUseLogoEnabled(false);
            		ab.setDisplayShowCustomEnabled(true);
            		ab.setDisplayShowTitleEnabled(true);
            		ab.setTitle("Action bar!");
            		LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            		View v = inflator.inflate(R.layout.tab_actionbar_button, null);

            		ab.setCustomView(v);
                    // create the TabHost that will contain the Tabs
                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


                    TabSpec tab1 = tabHost.newTabSpec("First Tab");
                    TabSpec tab2 = tabHost.newTabSpec("Second Tab");
                    TabSpec tab3 = tabHost.newTabSpec("Third tab");

                   // Set the Tab name and Activity
                   // that will be opened when particular Tab will be selected
                    tab1.setIndicator("Tab1");
                    tab1.setContent(new Intent(this,G1_TabtestSub1Activity.class));
                    
                    tab2.setIndicator("Tab2");
                    tab2.setContent(new Intent(this,G2_TabtestSub2Activity.class));

                    tab3.setIndicator("Tab3");
                    tab3.setContent(new Intent(this,G3_TabtestSub3Activity.class));
                    
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);
                    tabHost.addTab(tab3);
                    
                    Button btn = (Button)ab.getCustomView().findViewById(R.id.actionBtn);
                    btn.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(G_TabtestActivity.this, "ธÞที", Toast.LENGTH_SHORT).show();
							//Intent i = new Intent(TabtestActivity.this,TabtestSub1Activity.class);
							//startActivity(i);
							//Activity act = (Activity)getTabHost().getChildAt(0).getA;
							//Log.v(TAG, "getChildAt0="+act);
							//Activity act = (Activity)getTabHost().getChildAt(1).getContext();
							//Log.v(TAG, "getChildAt1="+act);
							//act.openOptionsMenu();
						}
					});
            }

          
} 