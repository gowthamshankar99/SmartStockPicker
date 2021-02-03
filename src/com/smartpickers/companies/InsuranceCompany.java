package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;

import com.smartpickers.stockdata.GetAPI;

public class InsuranceCompany extends AbstractCompany implements IStockIndex {
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks - this has been commented out for now - this is being 
	// read from a file for now
	
//	 static final String THEHARTFORD = "HIG";
//	 static final String TRAVELLERS = "TRV";
//	 static final String MASSMUTUAL = "MCI";
//	 static final String ALLSTATE = "ALL";
	

//	// Creating ArrayList to hold Stock Tickers
//	List<String> insuranceCompanies;
	
	private GetAPI getApi;
	
	public InsuranceCompany(GetAPI getApi) {
		this.getApi = getApi;
		
		// Initialize the list
//		insuranceCompanies = new ArrayList<String>();
//		insuranceCompanies.add(THEHARTFORD);
//		insuranceCompanies.add(TRAVELLERS);
//		insuranceCompanies.add(MASSMUTUAL);
//		insuranceCompanies.add(ALLSTATE);
	}
	
	public double getCurrentStandardPremiumAmount() {
		return 0;
	}

	/*
	 * Intent : Get stock data from the Alpha Advantage API and print the returned data to the console
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException {
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
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
		else
		{
			String getData = getApi.getApiData(tickerName);
			return getData;
		}
		return null;
	}

	@Override
	public String indexName() {
		return null;
	}

	@Override
	public String indexDetails() {
		return null;
	}

	@Override
	public String indexType() {
		return null;
	}
	

}
