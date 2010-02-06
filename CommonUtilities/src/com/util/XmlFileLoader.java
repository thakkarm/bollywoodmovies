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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlFileLoader
{

	public void loadXMLFromURL(String urlString, DefaultHandler xmlHandler)
			throws XMLFileLoaderException
	{
		try
		{
			URL url = new URL(urlString);

			// Get a SAXParser from the SAXPArserFactory.
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			// Get the XMLReader of the SAXParser we created.
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(xmlHandler);

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

			// | Load the online configuration
			InputSource inputSource = new InputSource(url.openStream());

			// Parse the xml-data from our URL
			xr.parse(inputSource);

		} catch (MalformedURLException e)
		{
			String message = "Error Loading : [" + urlString + "]";
			throw new XMLFileLoaderException(message, e);
		} catch (ParserConfigurationException e)
		{
			String message = "Error Loading : [" + urlString + "]";
			throw new XMLFileLoaderException(message, e);
		} catch (SAXException e)
		{
			String message = "Error Loading : [" + urlString + "]";
			throw new XMLFileLoaderException(message, e);
		} catch (IOException e)
		{
			String message = "Error Loading : [" + urlString + "]";
			throw new XMLFileLoaderException(message, e);
		} catch (Exception e)
		{
			String message = "Error Loading : [" + urlString + "]";
			throw new XMLFileLoaderException(message, e);
		}

	}

}
