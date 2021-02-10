package com.smartpickers.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.smartpickers.companies.IStockIndex;
import com.smartpickers.companies.StockDetailExtractor;
import com.smartpickers.companies.TechnologyCompany;
import com.smartpickers.stockdata.GetAPI;

public class GenericsTest {

	@Test
	public void testAPICalls() throws IOException {
		/* Intent: check if the API calls to get the data from api WORKS
		 * 
		 */
		GetAPI getAPI = new GetAPI();
		String data = getAPI.getApiData("AAPL", "OVERVIEW");
		
		// make sure the call works ok
		assertNotEquals(data, "issue with the API call");
	}
	
	@Test
	public void PERatioDataCheck() throws IOException, ParseException {
		/*
		 * Intent: Check if getPERatio method returns any data.
		 */
		/*
		 * POST_CONDITION: the condition passes if data is not null
		 */
		GetAPI getAPI = new GetAPI();
		TechnologyCompany tc = new TechnologyCompany(getAPI);
		StockDetailExtractor<TechnologyCompany> sde = new StockDetailExtractor<TechnologyCompany>(tc, getAPI);
		String data = sde.getPERatio("AAPL");
		assertNotNull(data);
	}

}
