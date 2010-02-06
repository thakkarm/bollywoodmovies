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

import com.util.CommonConstants;

public class NewsData
{

    // The XML file elements
    public static final String NEWS = "News";
    public static final String NEWS_ENTRY = "NewsEntry";
    public static final String NEWS_DATE = "Date";
    public static final String NEWS_TITLE = "Title";
    public static final String NEWS_BODY = "Body";

    // | -----------------------------------------------------------------------
    // | Public Class Operations
    // | -----------------------------------------------------------------------

    public NewsData copy()
    {
        NewsData copy = new NewsData();
        copy.mDate = mDate;
        copy.mTitle = mTitle;
        copy.mBody = mBody;
        return copy;
    }

    /**
     * @return the mDate
     */
    public String getDate()
    {
        return mDate;
    }

    /**
     * @param date
     *            the mDate to set
     */
    public void setDate(String date)
    {
        this.mDate = date;
    }

    /**
     * @return the mTitle
     */
    public String getTitle()
    {
        return mTitle;
    }

    /**
     * @param title
     *            the mTitle to set
     */
    public void setTitle(String title)
    {
        this.mTitle = title;
    }

    /**
     * @return the mBody
     */
    public String getBody()
    {
        return mBody;
    }

    /**
     * @param body
     *            the mBody to set
     */
    public void setBody(String body)
    {
        this.mBody = body;
    }

    public String toString()
    {
        String returnStr = CommonConstants.EMPTY_STRING;

        returnStr = CommonConstants.TO_STRING_SEPERATOR;
        
        returnStr = returnStr + "mDate = " + this.mDate
                + CommonConstants.NEW_LINE + "mTitle = " + this.mTitle
                + CommonConstants.NEW_LINE + "mBody = " + this.mBody;
        
        returnStr = returnStr + CommonConstants.NEW_LINE
                + CommonConstants.TO_STRING_SEPERATOR;

        return returnStr;
    }

    // | -----------------------------------------------------------------------
    // | Private Class Attributes
    // | -----------------------------------------------------------------------
    private String mDate = CommonConstants.EMPTY_STRING;
    private String mTitle = CommonConstants.EMPTY_STRING;
    private String mBody = CommonConstants.EMPTY_STRING;
}
