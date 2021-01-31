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
	
	public Boolean readFile(AbstractCompany abstractCompany, File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		
		while(scanner.next() != null)
		{
			System.out.println(scanner.next());
		}
		
		scanner.close();
		return false;
	}
	
	

}
