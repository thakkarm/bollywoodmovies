package com.bollywoodmovies;

import com.util.CommonConstants;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class AboutUsActivity extends BaseApplicationActivity
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + AboutUsActivity.class + "::onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);

	    MainApp.getInstance().setAboutUsActivity(this);

	    //| Create and initialize the footer buttons
	    createFooterNavigationButton();

	    // TODO
	    // Add email address, search online on how to send email from app
	    TextView aboutUsText = (TextView) findViewById(R.id.AboutUsText);
	    aboutUsText.setText("If you have suggestion please email us at: ");
	    
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + AboutUsActivity.class + "::onCreate()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onStart()
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + AboutUsActivity.class
				+ "::onStart()");
		super.onStart();

		// Display the logo
		ImageView logoView = (ImageView) findViewById(R.id.LogoView);
		logoView.setMinimumHeight(200);
		logoView.setMinimumWidth(200);
		logoView.setImageResource(R.drawable.appaboutus);

		MainApp.getInstance().init();

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + AboutUsActivity.class
				+ "::onStart()");
	}
	
}
