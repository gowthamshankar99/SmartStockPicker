package com.smartpickers.companies;

import java.io.IOException;

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
	
	// Abstract method - implementation in the subclasses
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
	
	

}
