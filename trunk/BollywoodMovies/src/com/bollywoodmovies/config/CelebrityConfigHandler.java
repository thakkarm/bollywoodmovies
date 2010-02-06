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

import com.util.CommonConstants;

public class CelebrityConfigHandler extends DefaultHandler
{
	private List<CelebrityData>	mCelebrities;
	private CelebrityData		mCurrentCelebrity;
	private StringBuilder		mBuilder;

	public List<CelebrityData> getCelebrities()
	{
		return this.mCelebrities;
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
		if (this.mCurrentCelebrity != null)
		{
			if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY_NAME))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				mCurrentCelebrity.setName(attrValue);
			}
			else if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY_CATAGORY))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				mCurrentCelebrity.setCatagory(attrValue);
			}
			else if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY_NUM_OF_PICS))
			{
				String attrValue = mBuilder.toString();
				attrValue = attrValue.replace('\n', ' ');
				attrValue= attrValue.trim();
				int numOfPics = Integer.parseInt(attrValue);
				mCurrentCelebrity.setNumOfPics(numOfPics);
			}
			else if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY_ENTRY))
			{
				mCelebrities.add(mCurrentCelebrity);
				System.out.println(mCurrentCelebrity.toString());
			}
			mBuilder.setLength(0);
		}
	}

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		mCelebrities = new ArrayList<CelebrityData>();
		mBuilder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes)
			throws SAXException
	{
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY))
		{
			// Do nothings this is the root element tag
		}
		if (localName.equalsIgnoreCase(CelebrityData.CELEBRITY_ENTRY))
		{
			this.mCurrentCelebrity = new CelebrityData();
		}
	}
}