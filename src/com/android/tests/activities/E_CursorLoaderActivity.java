package com.android.tests.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.android.tests.R;

public class E_CursorLoaderActivity extends Activity {
    private static final String TAG = "CursorLoaderActivity";

    final static int LOADER_ID_1ST = 1;
    final static int LOADER_ID_2ND = 2;
    final static int LOADER_ID_3RD = 3;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursorloader);

    }

    @Override
	public void onAttachFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		super.onAttachFragment(fragment);

		if(fragment instanceof CursorLoaderFragment){
			Log.v(TAG, "onAttachFragment CursorLoaderFragment");
		}
	}

	public static class CursorLoaderFragment extends ListFragment implements OnQueryTextListener, LoaderCallbacks<Cursor> {
    	// This is the Adapter being used to display the list's data.
		ACursorAdapter mAdapter;
		// If non-null, this is the current filter the user has provided.
    	String mCurFilter;

    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		Log.v(TAG, "onActivityCreated");
    		// Give some text to display if there is no data.  In a real
    		// application this would come from a resource.
    		setEmptyText("No phone numbers");

    		// We have a menu item to show in action bar.
    		setHasOptionsMenu(true);

    		// Create an empty adapter we will use to display the loaded data.
    		/*mAdapter = new SimpleCursorAdapter(getActivity(),
    		        android.R.layout.simple_list_item_2, null,
    		        new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS },
    		        new int[] { android.R.id.text1, android.R.id.text2 }, 0);*/
    		mAdapter = new ACursorAdapter(getActivity(), null);

    		setListAdapter(mAdapter);

    		// Prepare the loader.  Either re-connect with an existing one,
    		// or start a new one.
    		getLoaderManager().initLoader(LOADER_ID_1ST, null, this);
    	}

    	@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    		// Place an action bar item for searching.
    		MenuItem item = menu.add("Search");
    		item.setIcon(android.R.drawable.ic_menu_search);
    		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    		SearchView sv = new SearchView(getActivity());
    		sv.setOnQueryTextListener(this);
    		item.setActionView(sv);
    	}

    	public boolean onQueryTextChange(String newText) {
    		// Called when the action bar search text has changed.  Update
    		// the search filter, and restart the loader to do a new query
    		// with this filter.
    		mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
    		getLoaderManager().restartLoader(0, null, this);
    		return true;
    	}

    	@Override public boolean onQueryTextSubmit(String query) {
    		// Don't care about this.
    		return true;
    	}

    	@Override public void onListItemClick(ListView l, View v, int position, long id) {
    		// Insert desired behavior here.
    		Log.i("FragmentComplexList", "Item clicked: " + id);
    	}

    	// These are the Contacts rows that we will retrieve.
    	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
    		Contacts._ID,
    		Contacts.DISPLAY_NAME,
    		Contacts.CONTACT_STATUS,
    		Contacts.CONTACT_PRESENCE,
    		Contacts.PHOTO_ID,
    		Contacts.LOOKUP_KEY,
    	};
    	static final String[] CONTACTS_DATA_PROJECTION = new String[] {
    		Contacts._ID,
    		Data.MIMETYPE,
    		Data.DATA1
    	};

    	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    		// This is called when a new Loader needs to be created.  This
    		// sample only has one Loader, so we don't care about the ID.
    		// First, pick the base URI to use depending on whether we are
    		// currently filtering.

    		Uri baseUri;
    		String select;
    		CursorLoader cl;
        	
    		if (mCurFilter != null) {
    		    baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
    		            Uri.encode(mCurFilter));
    		} else {
    		    baseUri = Contacts.CONTENT_URI;
    		}
    		Log.v(TAG, "onCreateLoader id="+id+" baseUri="+baseUri.toString());
    		
    		switch (id){
	    		case LOADER_ID_1ST :
	    			// Now create and return a CursorLoader that will take care of
	        		// creating a Cursor for the data being displayed.
	        		select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
	        		        + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
	        		        + Contacts.DISPLAY_NAME + " != '' ))";
	    			cl = new CursorLoader(getActivity(), baseUri,
	        		        CONTACTS_SUMMARY_PROJECTION, select, null,
	        		        Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
	    			break;
	    		case LOADER_ID_2ND :
	    			select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
	        		        + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
	        		        + Contacts.DISPLAY_NAME + " != '' ))";
	    			cl = new CursorLoader(getActivity(), baseUri,
	        		        CONTACTS_SUMMARY_PROJECTION, select, null,
	        		        Contacts.DISPLAY_NAME + " COLLATE LOCALIZED DESC");
	    			break;
	    		case LOADER_ID_3RD :
	    			select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
	        		        + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
	        		        + Contacts.DISPLAY_NAME + " != '' ))";
	    			cl = new CursorLoader(getActivity(), baseUri,
	        		        CONTACTS_SUMMARY_PROJECTION, select, null,
	        		        Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
	    			break;
    			default:
    				cl = null;
    				break;
    		}
    		
    		return cl;
    	}

    	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    		// Swap the new cursor in.  (The framework will take care of closing the
    		// old cursor once we return.)
    		Log.v(TAG, "onLoadFinished");
    		switch (loader.getId()){
    			case LOADER_ID_1ST :
    				Log.v(TAG, "onLoadFinished LOADER_ID_1ST");
    				getLoaderManager().restartLoader(LOADER_ID_2ND, null, this);
    				break;
    			case LOADER_ID_2ND :
    				
    				Log.v(TAG, "onLoadFinished LOADER_ID_2ND");
    				break;
    		}
    		mAdapter.swapCursor(data);
    	}

    	public void onLoaderReset(Loader<Cursor> loader) {
    		// This is called when the last Cursor provided to onLoadFinished()
    		// above is about to be closed.  We need to make sure we are no
    		// longer using it.
    		Log.v(TAG, "onLoadFinished");
    		
    		mAdapter.swapCursor(null);
    	}
		public class ACursorAdapter extends CursorAdapter{
			Context mContext;
			long contact_id;
			private static final String TAG = "ACursorAdapter";
		    public ACursorAdapter(Context context, Cursor cursor) {
		        super(context, cursor, true);
		        mContext = context;
		    }

			@Override
			public void bindView(View v, Context context, Cursor c) {
				// TODO Auto-generated method stub
				if(v != null){
					ViewCache vc = (ViewCache) v.getTag();
					vc.topTextView.setText(c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME)));
					contact_id = c.getInt(c.getColumnIndex(Contacts._ID));
					Log.v(TAG, "bindView call initLoader contact_id="+contact_id);
					//getLoaderManager().initLoader(1, null, this);
				//check ypcard
				//vc.bottomTextView.setText(sb);
				}

			}

			@Override
			public View newView(Context context, Cursor c, ViewGroup parent) {
				// TODO Auto-generated method stub
				LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View v = vi.inflate(R.layout.list_layout, null);
				ViewCache vc = new ViewCache();
				vc.topTextView = (TextView)v.findViewById(R.id.textView1);
				//vc.bottomTextView = (TextView)v.findViewById(R.id.bottomText);
				v.setTag(vc);
				return v;
			}

			class ViewCache{
				TextView topTextView;
				TextView bottomTextView;
			}

			/*@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle arg1) {
				// TODO Auto-generated method stub
	    		String select = "((mimetype_id = 20) OR (mimetype_id = 21)) AND (data.raw_contact_id = "+contact_id+")";
	    		Log.v(TAG, "onCreateLoader id="+id);
				//select = null;
	    		Uri baseUri = Data.CONTENT_URI;
	    		Log.v(TAG, "onCreateLoader id="+id+" contact_id="+contact_id);

	    		CursorLoader cl = new CursorLoader(getActivity(), baseUri,
						CONTACTS_DATA_PROJECTION, select, null,
	    		        null);
				return cl;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> arg0, Cursor c) {
				// TODO Auto-generated method stub
				if(c != null){
					Log.v(TAG, "onLoadFinished c="+c.getCount());
					if(c.moveToFirst()){
						do{
							String data1 = c.getString(c.getColumnIndex(Data.DATA1));
							Log.v(TAG, "data1="+data1);
						}while(c.moveToNext());
					}
				}
			}

			@Override
			public void onLoaderReset(Loader<Cursor> arg0) {
				// TODO Auto-generated method stub

			}*/
		}
    }
}