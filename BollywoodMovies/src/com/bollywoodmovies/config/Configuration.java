package com.bollywoodmovies.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.bollywoodmovies.MainApp;
import com.util.CommonConstants;

public class Configuration
{
	static String	CONFIG_CELEBRITY	= "http://www.bollywoodmovies.us/android/app/bollywood/config/celebrities.xml";

	// | -----------------------------------------------------------------------
	// | Public Operations
	// | -----------------------------------------------------------------------
	public static Configuration getInstance()
	{
		if (null == instance)
		{
			// Create the one and only instance of the object
			instance = new Configuration();
		}
		return instance;
	}

	public void loadCelebrityConfig()
	{
		URL url;
		try
		{
			url = new URL(CONFIG_CELEBRITY);

			// Get a SAXParser from the SAXPArserFactory.
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			// Get the XMLReader of the SAXParser we created.
			XMLReader xr = sp.getXMLReader();
			// Create a new ContentHandler and apply it to the XML-Reader
			CelebrityConfigHandler celebrityConfigHandler = new CelebrityConfigHandler();
			xr.setContentHandler(celebrityConfigHandler);

// Prints the configuration file
			/*
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strLine = CommonConstants.EMPTY_STRING;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null)
			{
				System.out.println(strLine);
			}
*/
			/*
			//| Load the local configuration
			InputSource inputSourceLocal = new InputSource(MainApp.getInstance().getLocalXml());
			// Parse the xml-data from our URL
			xr.parse(inputSourceLocal);
			*/
			
			try {
				//| Load the online configuration
				InputSource inputSource = new InputSource(url.openStream());

				// Parse the xml-data from our URL
				xr.parse(inputSource);				
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				MainApp.getInstance().handleException(e);				
			}

			// Provides the parsed data to us.
			mCelebrities = celebrityConfigHandler.getCelebrities();

		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		}

	}

	public List<CelebrityData> getCelebrities()
	{
		return this.mCelebrities;
	}

	public CelebrityData getCelebrityData(String celebrityName)
	{
		CelebrityData foundCelebrityData = null;
		ArrayList<CelebrityData> listOfCelebrities = new ArrayList<CelebrityData>(mCelebrities);

		for (Iterator<CelebrityData> it = listOfCelebrities.iterator(); it.hasNext();)
		{
			CelebrityData celebrityData = (CelebrityData) it.next();
			if (celebrityData.getName().equals(celebrityName))
			{
				foundCelebrityData = celebrityData;
				Log.i(CommonConstants.LOG_TAG, "Found CelebrityData : ---------- \n" + celebrityData.toString());				
			}
		}
		return foundCelebrityData;
	}

	// | -----------------------------------------------------------------------
	// | Private Class Attributes
	// | -----------------------------------------------------------------------
	private static Configuration	instance	= null;

	private List<CelebrityData>		mCelebrities;

}
