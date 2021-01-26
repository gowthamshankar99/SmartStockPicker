package com.smartpickers.companies;

import com.smartpickers.stockdata.GetAPI;

public class TechnologyCompany extends AbstractCompany {
	
	private GetAPI getApi;
	
	public TechnologyCompany(GetAPI getApi) {
		this.getApi = getApi;
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
	
	public void getStockDetails()
	{
		System.out.println("Getting stock details from Technology Company");
	}

	@Override
	public void getstockDetails() {
		// get stock data from the API
		
	}
	
}
