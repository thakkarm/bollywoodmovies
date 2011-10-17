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

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

import com.bollywoodmovies.config.CelebrityData;
import com.bollywoodmovies.config.Configuration;
import com.bollywoodmovies.listeners.AboutUsButtonListener;
import com.bollywoodmovies.listeners.NewsButtonListener;
import com.bollywoodmovies.listeners.PhotoGalleryButtonListener;
import com.util.BaseException;
import com.util.CommonConstants;
import com.util.XMLFileLoaderException;

public class MainApp
{
    // | -----------------------------------------------------------------------
    // | Public Operations
    // | -----------------------------------------------------------------------
    public static MainApp getInstance()
    {
        if (null == instance)
        {
            // Create the one and only instance of the object
            instance = new MainApp();
        }
        return instance;
    }

    public String getURLBollywoodCelebrity(CelebrityData selectedCelebrity)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
                + "::getURLBollywoodActress()");
        
        String url = CommonConstants.EMPTY_STRING;
        
        if (null != selectedCelebrity.getName())
        {
            // | Covert the selected item to lowercase and replace spaces with _
            String personNameLowerCase = selectedCelebrity.getName().toLowerCase();
            String personNameLowerCaseWithUnderscores = personNameLowerCase
                    .replace(" ", "_");
            Log.d(CommonConstants.LOG_TAG, "nameOfPerson Modified : [ "
                    + personNameLowerCaseWithUnderscores + " ]");
            Log.d(CommonConstants.LOG_TAG, "Num : [ " + mCurrentImageShownNum + " ]");

            url = PIC_URL.toString();
            url = url.replaceAll(TAG_DIR_PERSON_NAME,
                    personNameLowerCaseWithUnderscores);
            url = url.replaceAll(TAG_DIR_CATAGORY,
                    selectedCelebrity.getCatagory().toLowerCase());
            
            String num = CommonConstants.EMPTY_STRING;
            if (mCurrentImageShownNum < 10)
            {
                num = "0" + Long.toString(mCurrentImageShownNum);
            } else
            {
                num = Long.toString(mCurrentImageShownNum);
            }
            url = url.replaceAll(TAG_PERSON_NAME_ID, num);

            Log.d(CommonConstants.LOG_TAG, "URL : [ " + url + " ]");            
        }
        else
        {
            Log.e(CommonConstants.LOG_TAG, "selectedCelebrity.getName() returns null");           
        }
        
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
                + "::getURLBollywoodActress()");
        return url;
    }

    public void handleException(Exception exception)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
                + "::handleException()");

        Log.e(CommonConstants.LOG_TAG,
                "-----------------------------------------------");
        Log.e(CommonConstants.LOG_TAG, exception.toString());

        // TODO
        // Add popup that application error, try again...
        // **** Current sending to About page but need to FIX.....
        Context context = mSplashActivity.getApplicationContext();
        // Intent intent = new Intent(context, AboutUsActivity.class);
        // MainApp.getInstance().getSplashActivity().startActivityForResult(
        // intent, 0);

        BaseAlertDialog alertDialog = new BaseAlertDialog(context);
        alertDialog.show();

        Log.e(CommonConstants.LOG_TAG,
                "-----------------------------------------------");

        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
                + "::handleException()");
    }

    public boolean createOptionMenu(Menu menu)
    {
        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
                + "::onCreateOptionsMenu()");

        // TODO
        // This menus are display when the menu button is clicked while app is
        // running
        // menu.add("Menu 1");
        // menu.add("Menu 2");
        // menu.add(1, 1, MENU_1_ID, "Menu 4");

        Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
                + "::onCreateOptionsMenu()");
        return true;
    }

    /**
     * @param mSplashActivity
     *            the mSplashActivity to set
     */
    public void setSplashActivity(SplashActivity mSplashActivity)
    {
        this.mSplashActivity = mSplashActivity;
    }

    /**
     * @return the mSplashActivity
     */
    public SplashActivity getSplashActivity()
    {
        return mSplashActivity;
    }

    /**
     * @param mMenuActivity
     *            the mMenuActivity to set
     */
    public void setMenuActivity(MenuActivity mMenuActivity)
    {
        this.mMenuActivity = mMenuActivity;
    }

    /**
     * @return the mMenuActivity
     */
    public MenuActivity getMenuActivity()
    {
        return mMenuActivity;
    }

    /**
     * @param mPhotoGalleryActivity
     *            the mPhotoGalleryActivity to set
     */
    public void setPhotoGalleryActivity(
            PhotoGalleryActivity mPhotoGalleryActivity)
    {
        this.mPhotoGalleryActivity = mPhotoGalleryActivity;
    }

    /**
     * @return the mPhotoGalleryActivity
     */
    public PhotoGalleryActivity getPhotoGalleryActivity()
    {
        return mPhotoGalleryActivity;
    }

    // | -----------------------------------------------------------------------
    // | Private Class Operations
    // | -----------------------------------------------------------------------
    private MainApp()
    {
        mPhotoGalleryButtonListener = new PhotoGalleryButtonListener();
        mAboutUsButtonListener = new AboutUsButtonListener();
        mNewsButtonListener = new NewsButtonListener();
    }

    /**
     * @return the mPhotoGalleryButtonListener
     */
    public PhotoGalleryButtonListener getPhotoGalleryButtonListener()
    {
        return mPhotoGalleryButtonListener;
    }

    /**
     * @return the mAboutUsButtonListener
     */
    public AboutUsButtonListener getAboutUsButtonListener()
    {
        return mAboutUsButtonListener;
    }

    /**
     * @return the mNewsButtonListener
     */
    public NewsButtonListener getNewsButtonListener()
    {
        return mNewsButtonListener;
    }

    /**
     * @param mNewsActivity
     *            the mNewsActivity to set
     */
    public void setNewsActivity(NewsActivity mNewsActivity)
    {
        this.mNewsActivity = mNewsActivity;
    }

    /**
     * @return the mNewsActivity
     */
    public NewsActivity getNewsActivity()
    {
        return mNewsActivity;
    }

    /**
     * @param mAboutUsActivity
     *            the mAboutUsActivity to set
     */
    public void setAboutUsActivity(AboutUsActivity mAboutUsActivity)
    {
        this.mAboutUsActivity = mAboutUsActivity;
    }

    /**
     * @return the mAboutUsActivity
     */
    public AboutUsActivity getAboutUsActivity()
    {
        return mAboutUsActivity;
    }

    /**
     * @param mCurrentPersonName
     *            the mCurrentPersonName to set
     */
    public void setCurrentPersonName(String currentPersonName)
    {
        this.mCurrentPersonName = currentPersonName;
    }

    /**
     * @return the mCurrentPersonName
     */
    public String getCurrentPersonName()
    {
        return mCurrentPersonName;
    }

    /**
     * @param currentImageShownNum
     *            the currentImageShownNum to set
     */
    public void setCurrentImageShownNum(long currentImageShownNum)
    {
        this.mCurrentImageShownNum = currentImageShownNum;
    }

    /**
     * @return the mCurrentPersonIndex
     */
    public long getCurrentImageShownNum()
    {
        return mCurrentImageShownNum;
    }

    /**
     * @param mCurrentNewsIndex
     *            the mCurrentNewsIndex to set
     */
    public void setCurrentNewsIndex(int mCurrentNewsIndex)
    {
        this.mCurrentNewsIndex = mCurrentNewsIndex;
    }

    /**
     * @return the mCurrentNewsIndex
     */
    public int getCurrentNewsIndex()
    {
        return mCurrentNewsIndex;
    }

    /**
     * @param mCurrentSelectedCelebrity the mCurrentSelectedCelebrity to set
     */
    public void setCurrentSelectedCelebrity(CelebrityData mCurrentSelectedCelebrity)
    {
        this.mCurrentSelectedCelebrity = mCurrentSelectedCelebrity;
    }

    /**
     * @return the mCurrentSelectedCelebrity
     */
    public CelebrityData getCurrentSelectedCelebrity()
    {
        return mCurrentSelectedCelebrity;
    }

    public void init() throws BaseException
    {
        if (mInited == false)
        {
            // TODO
            // Check if HTTP or WiFi is available if not, popup error and exit

            // Get the local configuration file from the resource dir
            // setLocalXml(getSplashActivity().getApplicationContext().getResources().openRawResource(R.raw.celebrities));

            Configuration config = Configuration.getInstance();
            config.loadCelebrityConfig();
            // NOTE: News config being loaded on NewsButtonListener:onClick on 1st click
            //config.loadNewsConfig();
            mInited = true;
        }
    }

    /**
     * @param mLocalXml
     *            the mLocalXml to set
     */
    public void setLocalXml(InputStream mLocalXml)
    {
        this.mLocalXml = mLocalXml;
    }

    /**
     * @return the mLocalXml
     */
    public InputStream getLocalXml()
    {
        return mLocalXml;
    }

    /**
     * 
     * Method to send email
     */

    protected void sendEmail()
    {

        // Setup the recipient in a String array
        String mailto = "info@bollywoodmovies.us";
        String subject = "Suggestion";
        String body = "user fillin";

        // Create a new Intent to send messages
        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        // Add attributes to the intent
        sendIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);
        sendIntent.setType("text/plain");

        mAboutUsActivity.startActivity(Intent.createChooser(sendIntent,
                "SendEmail"));
    }

    // | -----------------------------------------------------------------------
    // | Private Class Attributes
    // | -----------------------------------------------------------------------
    private static MainApp instance = null;

    private SplashActivity mSplashActivity = null;
    private AboutUsActivity mAboutUsActivity = null;
    private NewsActivity mNewsActivity = null;
    private MenuActivity mMenuActivity = null;
    private PhotoGalleryActivity mPhotoGalleryActivity = null;

    private PhotoGalleryButtonListener mPhotoGalleryButtonListener = null;
    private AboutUsButtonListener mAboutUsButtonListener = null;
    private NewsButtonListener mNewsButtonListener = null;

    static final String BOLLYWOOD_MOVIES_URL = "http://www.bollywoodmovies.us/";

    private InputStream mLocalXml = null;

    private String mCurrentPersonName = CommonConstants.EMPTY_STRING;
    private long mCurrentImageShownNum = CommonConstants.DEFAULT_LONG;
    private CelebrityData mCurrentSelectedCelebrity = null;
    
    private int mCurrentNewsIndex = CommonConstants.DEFAULT_INT;

    private boolean mInited = false;

    final static String TAG_DIR_PERSON_NAME = "TAG_DIR_PERSON_NAME";
    final static String TAG_PERSON_NAME_ID = "TAG_PERSON_NAME_ID";
    final static String TAG_DIR_CATAGORY = "TAG_DIR_CATAGORY";

    // final static String PIC_URL =
    // "http://www.bollywoodmovies.us/actress/TAG_DIR_PERSON_NAME/pics/TAG_DIR_PERSON_NAME_TAG_PERSON_NAME_ID.jpg";
    // final static String PIC_URL =
    // "http://www.bollywoodmovies.us/actress/TAG_DIR_PERSON_NAME/pictures/TAG_DIR_PERSON_NAME_TAG_PERSON_NAME_ID.jpg";
    final static String PIC_URL = "http://www.bollywoodmovies.us/TAG_DIR_CATAGORY/TAG_DIR_PERSON_NAME/pictures/TAG_DIR_PERSON_NAME_TAG_PERSON_NAME_ID.jpg";

    // TODO
    // Set flag to false before release and also remove some repeat logging
    final static boolean DEBUG = true;
}
