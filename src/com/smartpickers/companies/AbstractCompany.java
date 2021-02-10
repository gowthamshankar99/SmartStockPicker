package com.smartpickers.companies;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public abstract class AbstractCompany {
	
	private GetAPI getApi;
	
	
	String tickerName;
	String headQuartersLocation;
	
	// getter Method for Ticker Name
	public String getTickerName() {
		return tickerName;
	}
	// Setter Method for Ticker Name
	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}
	// getter Method for HeadQuarters
	public String getHeadQuartersLocation() {
		return headQuartersLocation;
	}
	// Getter Method for HeadQuarters
	public void setHeadQuartersLocation(String headQuartersLocation) {
		this.headQuartersLocation = headQuartersLocation;
	}
	
	public String getPERatio(String tickerArray) throws IOException, ParseException {
		
		getApi = new GetAPI();

		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getApi.getApiData(tickerArray, "OVERVIEW"));
		String peRatio = (String) obj.get("PERatio");

		return peRatio;
	}
	
	// Abstract method - implementation in the subclass
	public abstract String getstockDetails(String tickerName) throws IOException, InvalidTickerException;
	/*
	 * Intent:  Read the respective industry File to see if the Ticker Exist before doing API call
	 */
	
	/*
	 * PRE_CONDITION: The Industry file should be present and the application assumes 
	 * that the file already exists with values in it.
	 */
	
	/*
	 * POST_CONDITION: Returns true if the Ticker is present in the industry File
	 */
	public Boolean readFile(AbstractCompany abstractCompany, File file, String tickerName) throws FileNotFoundException {
		// Using Scanner to read the file - as the file type is always a text file
		Scanner scanner = new Scanner(file);
		// Check if there is any more lines in the file to read
		while(scanner.hasNext())
		{
			//System.out.println(scanner.next().toLowerCase().trim() + "=" + tickerName.toLowerCase().trim());
			// Print the Line from the file
			if(scanner.next().toLowerCase().trim().contentEquals(tickerName.trim().toLowerCase()))
				// returns true if the Ticker exist in the file
				return true;		
		}
		// Close the Scanner Object
		scanner.close();
		// returns true if the Ticker exist in the file (or) Returns false if the ticker doesnt exist in the file
		return false;
	}
	
	public void printTickerFile(File file) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			System.out.println(scanner.next());
		}
	}
	
	/*
	 * Intent:  Write Logs to the Log file if the call is being made for the first time
	 */
	
	/*
	 * PRE_CONDITION: The Log file should be present and the application assumes 
	 * that the file already exists
	 */
	
	/*
	 * POST_CONDITION: Writes the Quote values to the log file using BufferedWriter
	 */
	public void writeDailyLogs(File file, String data) throws IOException {

		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
		out.write(data+"\n");
		out.newLine();
		
		out.close();
		
		
	}
	

	
	

}
