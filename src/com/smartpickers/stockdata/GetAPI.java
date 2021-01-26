package com.smartpickers.stockdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAPI {

	
	public String getApiData(String amountofQuestions, String catagory, String difficulty, String type) throws IOException
	{
		URL urlForGetRequest = new URL("https://opentdb.com/api.php?amount=" + amountofQuestions + "&category=" + catagory + "&difficulty=" + difficulty + "&type=" + type);
		System.out.println("https://opentdb.com/api.php?amount=" + amountofQuestions + "&category=" + catagory + "&difficulty=" + difficulty + "&type=" + type);
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
