package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public class TechnologyCompany extends AbstractCompany implements IStockIndex {

	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks - the below has been commented for now as the ticker has been loaded 
	// from the File for now
	
//	 static final String APPLE = "AAPL";
//	 static final String GOOGLE = "GOOG";
//	 static final String IBM = "IBM";
//	 static final String PELATON = "PTON";
//	 static final String CISCO = "CSCO";
	

	// Creating ArrayList to hold Stock Tickers
	List<String> technologyCompanies;

	private GetAPI getApi;

	public TechnologyCompany(GetAPI getApi) {
		this.getApi = getApi;

//		// initialize the arrayList
//		technologyCompanies = new ArrayList<String>();
//		// load the companies into the list
//		technologyCompanies.add(APPLE);
//		technologyCompanies.add(GOOGLE);
//		technologyCompanies.add(IBM);
//		technologyCompanies.add(PELATON);
		
	}
	
	
	/*
	 * Intent : Get stock data from the Alpha Advantage API and print the returned data to the console
	 */
	@Override
	public String getstockDetails(String tickerName) throws IOException, InvalidTickerException {
		/*
		 *  PRE_CONDITION: tickerName should not be null, tickerName should be one of the 
		 *  values from the TechnologyFile for the quote to work 
		 * 
		 */
		
		/*
		 *  POST_CONDITION : returns the stock quote, high value, low value from the API 
		 */
		System.out.println("Getting stock data for Tech Stocks!");
		//if(!this.technologyCompanies.contains(tickerName))
		File technologyFile = new File("technology.txt");
		
		// If the file doesnt contain the ticker - fail the process
		if(!readFile(this, technologyFile, tickerName))
			//System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Technology Company!");
			throw new InvalidTickerException("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
		else
		{
			String getData = getApi.getApiData(tickerName, "GLOBAL_QUOTE");			
			return getData;
		}
		
		//return "";
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

	/*
	 * Intent : Get the active Subscriber count
	 */
	public int getActiveSubscribersCount() {
		// api call is yet to be implemented
		return 0;
	}

}
