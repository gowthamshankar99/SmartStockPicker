package com.smartpickers.companies;

import com.smartpickers.stockdata.GetAPI;

public class FinanceCompany extends AbstractCompany {
	
	private GetAPI getApi;
	
	public FinanceCompany(GetAPI getApi) {
		this.getApi = getApi;
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
	public void getstockDetails(String tickerName) {
		// TODO Auto-generated method stub
		
	}

}
