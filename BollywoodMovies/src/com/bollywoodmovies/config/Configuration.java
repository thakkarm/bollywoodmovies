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
package com.bollywoodmovies.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
import com.util.XMLFileLoaderException;
import com.util.XmlFileLoader;

public class Configuration
{
	static String CONFIG_CELEBRITY = "http://www.bollywoodmovies.us/android/app/bollywood/config/celebrities.xml";
    static String CONFIG_NEWS = "http://www.bollywoodmovies.us/android/app/bollywood/config/news.xml";

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
		// | Create celebrity config file reader handler
		CelebrityConfigHandler celebrityConfigHandler = new CelebrityConfigHandler();

		XmlFileLoader xmlFileLoader = new XmlFileLoader();
		
		// | Load the configuration from the url
		try
        {
            xmlFileLoader.loadXMLFromURL(CONFIG_CELEBRITY, celebrityConfigHandler);

            // | Get the parsed data
            mCelebrities = celebrityConfigHandler.getCelebrities();
            Log.i(CommonConstants.LOG_TAG, "CelebrityData : ---------- \n");
            for (Iterator<CelebrityData> it = mCelebrities.iterator(); it.hasNext();)
            {
                CelebrityData celebrityData = (CelebrityData) it.next();
                Log.i(CommonConstants.LOG_TAG, celebrityData.toString());
            }
            Log.i(CommonConstants.LOG_TAG, "CelebrityData : ---------- \n");
        } catch (XMLFileLoaderException e)
        {
            e.printStackTrace();
            MainApp.getInstance().handleException(e);
        }

	}

    public void loadNewsConfig()
    {
        // | Create celebrity config file reader handler
        NewsConfigHandler newsConfigHandler = new NewsConfigHandler();

        XmlFileLoader xmlFileLoader = new XmlFileLoader();
        
        // | Load the configuration from the url
        try
        {
            xmlFileLoader.loadXMLFromURL(CONFIG_NEWS, newsConfigHandler);

            // | Get the parsed data
            mNews = newsConfigHandler.getNews();
            Log.i(CommonConstants.LOG_TAG, "NewsData : ---------- \n");
            for (Iterator<NewsData> it = mNews.iterator(); it.hasNext();)
            {
                NewsData newsData = (NewsData) it.next();
                Log.i(CommonConstants.LOG_TAG, newsData.toString());
            }
            Log.i(CommonConstants.LOG_TAG, "NewsData : ---------- \n");
        } catch (XMLFileLoaderException e)
        {
            e.printStackTrace();
            MainApp.getInstance().handleException(e);
        }
    }

	private void loadCelebrityConfigOldWorks()
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
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader(url.openStream())); String strLine =
			 * CommonConstants.EMPTY_STRING; // Read File Line By Line while
			 * ((strLine = br.readLine()) != null) {
			 * System.out.println(strLine); }
			 */
			/*
			 * //| Load the local configuration InputSource inputSourceLocal =
			 * new InputSource(MainApp.getInstance().getLocalXml()); // Parse
			 * the xml-data from our URL xr.parse(inputSourceLocal);
			 */

			try
			{
				// | Load the online configuration
				InputSource inputSource = new InputSource(url.openStream());

				// Parse the xml-data from our URL
				xr.parse(inputSource);
			} catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				MainApp.getInstance().handleException(e);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				MainApp.getInstance().handleException(e);
			}

			// Provides the parsed data to us.
			mCelebrities = celebrityConfigHandler.getCelebrities();

		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainApp.getInstance().handleException(e);
		} catch (Exception e)
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

    public CelebrityData getCelebrity(int celebrityIndex)
    {
        return this.mCelebrities.get(celebrityIndex);
    }

    public List<NewsData> getNewsList()
    {
        return this.mNews;
    }

    public NewsData getNewsList(int newsIndex)
    {
        return this.mNews.get(newsIndex);
    }

    public int getNewsListSize()
    {
        return this.mNews.size();
    }

       
	public CelebrityData getCelebrityData(String celebrityName)
	{
		CelebrityData foundCelebrityData = null;
		ArrayList<CelebrityData> listOfCelebrities = new ArrayList<CelebrityData>(
				mCelebrities);

		for (Iterator<CelebrityData> it = listOfCelebrities.iterator(); it
				.hasNext();)
		{
			CelebrityData celebrityData = (CelebrityData) it.next();
			if (celebrityData.getName().equals(celebrityName))
			{
				foundCelebrityData = celebrityData;
				Log.i(CommonConstants.LOG_TAG,
						"Found CelebrityData : ---------- \n"
								+ celebrityData.toString());
			}
		}
		return foundCelebrityData;
	}

	// | -----------------------------------------------------------------------
	// | Private Class Attributes
	// | -----------------------------------------------------------------------
	private static Configuration instance = null;

	private List<CelebrityData> mCelebrities = null;
	private List<NewsData> mNews = null;

}
