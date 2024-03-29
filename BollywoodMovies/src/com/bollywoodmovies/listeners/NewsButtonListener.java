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

import java.util.List;

import com.bollywoodmovies.MainApp;
import com.bollywoodmovies.NewsListView;
import com.bollywoodmovies.config.Configuration;
import com.bollywoodmovies.config.NewsData;
import com.util.CommonConstants;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class NewsButtonListener implements OnClickListener
{

	@Override
	public void onClick(View view)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsButtonListener.class + "::onClick()");

		if (null != view)
		{
	        Configuration config = Configuration.getInstance();
	        List<NewsData> newsList = config.getNewsList();
            //| Load the new the first time
	        if (null == newsList)
	        {
	            config.loadNewsConfig();
	        }
		    
			Intent intent = new Intent(view.getContext(), NewsListView.class);
            if (null != MainApp.getInstance().getSplashActivity())
            {                
                MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);
            }
            else
            {
                Log.e(CommonConstants.LOG_TAG, "getSplashActivity() returns null");
            }

			//Intent intent = new Intent(view.getContext(), HelloListView.class);
			//MainApp.getInstance().getSplashActivity().startActivityForResult(intent, 0);
			
		}
		else
		{
			Log.e(CommonConstants.LOG_TAG, "View is null");
		}
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsButtonListener.class + "::onClick()");
	}

}
