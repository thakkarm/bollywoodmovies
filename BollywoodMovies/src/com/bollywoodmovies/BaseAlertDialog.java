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
 * $Id: ActressListActivity.java 21 2010-02-06 04:45:03Z thakkm $
 * 
 ******************************************************************************/
package com.bollywoodmovies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class BaseAlertDialog extends AlertDialog
{

    /**
     * @param context
     */
    protected BaseAlertDialog(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub

        setTitle("Alert 1");
        setMessage("This is an alert");
        setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                return;
            }
        });
    }

}
