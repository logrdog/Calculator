/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geonames;


import java.net.*;
import java.io.*;
//		json_simple-1.1.jar
//
// In eclipse or netBeans, you can add the jar files to the build path
// From the UNIX command line, put the jar files in the same directory as your source, make that your current   
// working directory, and then add them to the CLASSPATH as follows:
//
//		export CLASSPATH=".:./*"
//
// The export statement sets the CLASSPATH to the current working directory and any jars or directories in that directory
//import org.json.simple.*;  
// Use the following imports if you are using the full JSON libarary.  You will need to include 

import java.net.URL;
import java.util.Iterator;
import net.sf.json.*;
import org.apache.commons.lang.exception.*;
import org.apache.commons.io.IOUtils;
// The download for the JSON-lib as well as the other dependencies can be found at http://json-lib.sourceforge.net/
// With this version, you will need to include the following jar files in the CLASSPATH
//		commons-beanutils-1.8.3.jar
//		commons-collections-3.2.1.jar
//		commons-lang-2.6.jar
//		commons-logging-1.1.1.jar
//		ezmorph-1.0.6.jar
//		json-lib-2.4-jdk15.jar
//
// other dependencies may exist if you are using json lib with XML or Groovy
// Use this import regardless of with version of JSON you are using
// import to allow the use of IOUtils


/**
 *
 * liveDataSample.java is a sample program demonstrating how to access live
 * weather data from geonames.org using simple JSON and IOUtils. In the process,
 * it also demonstrates how to read a URL as a String.
 *
 * This version improves upon version 1 through the use of IOUtils
 *
 * @author Chase
 * @version 2.0
 *
 *
 */
public class geonames {

    public static void main(String[] args) throws Exception {

// Call to readURL method to read the given URL as a String.  Change the last portion of the URL 
// to your username after you have created an account and enabled web services (e.g. &username=jDoe)		
        String JSonString = readURL("http://api.geonames.org/weatherIcaoJSON?ICAO=KPHL&formatted=true&username=glrichards12");
        
         String JSonString1 = readURL("http://api.geonames.org/weatherJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=glrichards12");
         
        /* The String returned should look something like this:
 
		{"weatherObservation": {
			"elevation": 18,
			"lng": -75.23333333333333,
			"observation": "KPHL 241854Z 20007KT 10SM SCT280 M06/M20 A3016 RMK AO2 SLP211 T10561200",
			"ICAO": "KPHL",
			"clouds": "scattered clouds",
			"dewPoint": "-20",
			"cloudsCode": "SCT",
			"datetime": "2015-02-24 18:54:00",
			"seaLevelPressure": 1021.1,
			"countryCode": "US",
			"temperature": "-5.6",
			"humidity": 31,
			"stationName": "Philadelphia, Philadelphia International Airport",
			"weatherCondition": "n/a",
			"windDirection": 200,
			"windSpeed": "07",
			"lat": 39.86666666666667
		}}
 
    Notice that this JSON object contains only one field – "weather observation".  That field contains another
    nested JSON object with several fields.
 
    If you do not think you are getting the correct String, copy the URL into a web browser to see what you get back.
    It may mean that you have not yet enabled web services or that the weather station you are attempting to access
    is off line.
         */
// Use this block of code to generate the 1st level JSon Object if you are using simple JSON		
//		Object n  = JSONValue.parse(JSonString);
//		JSONObject x = (JSONObject) n ;
// Use the following line of code to generate the 1st level JSon Object if you are using the full JSon library
        JSONObject x = JSONObject.fromObject(JSonString);
        
        JSONObject y = JSONObject.fromObject(JSonString1);
// The following line of code extracts the field "weatherObervation" as another JSONObject.		
        JSONObject weatherData = (JSONObject) (x.get("weatherObservation"));
        
        //JSONObject weatherData2 = (JSONObject) (y.get("weatherObservations"));
        
// Finally, we are able to extract the fields for "stationName", "temperature", and "clouds" 		
        System.out.println("elevation is " + weatherData.get("elevation"));
        System.out.println("lng: " + weatherData.get("lng"));
        System.out.println("observation: " + weatherData.get("observation"));
        System.out.println("ICAO: " + weatherData.get("ICAO"));
        System.out.println("Cloud coverage: " + weatherData.get("clouds"));
        System.out.println("dewPoint: " + weatherData.get("dewPoint"));
        System.out.println("cloudsCode: " + weatherData.get("cloudsCode"));
        System.out.println("datetime: " + weatherData.get("datetime"));
        System.out.println("seaLevelPressure: " + weatherData.get("seaLevelPressure"));
        System.out.println("countryCode: " + weatherData.get("countryCode"));
        System.out.println("Temperature is: " + weatherData.get("temperature"));
        System.out.println("humidity: " + weatherData.get("humidity"));
        System.out.println("Weather data for: " + weatherData.get("stationName"));
        System.out.println("weatherCondition: " + weatherData.get("weatherCondition"));
        System.out.println("windDirection: " + weatherData.get("windDirection"));
        System.out.println("windSpeed: " + weatherData.get("windSpeed"));
        System.out.println("lat: " + weatherData.get("lat"));
        System.out.println("/***************************************/");
        
        int index = 0;
        
        double result = 0;
        String str = "";
        for(Object obj:y.getJSONArray("weatherObservations")){
            
           JSONObject weatherData2 = (JSONObject) (obj);
           result+=Double.parseDouble(weatherData2.get("temperature").toString());
           str += weatherData2.get("stationName") + ", Temperature: " + weatherData2.get("temperature") + "\t" + weatherData2.get("datetime") + "\n";
           index++;
        }
        result=result/index;
        System.out.println(result);
        System.out.println(str);
    }

    /**
     *
     * private helper method demonstrates how to read the contents of a URL as a
     * String.
     *
     * This version improves upon version 1 through the use of IOUtils
     *
     * @param webservice provides the URL address of the web service to be
     * accessed
     * @return String representation of the given web page or service
     * @throws java.net.MalformedURLException if the given url is poorly formed
     * @throws java.io.IOException if IOUtils encounters an IOException
     *
     *
     */
    private static String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException {
// create a URL object from the given address		
        URL service = new URL(webservice);

// use IOUtils to access the URL and return a string		
        String result = IOUtils.toString(service, "UTF-8");
        return result;
    }
}
