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

import com.bollywoodmovies.config.CelebrityData;
import com.bollywoodmovies.config.Configuration;
import com.util.CommonConstants;

public class PhotoListView extends ListActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoListView.class
				+ "::onCreate()");

		super.onCreate(savedInstanceState);

		Configuration config = Configuration.getInstance();
		List<CelebrityData> celebrities = config.getCelebrities();
		if (null == celebrities)
		{
		    //TODO remove???

		    BaseAlertDialog alertDialog = new BaseAlertDialog(this);
		    alertDialog.show();
		    
		}
		ArrayList<CelebrityData> listOfCelebrities = new ArrayList<CelebrityData>(celebrities);

		mListOfCelebritiesStr = new String[listOfCelebrities.size()];
		int celebrityCount = 0;

//        Log.i(CommonConstants.LOG_TAG, "CelebrityData : ---------- \n");
		for (Iterator<CelebrityData> celebritiesIterator = listOfCelebrities.iterator(); celebritiesIterator.hasNext();)
		{
			CelebrityData celebrityData = (CelebrityData) celebritiesIterator.next();
//			Log.i(CommonConstants.LOG_TAG, celebrityData.toString());
			mListOfCelebritiesStr[celebrityCount++] = celebrityData.getName();
		}

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				mListOfCelebritiesStr));

		getListView().setTextFilterEnabled(true);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::onCreate()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoListView.class
				+ "::onCreateOptionsMenu()");
		super.onCreateOptionsMenu(menu);

		boolean createMenu = MainApp.getInstance().createOptionMenu(menu);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::onCreateOptionsMenu()");

		return createMenu;
	}

	protected void onListItemClick(ListView listView, View view, int position, long id)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoListView.class
				+ "::onListItemClick()");

		// | If MAIN_MENU start main menu activity
		String selectedItem = mListOfCelebritiesStr[position];
		Log.d(CommonConstants.LOG_TAG, "selectedItem : [ " + selectedItem + " ]");

		if (selectedItem.equals(MAIN_MENU))
		{
			Log.d(CommonConstants.LOG_TAG, "Open Main Menu...");
			Intent intent = new Intent(view.getContext(), MenuActivity.class);
			this.startActivityForResult(intent, 0);
		}
		else
		{
			MainApp mainApp = MainApp.getInstance();
			//| If the selected item is the same one as last selected Then
			if (selectedItem.equals(MainApp.getInstance().getCurrentPersonName()))
			{
			    //| Continue to show the items from last image shown
			    // No need to update the index
			}
			//| Else
			else
			{
			    //| Reset current person index to image 1
				mainApp.setCurrentImageShownNum(1);
			}

			mainApp.setCurrentPersonName(selectedItem);
			mainApp.setCurrentSelectedCelebrity(Configuration.getInstance().getCelebrity(position));

			Intent intent = new Intent(view.getContext(), Photo.class);
			mainApp.getSplashActivity().startActivityForResult(intent, 0);
		}
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::onListItemClick()");
	}

	/*
	private String createURL(int position, long id)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoListView.class
				+ "::createURL()");

		String url = CommonConstants.EMPTY_STRING;

		Log.d(CommonConstants.LOG_TAG, "Position [ " + Integer.toString(position) + " ]");
		Log.d(CommonConstants.LOG_TAG, "ID : [ " + Long.toString(id) + " ]");
		Log.d(CommonConstants.LOG_TAG, "Item : [ " + mListOfCelebritiesStr[position] + " ]");

		// | Get the selected item from the list of items
		String selectedItem = mListOfCelebritiesStr[position];
		Log.d(CommonConstants.LOG_TAG, "selectedItem : [ " + selectedItem + " ]");

		MainApp.getInstance().setCurrentImageShownNum(id);
		MainApp.getInstance().setCurrentPersonName(selectedItem);
		
		CelebrityData selectedCelebrity = Configuration.getInstance().getCelebrity(position);
		
		url = MainApp.getInstance().getURLBollywoodCelebrity(selectedCelebrity);
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::createURL()");
		return url;
	}
    */
	
	/*
	private void openURL(String urlToOpen)
	{
		String url = BOLLYWOOD_MOVIES_URL;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response;
		HttpEntity entity;
		try
		{
			HttpGet get = new HttpGet(url);
			response = client.execute(get);
			entity = response.getEntity();
			InputStream inputStream = entity.getContent();

			Log.d(CommonConstants.LOG_TAG, inputStream.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String strLine = CommonConstants.EMPTY_STRING;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null)
			{
				// Print the content on the console
				System.out.println(strLine);
			}

			inputStream.close();
			if (entity != null)
			{
				entity.consumeContent();
				Log.d(CommonConstants.LOG_TAG, "Length [ " + entity.getContentLength() + "]");
			}
		}
		catch (Exception e)
		{
			Log.e(CommonConstants.LOG_TAG, e.getMessage(), e);
		}
	}
    */
	
	/*
	 * private Drawable ImageOperations(Context ctx, String url, String
	 * saveFilename) { try { InputStream is = (InputStream)this.fetch(
	 * "http://www.bollywoodmovies.us/actress/aishwarya_rai/pictures/aishwarya_rai_12.jpg"
	 * ); Drawable d = Drawable.createFromStream(is, "src"); return d; } catch
	 * (MalformedURLException e) { e.printStackTrace(); return null; } catch
	 * (IOException e) { e.printStackTrace(); return null; } }
	 */
	public Object fetch(String address) throws MalformedURLException, IOException
	{
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

	// | -----------------------------------------------------------------------
	// | Private Class Attributes
	// | -----------------------------------------------------------------------
	static final String		MAIN_MENU				= "Main Menu";
	//static final String[]	LIST_OF_PHOTOS			= new String[] { MAIN_MENU, "Aishwarya Rai",
	//		"Kareena Kapoor", "Priyanka Chopra", MAIN_MENU };
	static final String		BOLLYWOOD_MOVIES_URL	= "http://www.bollywoodmovies.us/news/2010/01_04_2010.html";

	private String[] mListOfCelebritiesStr;
	
}
