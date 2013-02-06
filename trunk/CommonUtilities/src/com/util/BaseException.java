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

import android.util.Log;

public class BaseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555930756749261870L;


   /**
     * Holds the nested exception.
     */
    private Throwable mNestedException = null;

    // Constructors

    /**
     * Class constructor.
     */

    public BaseException() {
        super();
        log();
    }

    /**
     * Class constructor.
     *
     * @param message The message held within the exception.
     */

    public BaseException(final String message) {

        super(message);
        log();
    }

    /**
     * Class constructor.
     *
     * @param nestedException The exception that should be put inside this
     *                          exception.
     */

    public BaseException(Throwable nestedException) {

        super();
        this.mNestedException = nestedException;
        log();
    }

    /**
     * Class constructor.
     *
     * @param message The message that this exception should hold.
     * @param nestedException The exception that should be inside the
     *                           exception.
     */

    public BaseException(String message,
            Throwable nestedException) {

        super(message);
        this.mNestedException = nestedException;
        log();
    }

    /**
     * Return the nested exception of this object.
     * @return The exception nested in this exception.
     */

    public final Throwable getNestedException() {

        return mNestedException;
    }



    /**
     * Logs this exception to system logger.
     */

    public void log() {
        Log.e(CommonConstants.LOG_TAG, "", this);
    }

    /**
     * Converts this exception to a string.
     * @return The string representation of this exception.
     */
    public final String toString() {
        String message = super.toString();
        if (null != mNestedException) {
            message = mNestedException + CommonConstants.EXCEPTION_SEPARATOR
            + message;
        }
        return message;
    }

    /**
     * Mutator for mNestedException.
     * @param nestedException the exception nested within this exception.
     */
    public final void setNestedException(final Throwable nestedException) {
        mNestedException = nestedException;
    }
	
}

