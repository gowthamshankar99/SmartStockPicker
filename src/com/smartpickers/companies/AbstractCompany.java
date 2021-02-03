package com.smartpickers.companies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class AbstractCompany {
	
	
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
	public abstract String getstockDetails(String tickerName) throws IOException;
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regadless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	public double getPERatio() {
		//  yet to be implemented
		return 0;
	}
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	
	public double getEPS() {
		return 0;
	}
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	public double getDEquityRatio() {
		return 0;
	}
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	public double getLongTermAssets() {
		return 0;
	}
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	public double getShortTermAssets() {
		return 0;
	}
	
	
	// Read the respective industry File to see if the Ticker Exist before doing API call
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
	
	

}
