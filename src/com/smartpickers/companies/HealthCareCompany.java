package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;

import com.smartpickers.stockdata.GetAPI;

public class HealthCareCompany extends AbstractCompany implements IStockIndex  {
	
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks
	
	 static final String ANTHEM = "ANTM";
	 static final String CIGNA = " CI";
	 static final String CVS = "CVS";
	 static final String HUMANA = "HUM";
	 
	

//	// Creating ArrayList to hold Stock Tickers
//	List<String> healthcareCompanies;
	
	private GetAPI getAPI;
	
	 public HealthCareCompany(GetAPI getAPI) {
		 this.getAPI = getAPI;
		 
//		 healthcareCompanies = new ArrayList<String>();
//		 healthcareCompanies.add(ANTHEM);
//		 healthcareCompanies.add(CIGNA);
//		 healthcareCompanies.add(CVS);
//		 healthcareCompanies.add(HUMANA);
		 
	 }

	 
	/*
	 *  Intent: Get Stock Details from the API and send the data to Console
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException {
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
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Healthcare Company!");
		else
		{
			String getData = getAPI.getApiData(tickerName);
			System.out.println(getData);
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
