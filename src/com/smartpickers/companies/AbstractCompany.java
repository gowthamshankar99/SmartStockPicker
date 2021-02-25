package com.smartpickers.companies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public abstract class AbstractCompany implements Serializable {
	
	private GetAPI getApi;
	
	List<String> tickerList = new ArrayList<String>();
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

			// Push data to Array List
			tickerList.add(scanner.next());
			
			// Use Lambda expression's for each to loop through the list and print the tickers
			tickerList.forEach((n) -> System.out.println(n));
			
		}
	}
	

	public synchronized void writeDailyLogs(String file, String data) throws IOException {
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
//		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//		
//		// add a new line to the input string 
//		data = data+"\n\n";
//		// Convert Data string to byte array 
//		byte b[] = data.getBytes();
//		out.write(b);
//		out.close();
		
	     
	    FileWriter fileWriter = new FileWriter(file, true); //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(data);  //New line
	    printWriter.close();
		
		
	}

}
