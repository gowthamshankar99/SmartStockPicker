package com.smartpickers.companies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smartpickers.stockdata.GetAPI;

public class FinanceCompany extends AbstractCompany {
	
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks
	
	 static final String BOFA = "BAC";
	 static final String WELLSFARGO = "WFC";
	 static final String JPM = "JPM";
	 static final String CITIGROUP = "C";
	 
	

	// Creating ArrayList to hold Stock Tickers
	List<String> financeCompanies;
	
	private GetAPI getApi;
	
	public FinanceCompany(GetAPI getApi) {
		this.getApi = getApi;
		
		
		// Initialize the arraylist
		financeCompanies = new ArrayList<String>();
		financeCompanies.add(BOFA);
		financeCompanies.add(WELLSFARGO);
		financeCompanies.add(JPM);
		financeCompanies.add(CITIGROUP);
	
		
	}

	@Override
	public double getPERatio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEPS() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDEquityRatio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLongTermAssets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShortTermAssets() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double getLoanOutstanding()
	{
		return 0;
	}


	
	@Override
	public void getstockDetails(String tickerName) throws IOException {
		// get stock data from the API
		System.out.println("Getting stock data for Finance Stocks!");
		if(!this.financeCompanies.contains(tickerName))
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Finance Company!");
		else
		{
			String getData = GetAPI.getApiData(tickerName);
			System.out.println(getData);
		}
	}

}
