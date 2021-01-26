package com.smartpickers.companies;

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
	
	public abstract void getstockDetails();
	
	public abstract double getPERatio();
	
	public abstract double getEPS();
	
	public abstract double getDEquityRatio();
	
	public abstract double getLongTermAssets();
	
	public abstract double getShortTermAssets();
	
	

}
