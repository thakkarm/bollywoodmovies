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
 * $Id: AboutUsActivity.java 20 2010-02-06 04:44:14Z thakkm $
 * 
 ******************************************************************************/
package com.util;

public class BaseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555930756749261870L;

	BaseException()
	{
		super();
	}

	BaseException(String message)
	{
		super(message);
	}

	BaseException(String message, Throwable exception)
	{
		super(message);
		mNestedException = exception;
		exception.printStackTrace();
	}
	
	private Throwable mNestedException = null;
}

