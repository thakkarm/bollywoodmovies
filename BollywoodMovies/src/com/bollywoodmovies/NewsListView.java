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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bollywoodmovies.config.Configuration;
import com.bollywoodmovies.config.NewsData;
import com.util.CommonConstants;

public class NewsListView extends ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsListView.class
                + "::onCreate()");

        super.onCreate(savedInstanceState);

        Configuration config = Configuration.getInstance();
        List<NewsData> newsList = config.getNewsList();
        if (null == newsList)
        {
            //TODO remove???
            BaseAlertDialog alertDialog = new BaseAlertDialog(this);
            alertDialog.show();
            
        }
        ArrayList<NewsData> listOfNews = new ArrayList<NewsData>(newsList);

        mListOfNewsStr = new String[listOfNews.size()];
        int newsCount = 0;

//        Log.i(CommonConstants.LOG_TAG, "NewsData : ---------- \n");
        for (Iterator<NewsData> it = listOfNews.iterator(); it.hasNext();)
        {
            NewsData newsData = (NewsData) it.next();
//          Log.i(CommonConstants.LOG_TAG, newsData.toString());
            mListOfNewsStr[newsCount++] = newsData.getTitle();
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mListOfNewsStr));

        getListView().setTextFilterEnabled(true);

        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsListView.class
                + "::onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsListView.class
                + "::onCreateOptionsMenu()");
        super.onCreateOptionsMenu(menu);

        boolean createMenu = MainApp.getInstance().createOptionMenu(menu);

        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsListView.class
                + "::onCreateOptionsMenu()");

        return createMenu;
    }

    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsListView.class
                + "::onListItemClick()");

        // | If MAIN_MENU start main menu activity
        String selectedItem = mListOfNewsStr[position];
        Log.d(CommonConstants.LOG_TAG, "selectedItem : [ " + selectedItem + " ]");
        Log.d(CommonConstants.LOG_TAG, "currentIndex : [ " + position + " ]");

        if (selectedItem.equals(MAIN_MENU))
        {
            Log.d(CommonConstants.LOG_TAG, "Open Main Menu...");
            Intent intent = new Intent(view.getContext(), MenuActivity.class);
            this.startActivityForResult(intent, 0);
        }
        else
        {
            MainApp mainApp = MainApp.getInstance();
            mainApp.setCurrentNewsIndex(position);

            Intent intent = new Intent(view.getContext(), NewsItem.class);
            mainApp.getSplashActivity().startActivityForResult(intent, 0);
        }
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsListView.class
                + "::onListItemClick()");
    }

    public Object fetch(String address) throws MalformedURLException, IOException
    {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }

    // | -----------------------------------------------------------------------
    // | Private Class Attributes
    // | -----------------------------------------------------------------------
    static final String     MAIN_MENU               = "Main Menu";

    private String[] mListOfNewsStr;
    
}
