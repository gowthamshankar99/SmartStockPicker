package com.smartpickers.companies;

import java.io.File;
import java.io.IOException;

import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public class FinanceCompany extends AbstractCompany implements IStockIndex {
	
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks - this has been changed to reading the tickers from a file
	
//	 static final String BOFA = "BAC";
//	 static final String WELLSFARGO = "WFC";
//	 static final String JPM = "JPM";
//	 static final String CITIGROUP = "C";
	 
	

//	// Creating ArrayList to hold Stock Tickers
//	List<String> financeCompanies;
	
	private GetAPI getApi;
	
	public FinanceCompany(GetAPI getApi) {
		this.getApi = getApi;
		
		
//		// Initialize the arraylist
//		financeCompanies = new ArrayList<String>();
//		financeCompanies.add(BOFA);
//		financeCompanies.add(WELLSFARGO);
//		financeCompanies.add(JPM);
//		financeCompanies.add(CITIGROUP);
	}
	
	public double getLoanOutstanding()
	{
		return 0;
	}

	/*
	 * Intent : Get stock data from the Alpha Advantage API
	 */
	
	@Override
	public String getstockDetails(String tickerName) throws IOException, InvalidTickerException {
		/*
		 *  PRE_CONDITION: tickerName should not be null, tickerName should be one of the 
		 *  values from the Finance for the quote to work 
		 * 
		 */
		
		/*
		 *  POST_CONDITION : returns the stock quote, high value, low value from the API 
		 */
		System.out.println("Getting stock data for Finance Stocks!");
		File financeFile = new File("Finance.txt");
		// If the file doesnt contain the ticker - fail the process
		if(!readFile(this, financeFile, tickerName))
			throw new InvalidTickerException("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
			//System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Finance Company!");
		else
		{
			String getData = getApi.getApiData(tickerName);
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
