/*******************************************************************************
 * CLASS:
 * 
 * DESCRIPTION:
 * 
 * NOTES:
 * 
 ******************************************************************************/

/*******************************************************************************
 * 
 * $Revision$
 * $LastChangedBy$
 * $Date$
 * $Id:$
 * 
 ******************************************************************************/package com.bollywoodmovies;

import com.util.CommonConstants;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ActressListActivity extends ListActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + ActressListActivity.class + "::onCreate()");
	  super.onCreate(savedInstanceState);
	  
	  setListAdapter(new ArrayAdapter<String>(this,
			  R.id.PhotoListView, ACTRESSES));
	  getListView().setTextFilterEnabled(true);
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + ActressListActivity.class + "::onCreate()");
	}

	  static final String[] ACTRESSES = new String[] {
		    "Aishwarya Rai", 
		    "Kareen Kapoor"
	  };
}
