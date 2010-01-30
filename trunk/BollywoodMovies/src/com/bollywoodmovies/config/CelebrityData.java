package com.bollywoodmovies.config;

import com.util.CommonConstants;

public class CelebrityData
{
	public static final String	CELEBRITY_ENTRY	= "CelebrityEntry";
	public static final String	CELEBRITY	= "Celebrities";
	public static final String	NAME		= "Name";
	public static final String	CATAGORY	= "Catagory";
	public static final String	NUM_OF_PICS	= "NumOfPics";

	// | -----------------------------------------------------------------------
	// | Public Class Operations
	// | -----------------------------------------------------------------------
	/**
	 * @param mCatagory
	 *            the mCatagory to set
	 */
	public void setCatagory(String mCatagory)
	{
		this.mCatagory = mCatagory;
	}

	/**
	 * @return the mCatagory
	 */
	public String getCatagory()
	{
		return mCatagory;
	}

	/**
	 * @param mNumOfPics
	 *            the mNumOfPics to set
	 */
	public void setNumOfPics(int mNumOfPics)
	{
		this.mNumOfPics = mNumOfPics;
	}

	/**
	 * @return the mNumOfPics
	 */
	public int getNumOfPics()
	{
		return mNumOfPics;
	}

	/**
	 * @param mName
	 *            the mName to set
	 */
	public void setName(String mName)
	{
		this.mName = mName;
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
        return "mName = " + this.mName
                  + "\n mCatagory = " + this.mCatagory
                  + "\n mNumOfPics = " + this.mNumOfPics
                  ;
   }

	// | -----------------------------------------------------------------------
	// | Private Class Attributes
	// | -----------------------------------------------------------------------
	private String	mName		= CommonConstants.EMPTY_STRING;
	private String	mCatagory	= CommonConstants.EMPTY_STRING;
	private int		mNumOfPics	= CommonConstants.DEFAULT_INT;
}
