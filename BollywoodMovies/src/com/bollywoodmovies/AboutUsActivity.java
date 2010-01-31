package com.bollywoodmovies;

import com.util.CommonConstants;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

	    //| Get button from layout
	    Button sendEmailGalleryButton = (Button)findViewById(R.id.ButtonEmail);
	    sendEmailGalleryButton.setText("Email Us");

		// | Register the onClick listener with the implementation above
	    sendEmailGalleryButton.setOnClickListener(sendEmailClickListner);

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
	
	OnClickListener sendEmailClickListner = new OnClickListener() {
		public void onClick(View view) {
			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + AboutUsActivity.class
					+ "::sendEmailClickListner()");

		    MainApp.getInstance().sendEmail();
		    
			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT
					+ AboutUsActivity.class + "::sendEmailClickListner()");
		}
	};

}
