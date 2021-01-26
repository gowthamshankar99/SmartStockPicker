package com.smartpickers.stockdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAPI {

	
	public String getApiData(String tickerName) throws IOException
	{
		System.out.println("Getting stock price for the ticker " + tickerName);
		URL urlForGetRequest = new URL("stockapicomeshere");
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
