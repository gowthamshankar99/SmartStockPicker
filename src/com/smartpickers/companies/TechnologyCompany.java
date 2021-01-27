package com.smartpickers.companies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smartpickers.stockdata.GetAPI;

public class TechnologyCompany extends AbstractCompany implements IStockIndex {

	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks
	
	 static final String APPLE = "AAPL";
	 static final String GOOGLE = "GOOG";
	 static final String IBM = "IBM";
	 static final String PELATON = "PTON";
	 static final String CISCO = "CSCO";
	

	// Creating ArrayList to hold Stock Tickers
	List<String> technologyCompanies;

	private GetAPI getApi;

	public TechnologyCompany(GetAPI getApi) {
		this.getApi = getApi;

		// initialize the arrayList
		technologyCompanies = new ArrayList<String>();
		// load the companies into the list
		technologyCompanies.add(APPLE);
		technologyCompanies.add(GOOGLE);
		technologyCompanies.add(IBM);
		technologyCompanies.add(PELATON);
		
	}


	@Override
	public String getstockDetails(String tickerName) throws IOException {
		// get stock data from the API
		System.out.println("Getting stock data for Tech Stocks!");
		if(!this.technologyCompanies.contains(tickerName))
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Technology Company!");
		else
		{
			String getData = getApi.getApiData(tickerName);			
			return getData;
		}
		
		return "";
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
