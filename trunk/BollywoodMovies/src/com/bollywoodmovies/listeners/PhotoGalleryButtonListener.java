package com.bollywoodmovies.listeners;

import com.bollywoodmovies.MainApp;
import com.bollywoodmovies.PhotoListView;
import com.util.CommonConstants;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class PhotoGalleryButtonListener implements OnClickListener
{

	@Override
	public void onClick(View view)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoGalleryButtonListener.class + "::onClick()");

		if (null != view)
		{
//			Intent intent = new Intent(view.getContext(), PhotoGalleryActivity.class);
//			MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);
			Intent intent = new Intent(view.getContext(), PhotoListView.class);
			MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);
		}
		else
		{
			Log.e(CommonConstants.LOG_TAG, "View is null");
		}
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoGalleryButtonListener.class + "::onClick()");
	}

}
