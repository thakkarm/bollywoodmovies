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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.util.CommonConstants;

public class NewsConfigHandler extends DefaultHandler
{
	private List<NewsData>	mNews;
	private NewsData		mCurrentNews;
	private StringBuilder		mBuilder;

	public List<NewsData> getNews()
	{
		return this.mNews;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		mBuilder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		super.endElement(uri, localName, name);
		if (this.mCurrentNews != null)
		{
			if (localName.equalsIgnoreCase(NewsData.NEWS_DATE))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				mCurrentNews.setDate(attrValue);
			}
			else if (localName.equalsIgnoreCase(NewsData.NEWS_TITLE))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				mCurrentNews.setTitle(attrValue);
			}
			else if (localName.equalsIgnoreCase(NewsData.NEWS_BODY))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				mCurrentNews.setBody(attrValue);
			}
			else if (localName.equalsIgnoreCase(NewsData.NEWS_ENTRY))
			{
				mNews.add(mCurrentNews);
				Log.v(CommonConstants.LOG_TAG, mCurrentNews.toString());
			}
			mBuilder.setLength(0);
		}
	}

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		mNews = new ArrayList<NewsData>();
		mBuilder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes)
			throws SAXException
	{
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(NewsData.NEWS))
		{
			// Do nothings this is the root element tag
		}
		if (localName.equalsIgnoreCase(NewsData.NEWS_ENTRY))
		{
			this.mCurrentNews = new NewsData();
		}
	}
}