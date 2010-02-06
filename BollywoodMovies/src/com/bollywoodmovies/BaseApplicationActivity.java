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
 * $Id$
 * 
 ******************************************************************************/

package com.bollywoodmovies;

import com.util.CommonConstants;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class BaseApplicationActivity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + BaseApplicationActivity.class + "::onCreate()");
		super.onCreate(savedInstanceState);
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + BaseApplicationActivity.class + "::onCreate()");
	}

	public void createFooterNavigationButton()
	{
	    //| Get button from layout
	    Button newsGalleryButton = (Button)findViewById(R.id.ButtonNews);
	    newsGalleryButton.setText("News");
	    
	    //| Register the onClick listener with the implementation above
	    newsGalleryButton.setOnClickListener(MainApp.getInstance().getNewsButtonListener());

	    //| Get button from layout
	    Button photoGalleryButton = (Button)findViewById(R.id.ButtonPhotoGallery);
	    photoGalleryButton.setText("Photos");
	    
	    //| Register the onClick listener with the implementation above
	    photoGalleryButton.setOnClickListener(MainApp.getInstance().getPhotoGalleryButtonListener());

	    //| Get button from layout
	    Button aboutUsButton = (Button)findViewById(R.id.ButtonAboutUs);
	    aboutUsButton.setText("About Us");
	    
	    //| Register the onClick listener with the implementation above
	    aboutUsButton.setOnClickListener(MainApp.getInstance().getAboutUsButtonListener());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log.d(CommonConstants.LOG_TAG, "Menu Selected [ " + item.getItemId() + " ]");
		return true;
	}
	
}
