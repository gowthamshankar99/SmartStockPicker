package com.smartpickers.stocks;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.smartpickers.companies.AbstractCompany;
import com.smartpickers.companies.AutomobileIndustryCompany;
import com.smartpickers.companies.FinanceCompany;
import com.smartpickers.companies.InsuranceCompany;
import com.smartpickers.companies.TechnologyCompany;
import com.smartpickers.stockdata.GetAPI;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		
		
		// Create the company stock declaration 
		
		AbstractCompany companyStock = null;
		
		// Get API Object
		 GetAPI api = new GetAPI();
		 
		
		// Get the Set of Questions
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Smart Stock Picker");
		System.out.println("Pick the Offerring from the options below!(Type 1, 2 or 3)");
		System.out.println("1. Get Stock Quote");
		System.out.println("2. Get the list of all Major Tech Industry Stocks");
		System.out.println("3. Compare two stocks and find a better Buy ?");
		
		int getOption = scanner.nextInt();		
		
		switch(getOption) {
			case 1:
				// call getstockquoteAPI
				
				System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Insurance\n4. Automobile\n");
				int getStockOption = scanner.nextInt();
				System.out.println("Enter the ticker Name! For Example - AAPL\n");
				String tickerName = scanner.next();
				stockProcessor(getStockOption, companyStock, api,tickerName);
				break;
				
			case 2:
				 // call smart stock picker API
				 System.out.println("Call smart stock picker API");
				 break;
			case 3:
				// call get the list of all major tech industry stocks names
				break;
			default:
				System.out.println("Wrong option selected!");
				
		
		// Close the Scanner Object 
				
		scanner.close();
				
		}
 	}
	
	public static void stockProcessor(int whatIndustry, AbstractCompany companyStock, GetAPI api,String tickerName) throws IOException, ParseException
	{
		String result = "";
		if(whatIndustry == 1) //  .contentEquals("Technology")
		{
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new TechnologyCompany(api);
			result = companyStock.getstockDetails(tickerName);
		}
		else if(whatIndustry == 2) { // .contentEquals("Finance")
			System.out.println("should be here" + tickerName);
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new FinanceCompany(api);
			result = companyStock.getstockDetails(tickerName);
		
		}
		else if(whatIndustry == 3) { // .contentEquals("Insurance")
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new InsuranceCompany(api);
			result = companyStock.getstockDetails(tickerName);
			
		}
		else if(whatIndustry == 4) { // .contentEquals("Automobile")
			
			// Create Automobile Object - Objects are created dynamically using runtime Polymorphism
			companyStock  = new AutomobileIndustryCompany(api);
			result = companyStock.getstockDetails(tickerName);
		}
		
		if(result == null)
		{
			System.out.println("Something went wrong while performing the API call!");
			System.exit(99);
		}
		
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(result);
		JSONObject obj2 = (JSONObject)obj.get("Global Quote");
		
        
        System.out.println("Current Price " + obj2.get("05. price"));
        System.out.println("Previous High " + obj2.get("03. high"));
        System.out.println("Previous Low " + obj2.get("04. low"));
        System.out.println("Current Volume " + obj2.get("06. volume"));
        
				
	}

}
