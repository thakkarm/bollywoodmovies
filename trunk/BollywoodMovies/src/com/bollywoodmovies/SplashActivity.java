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
 ******************************************************************************/package com.bollywoodmovies;

// import com.mydomain.appone.R;
import com.bollywoodmovies.R;
import com.util.CommonConstants;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SplashActivity extends BaseApplicationActivity
{
	// | -----------------------------------------------------------------------
	// | Private Class Attributes
	// | -----------------------------------------------------------------------

	// | -----------------------------------------------------------------------
	// | Public Operations
	// | -----------------------------------------------------------------------
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + SplashActivity.class
				+ "::onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		MainApp.getInstance().setSplashActivity(this);

		/*
		// do what you want to do before sleeping
		try
		{
			Thread.currentThread();
			// Sleep for 5000 ms
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + SplashActivity.class
				+ "::onCreate()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onStart()
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + SplashActivity.class
				+ "::onStart()");
		super.onStart();

		// Display the logo
		ImageView logoView = (ImageView) findViewById(R.id.LogoView);
		logoView.setImageResource(R.drawable.logo);

		Intent intent = new Intent(this.getBaseContext(), MenuActivity.class);
		MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);

		MainApp.getInstance().init();

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + SplashActivity.class
				+ "::onStart()");
	}

    public void onTerminate() {
        // clean up application global
        //super.onStop();
        super.onDestroy();
        this.finish();        // /// close the application
    }
}
