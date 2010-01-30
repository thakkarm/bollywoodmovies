package com.bollywoodmovies;

import java.io.InputStream;

import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;

import com.bollywoodmovies.config.Configuration;
import com.bollywoodmovies.listeners.AboutUsButtonListener;
import com.bollywoodmovies.listeners.NewsButtonListener;
import com.bollywoodmovies.listeners.PhotoGalleryButtonListener;
import com.util.CommonConstants;

public class MainApp
{
	//| -----------------------------------------------------------------------
	//| Public Operations
	//| -----------------------------------------------------------------------
	public static MainApp getInstance()
	{
		if (null == instance)
		{
			// Create the one and only instance of the object
			instance = new MainApp();
		}
		return instance;
	}

	public String getURLBollywoodActress(String nameOfPerson)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
				+ "::getURLBollywoodActress()");

		// | Covert the selected item to lowercase and replace spaces with _
		String personNameLowerCase = nameOfPerson.toLowerCase();
		String personNameLowerCaseWithUnderscores = personNameLowerCase.replace(" ", "_");
		Log.d(CommonConstants.LOG_TAG, "nameOfPerson Modified : [ "
				+ personNameLowerCaseWithUnderscores + " ]");
		Log.d(CommonConstants.LOG_TAG, "Num : [ "
				+ mCurrentPersonIndex + " ]");

		String url = PIC_URL.toString();
		url = url.replaceAll(TAG_DIR_PERSON_NAME, personNameLowerCaseWithUnderscores);
		String num = CommonConstants.EMPTY_STRING;
		if (mCurrentPersonIndex < 10)
		{
			num = "0" + Long.toString(mCurrentPersonIndex);			
		}
		else 
		{
			num = Long.toString(mCurrentPersonIndex);			
		}
		url = url.replaceAll(TAG_PERSON_NAME_ID, num);
		
		Log.d(CommonConstants.LOG_TAG, "URL : [ "
				+ url + " ]");

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
				+ "::getURLBollywoodActress()");
		return url;
	}

	public void handleException(Exception exception)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
				+ "::handleException()");
		
		//TODO
		// Add popup that application error, try again...
		
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
				+ "::handleException()");
	}
	
	public boolean createOptionMenu(Menu menu)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + MainApp.class
				+ "::onCreateOptionsMenu()");

		// This menus are display when the menu button is clicked while app is running
		menu.add("Menu 1");
		menu.add(1, 1, MENU_1_ID, "Menu 4");
		
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + MainApp.class
				+ "::onCreateOptionsMenu()");
		return true;
	}

	/**
	 * @param mSplashActivity the mSplashActivity to set
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
	 * @param mMenuActivity the mMenuActivity to set
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
	 * @param mPhotoGalleryActivity the mPhotoGalleryActivity to set
	 */
	public void setPhotoGalleryActivity(PhotoGalleryActivity mPhotoGalleryActivity)
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


	//| -----------------------------------------------------------------------
	//| Private Class Operations
	//| -----------------------------------------------------------------------
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
	 * @param mNewsActivity the mNewsActivity to set
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
	 * @param mAboutUsActivity the mAboutUsActivity to set
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
	 * @param mCurrentPersonName the mCurrentPersonName to set
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
	 * @param mCurrentPersonIndex the mCurrentPersonIndex to set
	 */
	public void setCurrentPersonIndex(long currentPersonIndex)
	{
		this.mCurrentPersonIndex = currentPersonIndex;
	}

	/**
	 * @return the mCurrentPersonIndex
	 */
	public long getCurrentPersonIndex()
	{
		return mCurrentPersonIndex;
	}
	
	public void init()
	{
		if (mInited == false)
		{
			//TODO
			// Check if HTTP or WiFi is available if not, popup error and exit
			
			// Get the local configuration file from the resource dir
			//setLocalXml(getSplashActivity().getApplicationContext().getResources().openRawResource(R.raw.celebrities));			

			Configuration config = Configuration.getInstance();
			config.loadCelebrityConfig();
			mInited = true;
		}
	}
	
	/**
	 * @param mLocalXml the mLocalXml to set
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

	//| -----------------------------------------------------------------------
	//| Private Class Attributes
	//| -----------------------------------------------------------------------
	private static MainApp	instance = null;

	private SplashActivity	mSplashActivity	= null;
	private AboutUsActivity mAboutUsActivity = null;
	private NewsActivity mNewsActivity = null;
	private MenuActivity mMenuActivity = null;
	private PhotoGalleryActivity mPhotoGalleryActivity = null;

	private PhotoGalleryButtonListener mPhotoGalleryButtonListener = null;
	private AboutUsButtonListener mAboutUsButtonListener = null;
	private NewsButtonListener mNewsButtonListener = null;

	static final String	BOLLYWOOD_MOVIES_URL	= "http://www.bollywoodmovies.us/";

	private static int MENU_1_ID = 1;
	
	private InputStream mLocalXml = null;
	
	private String mCurrentPersonName = CommonConstants.EMPTY_STRING;
	private long mCurrentPersonIndex = 0;
	
	private boolean mInited = false;
	
	static String TAG_DIR_PERSON_NAME = "TAG_DIR_PERSON_NAME";
	static String TAG_PERSON_NAME_ID = "TAG_PERSON_NAME_ID";
	
	static String PIC_URL = "http://www.bollywoodmovies.us/actress/TAG_DIR_PERSON_NAME/pics/TAG_DIR_PERSON_NAME_TAG_PERSON_NAME_ID.jpg";

	// TODO
	// Set flag to false before release and also remove some repeat logging
	static boolean DEBUG = true;
}
