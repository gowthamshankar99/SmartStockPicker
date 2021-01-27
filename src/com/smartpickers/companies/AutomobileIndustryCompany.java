package com.smartpickers.companies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smartpickers.stockdata.GetAPI;

public class AutomobileIndustryCompany extends AbstractCompany implements IStockIndex  {
	
	
	static final String FORD = "F";
	static final String TESLA = "TSLA";
	 
	private GetAPI getApi;
	
	private List<String> automobileCompanyList;
	
	public AutomobileIndustryCompany(GetAPI getApi) {
		this.getApi = getApi;
		
		//initialize the list to add the companies that can be queried
		automobileCompanyList = new ArrayList<String>();
		automobileCompanyList.add(FORD);
		automobileCompanyList.add(TESLA);
	}
	
	public double getSalesoutput() {
		return 0;
	}

	@Override
	public String getstockDetails(String tickerName) throws IOException {
		// get stock data from the API
		System.out.println("Getting stock data for Automobile Stocks!");
		if(!this.automobileCompanyList.contains(tickerName))
			System.err.println("We cannot get a quote at this time! The problem is because the Company is not a Automobile Company!");
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
