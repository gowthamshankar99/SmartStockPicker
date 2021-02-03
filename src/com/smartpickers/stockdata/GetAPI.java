package com.smartpickers.stockdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAPI {

	/*
	 * Intent: Get the Stock Details from the alphaadvantage API and return the data
	 */
	public String getApiData(String tickerName) throws IOException
	{
		/*
		 * PRE_CONDITION : tickerName should be a valid Ticker Name, tickerName should not be a null value
		 */
		/*
		 * POST_CONDITION : return the JSON dump
		 */
		System.out.println("Getting stock price for the ticker " + tickerName + "\n");
		URL urlForGetRequest = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+tickerName + "&apikey=" + "PTQSQULZN28J4N6E");
	    String readLine = null;
	    HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
	    connection.setRequestMethod("GET");
	    int responseCode = connection.getResponseCode();
	    
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(connection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        return response.toString();
	    } else {
	        
	        return "issue with the API call";
	    }
	}
}
