package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public class InsuranceCompany extends AbstractCompany implements IStockIndex, Serializable {
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks - this has been commented out for now - this is being 
	// read from a file for now

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetAPI getApi;
	
	public InsuranceCompany(GetAPI getApi) {
		this.getApi = getApi;
	}
	
	public double getCurrentStandardPremiumAmount() {
		return 0;
	}

	/*
	 * Intent : Get stock data from the Alpha Advantage API and print the returned data to the console
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException, InvalidTickerException {
		/*
		 *  PRE_CONDITION: tickerName should not be null, tickerName should be one of the 
		 *  values from the InsuranceFile for the quote to work 
		 * 
		 */
		
		/*
		 *  POST_CONDITION : returns the stock quote, high value, low value from the API 
		 */
		// get stock data from the API
		System.out.println("Getting stock data for Insurance Stocks!");
		File insuranceFile = new File("Insurance.txt");
		if(!readFile(this, insuranceFile, tickerName))
			// call the User Defined Exception - InvalidTickerException
			throw new InvalidTickerException("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
			//System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
		else
		{
			String getData = getApi.getApiData(tickerName, "GLOBAL_QUOTE");
			return getData;
		}
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
