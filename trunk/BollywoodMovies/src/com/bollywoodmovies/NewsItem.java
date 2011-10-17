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
 * $Id: Photo.java 21 2010-02-06 04:45:03Z thakkm $
 * 
 ******************************************************************************/
package com.bollywoodmovies;

import com.bollywoodmovies.config.Configuration;
import com.bollywoodmovies.config.NewsData;
import com.util.CommonConstants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewsItem extends BaseApplicationActivity
{

    public void onCreate(Bundle icicle)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsItem.class
                + "::onCreateOptionsMenu()");
        super.onCreate(icicle);
        setContentView(R.layout.newsitem);

        // | Get button from layout
        Button prevButton = (Button) findViewById(R.id.ButtonPrev);

        prevButton.setWidth(100);
        prevButton.setText("Prev");
        prevButton.setOnClickListener(prevOnClickListner);

        // | Get button from layout
        Button nextButton = (Button) findViewById(R.id.ButtonNext);

        nextButton.setWidth(100);
        nextButton.setText("Next");
        nextButton.setOnClickListener(nextOnClickListner);

        Button photoGalleryButton = (Button) findViewById(R.id.ButtonMainMenu);
        photoGalleryButton.setWidth(100);
        photoGalleryButton.setText("Main Menu");
        
        // | Register the onClick listener with the implementation above
        photoGalleryButton.setOnClickListener(mainMenuOnClickListner);

        mGestureDetector = new GestureDetector(this, new GestureListener());

        MainApp mainApp = MainApp.getInstance();
        int currentNewsIndex = mainApp.getCurrentNewsIndex();
        showNewsItem(currentNewsIndex);
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsItem.class
                + "::onCreateOptionsMenu()");
    }

    /**
     * @param currentNewsIndex
     */
    private void showNewsItem(int newsIndex)
    {
        Configuration config = Configuration.getInstance();
        
        NewsData selectedNewsItem = (NewsData) config.getNewsList().get(newsIndex);
        TextView newsDate = (TextView) findViewById(R.id.TextViewNewsDate);
        newsDate.setText(selectedNewsItem.getDate() + "\n--------------------");        
        newsDate.setTextSize(10);

        TextView newsTitle = (TextView) findViewById(R.id.TextViewNewsTitle);
        newsTitle.setText(selectedNewsItem.getTitle() + "\n---------------------------------------------------------------");        
        newsTitle.setTextSize(15);

        TextView newsBody = (TextView) findViewById(R.id.TextViewNewsBody);
        newsBody.setText(selectedNewsItem.getBody());        
        newsBody.setTextSize(20);
    }

    public void showPreviousNewsItem()
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsItem.class
                + "::showPreviousNewsItem()");

        MainApp mainApp = MainApp.getInstance();
        int currentNewsIndex = mainApp.getCurrentNewsIndex();
        int newsListSize = Configuration.getInstance().getNewsListSize();
        Log.d(CommonConstants.LOG_TAG, "Current Index : [ " + currentNewsIndex + "]");
        currentNewsIndex--;
        if (currentNewsIndex < 0)
        {
            currentNewsIndex = newsListSize - 1;
        }
        mainApp.setCurrentNewsIndex(currentNewsIndex);

        showNewsItem(currentNewsIndex);
        
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsItem.class
                + "::showPreviousNewsItem()");
    }

    public void showNextNewsItem()
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsItem.class
                + "::showNewsNewsItem()");

        MainApp mainApp = MainApp.getInstance();
        int currentNewsIndex = mainApp.getCurrentNewsIndex();
        Log.d(CommonConstants.LOG_TAG, "Current Index : [ " + currentNewsIndex + "]");
        currentNewsIndex++;
        int newsListSize = Configuration.getInstance().getNewsListSize();
        if (currentNewsIndex >= newsListSize)
        {
            currentNewsIndex = 0;
        }
        mainApp.setCurrentNewsIndex(currentNewsIndex);

        showNewsItem(currentNewsIndex);
        
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + NewsItem.class
                + "::showNewsNewsItem()");
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector mGestureDetector = null;

    private class GestureListener extends SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY)
        {
            boolean imageMoved = false;
            if (((e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE)
                    && (Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY))
            {
                showNextNewsItem();
                imageMoved = true;
            } else if ((e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE)
                    && (Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY))
            {
                showPreviousNewsItem();
                imageMoved = true;
            }
            return imageMoved;
        }
    }

    OnClickListener mainMenuOnClickListner = new OnClickListener() {
        public void onClick(View view)
        {
            Intent intent = new Intent(view.getContext(), MenuActivity.class);
            startActivityForResult(intent, 0);
        }
    };


    OnClickListener prevOnClickListner = new OnClickListener() {
        public void onClick(View view)
        {
            Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsItem.class
                    + "::prevOnClickListner()");

            showPreviousNewsItem();
            Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT
                    + NewsItem.class + "::prevOnClickListner()");
        }
    };

    OnClickListener nextOnClickListner = new OnClickListener() {
        public void onClick(View view)
        {
            Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + NewsItem.class
                    + "::nextOnClickListner()");

            showNextNewsItem();

            Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT
                    + NewsItem.class + "::nextOnClickListner()");
        }
    };

}