package com.bollywoodmovies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.bollywoodmovies.config.CelebrityData;
import com.bollywoodmovies.config.Configuration;
import com.util.CommonConstants;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PhotoListView extends ListActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + PhotoListView.class
				+ "::onCreate()");

		super.onCreate(savedInstanceState);

		Configuration config = Configuration.getInstance();
		ArrayList<CelebrityData> listOfCelebrities = new ArrayList<CelebrityData>(config
				.getCelebrities());

		mListOfCelebritiesStr = new String[listOfCelebrities.size()];
		int celebrityCount = 0;

		for (Iterator<CelebrityData> it = listOfCelebrities.iterator(); it.hasNext();)
		{
			CelebrityData celebrityData = (CelebrityData) it.next();
			Log.i(CommonConstants.LOG_TAG, "CelebrityData : ---------- \n" + celebrityData.toString());
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

		if (selectedItem == MAIN_MENU)
		{
			Log.d(CommonConstants.LOG_TAG, "Open Main Menu...");
			Intent intent = new Intent(view.getContext(), MenuActivity.class);
			this.startActivityForResult(intent, 0);
		}
		else
		{
			MainApp mainApp = MainApp.getInstance();
			if (selectedItem.equals(MainApp.getInstance().getCurrentPersonName()))
			{
				mainApp.setCurrentPersonIndex(id);
			}
			else
			{
				mainApp.setCurrentPersonIndex(1);
			}

			mainApp.setCurrentPersonName(selectedItem);

			Intent intent = new Intent(view.getContext(), Photo.class);
			mainApp.getSplashActivity().startActivityForResult(intent, 0);

			// String url = this.createURL(position, id);
			// openURL(url);
		}
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::onListItemClick()");
	}

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

		MainApp.getInstance().setCurrentPersonIndex(id);
		MainApp.getInstance().setCurrentPersonName(selectedItem);

		url = MainApp.getInstance().getURLBollywoodActress(selectedItem);
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + PhotoListView.class
				+ "::createURL()");
		return url;
	}

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
