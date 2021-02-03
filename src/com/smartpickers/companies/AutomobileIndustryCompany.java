package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;

import com.smartpickers.stockdata.GetAPI;

public class AutomobileIndustryCompany extends AbstractCompany implements IStockIndex  {
	
	
//	static final String FORD = "F";
//	static final String TESLA = "TSLA";
	 
	private GetAPI getApi;
	
//	private List<String> automobileCompanyList;
	
	public AutomobileIndustryCompany(GetAPI getApi) {
		
		// Create Static ticker names for now - this will eventually get loaded from a
		// Databases in the coming weeks - this has been changed to reading the tickers from a file
		this.getApi = getApi;
		
//		//initialize the list to add the companies that can be queried
//		automobileCompanyList = new ArrayList<String>();
//		automobileCompanyList.add(FORD);
//		automobileCompanyList.add(TESLA);
	}
	
	public double getSalesoutput() {
		return 0;
	}

	/*
	 * Intent : Get stock data from the Alpha Advantage API
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException {
		/*
		 *  PRE_CONDITION: tickerName should not be null, tickerName should be one of the 
		 *  values from the AutomobileFile for the quote to work 
		 * 
		 */
		
		/*
		 *  POST_CONDITION : returns the stock quote, high value, low value from the API 
		 */
		// get stock data from the API
		System.out.println("Getting stock data for Automobile Stocks!");
		File automobileFile = new File("Automobile.txt");
		// If the file doesnt contain the ticker - fail the process
		if(!readFile(this, automobileFile, tickerName))
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Automobile Company!");
		else
		{
			String getData = getApi.getApiData(tickerName);
			return getData;
		}
		return null;
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
