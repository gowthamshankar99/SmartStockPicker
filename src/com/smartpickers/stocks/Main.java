package com.smartpickers.stocks;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Scanner;

import com.smartpickers.companies.AbstractCompany;
import com.smartpickers.companies.AutomobileIndustryCompany;
import com.smartpickers.companies.FinanceCompany;
import com.smartpickers.companies.InsuranceCompany;
import com.smartpickers.companies.TechnologyCompany;
import com.smartpickers.stockdata.GetAPI;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
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
		System.out.println();
		if(getOption == 1 || getOption == 2 || getOption == 3)
		{
			System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Automobile\n4. Insurance");
			System.out.println();
			int getStockOption = scanner.nextInt();
			System.out.println("Enter the ticker Name! For Example - AAPL");
			System.out.println();
			String tickerName = scanner.next();
			stockProcessor(getStockOption, companyStock, api,tickerName);
		}
		
		
		switch(getOption) {
			case 1:
				// call getstockquoteAPI
				System.out.println("Call stock quote API");
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
	
	public static void stockProcessor(int whatIndustry, AbstractCompany companyStock, GetAPI api,String tickerName) throws IOException
	{
		if(whatIndustry == 1) //  .contentEquals("Technology")
		{
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new TechnologyCompany(api);
			companyStock.getstockDetails(tickerName);
		}
		else if(whatIndustry == 2) { // .contentEquals("Finance")
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new FinanceCompany(api);
			companyStock.getstockDetails(tickerName);
		
		}
		else if(whatIndustry == 3) { // .contentEquals("Insurance")
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new InsuranceCompany(api);
			companyStock.getstockDetails(tickerName);
			
		}
		else if(whatIndustry == 4) { // .contentEquals("Automobile")
			
			// Create Automobile Object - Objects are created dynamically using runtime Polymorphism
			companyStock  = new AutomobileIndustryCompany(api);
			companyStock.getstockDetails(tickerName);
		}
				
	}

}
