package com.smartpickers.companies;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.smartpickers.stockdata.GetAPI;

public class StockDetailExtractor<T extends AbstractCompany> {
	
    private T industryObject;
    private GetAPI getAPI;
    
    public StockDetailExtractor(T industryObject, GetAPI getAPI) {  
    	/*
    	 * Constructor setting the incoming IndustryObject
    	 * 
    	 * The Industry Object can be one of the Technology, Insurance, Automobile, Healthcare classes
    	 */
    	this.industryObject = industryObject;  
    	this.getAPI = getAPI;
    	}
    
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
    /*
	 * POST_CONDITION - return the PERatio
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String getPERatio(String tickerArray) throws IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "OVERVIEW"));
		String peRatio = (String) obj.get("PERatio");

		return peRatio;
	}
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @throws IOException 
	 * @throws ParseException 
	 * @returns a  double value
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
	/*
	 * POST_CONDITION - return the Earnings Per Share
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	
	public String getEPS(String tickerArray) throws ParseException, IOException {
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "OVERVIEW"));
		String epsPrice = (String) obj.get("EPS");

		return epsPrice;

	}
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @throws IOException 
	 * @throws ParseException 
	 * @returns a  double value
	 */
	
	/*
	 * Intent - Gets the price to Book ratio by communicating with the API
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
	/*
	 * POST_CONDITION - return the Price to Equity Ratio
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String getDEquityRatio(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "OVERVIEW"));
		String PriceToBookRatio = (String) obj.get("PriceToBookRatio");

		return PriceToBookRatio;
	}
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
	 */
	/*
	 * Intent - Gets the long term assets of a Company by communicating with the API
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
	/*
	 * POST_CONDITION - return the Long Term Assets
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String getLongTermAssets(String tickerArray) throws ParseException, IOException {
		// totalNonCurrentAssets
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String totalNonCurrentAssets = (String) obj.get("totalNonCurrentAssets");

		return totalNonCurrentAssets;

	}
	
	/**
	 * This Method doesnt need to be a abstract method - all stocks regardless of what industry they belong to - have the same P/E ratio calculation
	 * @throws IOException 
	 * @throws ParseException 
	 * @returns a  double value
	 */
	/*
	 * Intent - Gets the short term assets of a Company by communicating with the API
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
	/*
	 * POST_CONDITION - return the ShortTermDebt
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String getShortTermAssets(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String shortTermAssets = (String) obj.get("totalCurrentAssets");

		return shortTermAssets;
	}
	
	/*
	 * Intent - Gets the short term Debt of a Company by communicating with the API
	 */
    /*
     * PRE_CONDITION - tickerName should be a valid ticker for API to respond valid data
     */
	/*
	 * POST_CONDITION - return the ShortTermDebt
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String shortTermDebt(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String shortTermDebt = (String) obj.get("PriceToBookRatio");

		return shortTermDebt;
	}
	
	/*
	 * Intent - Gets the long term Debt of a Company by communicating with the API
	 */
	
	/*
	 * PRE_CONDITION - tickerName should be a valid ticker
	 */
	
	/*
	 * POST_CONDITION - return the longTermDebt
	 * GETAPI should be reachable and shouldnt be a null value
	 */
	public String longTermDebt(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String longTermDebt = (String) obj.get("currentLongTermDebt");

		return longTermDebt;
	}
}
