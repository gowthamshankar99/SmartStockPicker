package com.smartpickers.companies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smartpickers.stockdata.GetAPI;

public class HealthCareCompany extends AbstractCompany {
	
	// Create Static ticker names for now - this will eventually get loaded from a
	// Databases in the coming weeks
	
	 static final String ANTHEM = "ANTM";
	 static final String CIGNA = " CI";
	 static final String CVS = "CVS";
	 static final String HUMANA = "HUM";
	 
	

	// Creating ArrayList to hold Stock Tickers
	List<String> healthcareCompanies;
	
	private GetAPI getAPI;
	
	 public HealthCareCompany(GetAPI getAPI) {
		 this.getAPI = getAPI;
		 
		 healthcareCompanies = new ArrayList<String>();
		 healthcareCompanies.add(ANTHEM);
		 healthcareCompanies.add(CIGNA);
		 healthcareCompanies.add(CVS);
		 healthcareCompanies.add(HUMANA);
		 
	 }

	@Override
	public double getPERatio() {
		// yet to be implemented
		return 0;
	}

	@Override
	public double getEPS() {
		// yet to be implemented
		return 0;
	}

	@Override
	public double getDEquityRatio() {
		// yet to be implemented
		return 0;
	}

	@Override
	public double getLongTermAssets() {
		// yet to be implemented
		return 0;
	}

	@Override
	public double getShortTermAssets() {
		// yet to be implemented
		return 0;
	}

	@Override
	public void getstockDetails(String tickerName) throws IOException {
		// get stock data from the API
		System.out.println("Getting stock data for Insurance Stocks!");
		if(!this.healthcareCompanies.contains(tickerName))
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Insurance Company!");
		else
		{
			String getData = getAPI.getApiData(tickerName);
			System.out.println(getData);
		}
	}

}
