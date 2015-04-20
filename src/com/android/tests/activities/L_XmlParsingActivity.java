package com.android.tests.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Button;

public class L_XmlParsingActivity extends Activity{

	static public String TAG = "L_XmlParsingActivity";
	ActionBar mActionBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mActionBar = getActionBar();
		mActionBar.setCustomView(null);
    	mActionBar.setDisplayShowCustomEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
		//mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        Intent i = new Intent();
		if(getIntent() != null)
			Log.v(TAG, "not null");
		else
			Log.v(TAG, "is a null");
		
		ATree Atree = new PineTree();
		
		BTree Btree = new PineTree();
		
		Animal animal = new Animal();
		Animal.Poyuryu po = animal.new Poyuryu();
		
		animal.move();
		Log.v(TAG, "onCreated");
		Dog dog = new Dog();
		dog.move();
	}

	class Animal {
		Button btn = null;
		Animal(){
			Log.v(TAG, "Animal created");
			move();
		}
		void move(){
			Log.v(TAG, "Animal moved");
		}
		class Poyuryu{
			Poyuryu(){
				
			}
		}
	}
	
	class Dog extends Animal{
		Dog(){
			Log.v(TAG, "Dog created");
			move();
		}
		void move(){
			Log.v(TAG, "Dog moved");
			super.move();
			//btn.setTag(null);
		}
	}
	
	interface ATree{
		void AleafShape();
	}

	interface BTree{
		void BleafShape();
	}

	class PineTree implements ATree, BTree{
		public void AleafShape(){
			Log.v(TAG, "AleafShape");
		}

		@Override
		public void BleafShape() {
			// TODO Auto-generated method stub
			Log.v(TAG, "BleafShape");
		}
	}

}

