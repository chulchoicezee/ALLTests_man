package com.android.tests.activities;


import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.tests.R;

public class O_SocketIOTestActivity extends Activity{

	private static final String TAG = "N_AABFragTestActivity";
	TextView tv = null;
	Button btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socketio);
		tv = (TextView)findViewById(R.id.textView1);
		btn = (Button)findViewById(R.id.button1);
			Log.v(TAG, "onCreate tv="+tv);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//IntentFilter newChatMessageFilter = new IntentFilter("newChatMessage");
			    //O_SocketIOTestActivity.this.registerReceiver(new MessageReceiver(), newChatMessageFilter);
			    
			    new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
						SocketIO socket = null;
						try {
							socket = new SocketIO("http://chulchoice.cafe24app.com:80");
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        socket.connect(new IOCallback() {
				            @Override
				            public void onMessage(JSONObject json, IOAcknowledge ack) {
				                try {
				                    System.out.println("Server said:" + json.toString(2));
				                } catch (JSONException e) {
				                    e.printStackTrace();
				                }
				            }

				            @Override
				            public void onMessage(String data, IOAcknowledge ack) {
				                System.out.println("Server said: " + data);
				            }

				            @Override
				            public void onError(SocketIOException socketIOException) {
				                System.out.println("an Error occured");
				                socketIOException.printStackTrace();
				            }

				            @Override
				            public void onDisconnect() {
				                System.out.println("Connection terminated.");
				            }

				            @Override
				            public void onConnect() {
				                System.out.println("Connection established");
				            }

				            @Override
				            public void on(String event, IOAcknowledge ack, Object... args) {
				                System.out.println("Server triggered event '" + event + "'");
				            }
				        });

				        // This line is cached until the connection is establisched.
				        socket.send("Hello Server!");
				        
						return null;
					}
				}.execute();
			}
		});
	}

	class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            final String chatMessage =(String) intent.getExtras().get("chatMessage");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setText(chatMessage);
                    //mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
