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
    	this.industryObject = industryObject;  
    	this.getAPI = getAPI;
    	}
    
	/**
	 * This Method doesnt need to be a abstract method - all stocks regadless of what industry they belong to - have the same P/E ratio calculation
	 * @returns a  double value
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
	public String getShortTermAssets(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String shortTermAssets = (String) obj.get("totalCurrentAssets");

		return shortTermAssets;
	}
	
	
	public String shortTermDebt(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String shortTermDebt = (String) obj.get("PriceToBookRatio");

		return shortTermDebt;
	}
	
	public String longTermDebt(String tickerArray) throws ParseException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(getAPI.getApiData(tickerArray, "BALANCE_SHEET"));
		String longTermDebt = (String) obj.get("currentLongTermDebt");

		return longTermDebt;
	}
}
