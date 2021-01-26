package com.smartpickers.stocks;

import java.util.AbstractCollection;
import java.util.Scanner;

import com.smartpickers.companies.AbstractCompany;
import com.smartpickers.companies.FinanceCompany;
import com.smartpickers.companies.InsuranceCompany;
import com.smartpickers.companies.TechnologyCompany;
import com.smartpickers.stockdata.GetAPI;

public class Main {

	public static void main(String[] args) {
		
		
		// Create the company stock declaration 
		
		AbstractCompany companyStock;
		
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
	
	public void stockProcessor(String whatIndustry, AbstractCompany companyStock, GetAPI api)
	{
		if(whatIndustry.contentEquals("Technology"))
		{
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new TechnologyCompany(api);
			companyStock.getstockDetails();
		}
		else if(whatIndustry.contentEquals("Finance")) {
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new FinanceCompany(api);
			companyStock.getstockDetails();
		
		}
		else if(whatIndustry.contentEquals("Insurance")) {
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new InsuranceCompany(api);
			companyStock.getstockDetails();
			
		}
		else if(whatIndustry.contentEquals("Automobile")) {
			
			// Create Automobile Object - Objects are created dynamically using runtime Polymorphism
		}
				
	}

}
