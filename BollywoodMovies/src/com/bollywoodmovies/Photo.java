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
 * $Revision:$
 * $LastChangedBy:$
 * $Date:$
 * $Id:$
 * 
 ******************************************************************************/
package com.bollywoodmovies;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.bollywoodmovies.config.CelebrityData;
import com.bollywoodmovies.config.Configuration;
import com.util.CommonConstants;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Photo extends BaseApplicationActivity {

	public void onCreate(Bundle icicle) {
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + Photo.class
				+ "::onCreateOptionsMenu()");
		super.onCreate(icicle);
		setContentView(R.layout.photo);

		// | Create and initialize the footer buttons
		createFooterNavigationButton();

		// | Get button from layout
		Button prevButton = (Button) findViewById(R.id.Prev);
		prevButton.setText("Prev");

		// | Register the onClick listener with the implementation above
		prevButton.setOnClickListener(prevOnClickListner);

		// | Get button from layout
		Button nextButton = (Button) findViewById(R.id.Next);
		nextButton.setText("Next");

		nextButton.setOnClickListener(nextOnClickListner);

		mGestureDetector = new GestureDetector(this, new GestureListener());

		String url = MainApp.getInstance().getURLBollywoodActress(
				MainApp.getInstance().getCurrentPersonName());

		ImageView imgView = (ImageView) findViewById(R.id.PhotoImageView);
		this.showImage(url, imgView);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + Photo.class
				+ "::onCreateOptionsMenu()");
	}

	public void showPreviousImage() {
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + Photo.class
				+ "::showPreviousImage()");

		String url = MainApp.getInstance().getURLBollywoodActress(
				MainApp.getInstance().getCurrentPersonName());

		ImageView imgView = (ImageView) findViewById(R.id.PhotoImageView);
		this.showImage(url, imgView);

		// Context context = view.getContext();
		// Context context = getApplicationContext();
		// Drawable image = ImageOperations(context, url);
		// ImageView imgView = new ImageView(context);
		// imgView = (ImageView) findViewById(R.id.PhotoImageView);
		// imgView.setImageDrawable(image);

		MainApp mainApp = MainApp.getInstance();
		long currentPersonIndex = mainApp.getCurrentPersonIndex();
		currentPersonIndex--;
		if (currentPersonIndex < 1) {
			currentPersonIndex = 1;
		}

		mainApp.setCurrentPersonIndex(currentPersonIndex);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + Photo.class
				+ "::showPreviousImage()");
	}

	public void showNextImage() {
		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + Photo.class
				+ "::showNextImage()");

		MainApp mainApp = MainApp.getInstance();
		long currentPersonIndex = mainApp.getCurrentPersonIndex();

		currentPersonIndex++;
		// Get the count from the configuration and compare against that
		Configuration config = Configuration.getInstance();
		CelebrityData celebrity = config.getCelebrityData(mainApp
				.getCurrentPersonName());
		int celebrityMaxPics = CommonConstants.MAX_PICS_PER_CELEBRITY;
		if (celebrity != null) {
			celebrityMaxPics = celebrity.getNumOfPics();
		}
		if (currentPersonIndex >= celebrityMaxPics) {
			currentPersonIndex = 1;
		}
		mainApp.setCurrentPersonIndex(currentPersonIndex);

		String url = MainApp.getInstance().getURLBollywoodActress(
				MainApp.getInstance().getCurrentPersonName());

		ImageView imgView = (ImageView) findViewById(R.id.PhotoImageView);
		this.showImage(url, imgView);

		// Context context = view.getContext();
		// Context context = getApplicationContext();
		// Drawable image = ImageOperations(context, url);
		// ImageView imgView = new ImageView(context);
		// imgView = (ImageView) findViewById(R.id.PhotoImageView);
		// imgView.setImageDrawable(image);

		Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT + Photo.class
				+ "::showNextImage()");
	}

	private Drawable ImageOperations(Context ctx, String url) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable drawable = Drawable.createFromStream(is, "src");
			return drawable;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,
			IOException {
		Log.d(CommonConstants.LOG_TAG, "Fetching [" + address + " ]");

		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	private GestureDetector mGestureDetector = null;

	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			boolean imageMoved = false;
			if (((e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE)
					&& (Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)) {
				showNextImage();
				imageMoved = true;
			} else if ((e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE)
					&& (Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)) {
				showPreviousImage();
				imageMoved = true;
			}
			return imageMoved;
		}
	}

	private void showImage(String url, ImageView imageView) {
		Log.d(CommonConstants.LOG_TAG, "Show image at URL : [" + url + "]");

		// Context context = view.getContext();
		Context context = getApplicationContext();
		Drawable image = ImageOperations(context, url);
		imageView = (ImageView) findViewById(R.id.PhotoImageView);

		// int height = image.getIntrinsicHeight();
		// int width = image.getIntrinsicWidth();
		// Log.d(CommonConstants.LOG_TAG, "Height " + height + "   width " +
		// width);

		imageView.setMaxHeight(300);
		imageView.setMaxWidth(300);
		imageView.setMinimumHeight(300);
		imageView.setMinimumWidth(300);
		imageView.setImageDrawable(image);

	}

	OnClickListener prevOnClickListner = new OnClickListener() {
		public void onClick(View view) {
			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + Photo.class
					+ "::prevOnClickListner()");

			showPreviousImage();
			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT
					+ Photo.class + "::prevOnClickListner()");
		}
	};

	OnClickListener nextOnClickListner = new OnClickListener() {
		public void onClick(View view) {
			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_IN + Photo.class
					+ "::nextOnClickListner()");

			showNextImage();

			Log.i(CommonConstants.LOG_TAG, CommonConstants.LOG_OUT
					+ Photo.class + "::nextOnClickListner()");
		}
	};
}