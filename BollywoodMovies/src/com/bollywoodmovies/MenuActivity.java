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

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends BaseApplicationActivity
{
	//| -----------------------------------------------------------------------
	//| Private Class Attributes
	//| -----------------------------------------------------------------------

	//| -----------------------------------------------------------------------
	//| Public Operations
	//| -----------------------------------------------------------------------
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MenuActivity.class + "::onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

	    MainApp.getInstance().setMenuActivity(this);

	    //| Create and initialize the footer buttons
	    createFooterNavigationButton();
	    
	    //| Get footer textview from layout
	    TextView urlFooter = (TextView)findViewById(R.id.TextViewFooterUrl);
	    //urlFooter.setGravity(RelativeLayout.CENTER_HORIZONTAL);
	    urlFooter.setText("www.bollywoodmovies.us");
	    
	    Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MenuActivity.class + "::onCreate()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MenuActivity.class
				+ "::onCreateOptionsMenu()");
		super.onCreateOptionsMenu(menu);

		boolean createMenu = MainApp.getInstance().createOptionMenu(menu);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MenuActivity.class
				+ "::onCreateOptionsMenu()");
		return createMenu;
	}

	/** Called when the activity is first created. */
	@Override
	public void onStart()
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MenuActivity.class
				+ "::onStart()");
		super.onStart();

		//| Initilize the application
        MainApp.getInstance().init();

		// Display the logo
		ImageView logoView = (ImageView) findViewById(R.id.LogoView);
		logoView.setMaxHeight(300);
		logoView.setMaxWidth(300);
		logoView.setAdjustViewBounds(true);
		logoView.setImageResource(R.drawable.logo);

		MainApp.getInstance().init();

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MenuActivity.class
				+ "::onStart()");
	}
	
}
