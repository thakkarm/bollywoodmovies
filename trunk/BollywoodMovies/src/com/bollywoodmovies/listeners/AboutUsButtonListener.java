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
package com.bollywoodmovies.listeners;

import com.bollywoodmovies.AboutUsActivity;
import com.bollywoodmovies.MainApp;
import com.util.CommonConstants;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class AboutUsButtonListener implements OnClickListener
{

	@Override
	public void onClick(View view)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + AboutUsButtonListener.class + "::onClick()");

		if (null != view)
		{
			Intent intent = new Intent(view.getContext(), AboutUsActivity.class);
			MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);
		}
		else
		{
			Log.e(CommonConstants.LOG_TAG, "View is null");
		}
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + AboutUsButtonListener.class + "::onClick()");
	}

}
