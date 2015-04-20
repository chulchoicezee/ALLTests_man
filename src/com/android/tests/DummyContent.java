package com.android.tests;

import java.util.ArrayList;
import java.util.List;

public class DummyContent {

	public static class DummyItem{
		String id;
		String name;
		DummyItem(String id, String name){
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}
		
	}
	
	public static List<DummyItem> LISTITEM = new ArrayList<DummyItem>();

	static{
		LISTITEM.add(new DummyItem("1", "chulgee1"));
		LISTITEM.add(new DummyItem("2", "chulgee2"));
		LISTITEM.add(new DummyItem("3", "chulgee3"));
	}
	
}
