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

public class CelebrityData
{
    // The XML file elements
    public static final String CELEBRITY = "Celebrities";
    public static final String CELEBRITY_ENTRY = "CelebrityEntry";
    public static final String CELEBRITY_NAME = "Name";
    public static final String CELEBRITY_CATAGORY = "Catagory";
    public static final String CELEBRITY_NUM_OF_PICS = "NumOfPics";

    // | -----------------------------------------------------------------------
    // | Public Class Operations
    // | -----------------------------------------------------------------------
    /**
     * @param catagory
     *            the mCatagory to set
     */
    public void setCatagory(String catagory)
    {
        this.mCatagory = catagory;
    }

    /**
     * @return the mCatagory
     */
    public String getCatagory()
    {
        return mCatagory;
    }

    /**
     * @param numOfPics
     *            the mNumOfPics to set
     */
    public void setNumOfPics(int numOfPics)
    {
        this.mNumOfPics = numOfPics;
    }

    /**
     * @return the mNumOfPics
     */
    public int getNumOfPics()
    {
        return mNumOfPics;
    }

    /**
     * @param name
     *            the mName to set
     */
    public void setName(String name)
    {
        this.mName = name;
    }

    /**
     * @return the mName
     */
    public String getName()
    {
        return mName;
    }

    public CelebrityData copy()
    {
        CelebrityData copy = new CelebrityData();
        copy.mName = mName;
        copy.mCatagory = mCatagory;
        copy.mNumOfPics = mNumOfPics;
        return copy;
    }

    public String toString()
    {
        String returnStr = CommonConstants.EMPTY_STRING;

        returnStr = CommonConstants.TO_STRING_SEPERATOR;
        
        returnStr = returnStr + "mName = " + this.mName
                + CommonConstants.NEW_LINE + "mCatagory = " + this.mCatagory
                + CommonConstants.NEW_LINE + "mNumOfPics = " + this.mNumOfPics;
       
        returnStr = returnStr + CommonConstants.NEW_LINE
                + CommonConstants.TO_STRING_SEPERATOR;

        return returnStr;
    }

    // | -----------------------------------------------------------------------
    // | Private Class Attributes
    // | -----------------------------------------------------------------------
    private String mName = CommonConstants.EMPTY_STRING;
    private String mCatagory = CommonConstants.EMPTY_STRING;
    private int mNumOfPics = CommonConstants.DEFAULT_INT;
}
