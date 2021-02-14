package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public class HealthCareCompany extends AbstractCompany implements IStockIndex, Serializable  {
	
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetAPI getAPI;
	
	 public HealthCareCompany(GetAPI getAPI) {
		 this.getAPI = getAPI;
	 }

	 
	/*
	 *  Intent: Get Stock Details from the API and send the data to Console
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException, InvalidTickerException {
		/*
		 *  PRE_CONDITION: tickerName should not be null, tickerName should be one of the 
		 *  values from the HealthCareFile for the quote to work 
		 * 
		 */	
		
		/*
		 *  POST_CONDITION : returns the stock quote, high value, low value from the API 
		 */
		System.out.println("Getting stock data for HealthCare Stocks!");
		File HealthcareFile = new File("Healthcare.txt");
		// If the file doesnt contain the ticker - fail the process
		if(!readFile(this, HealthcareFile, tickerName))
			throw new InvalidTickerException("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
			//System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Healthcare Company!");
		else
		{
			String getData = getAPI.getApiData(tickerName, "GLOBAL_QUOTE");
			System.out.println(getData);
			return getData;
		}
		//return null;
	}


	/*
	 * Intent : Get the Intent Name 
	 */
	@Override
	public String indexName() {
		return null;
	}


	/*
	 * Intent : Get Index Details
	 */
	@Override
	public String indexDetails() {
		return null;
	}

	/*
	 * Intent: Get the IndexType
	 */
	@Override
	public String indexType() {
		
		return null;
	}


}
