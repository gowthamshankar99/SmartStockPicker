package com.smartpickers.companies;

public class StockDetailExtractor<T extends AbstractCompany> {
	
    private T industryObject; 
    
    StockDetailExtractor(T industryObject) {  
    	this.industryObject = industryObject;  
    	}
    
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
