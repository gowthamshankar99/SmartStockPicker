package com.smartpickers.stocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		
		// what is today's date 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  

	    LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		
		// Create File object for Log file
		File logFile = new File("DailyTickerLog.txt");
		
		AbstractCompany companyStock = null;
		
		// Get API Object
		 GetAPI api = new GetAPI();
		 
		
		// Get the Set of Questions
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		System.err.println("\nWelcome to Smart Stock Picker");
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
				/*
				 *  First, read the log file for the day , 
				 *  check to see if the API call for the day 
				 *  has already been made
				 */
					
				String datafromLogFile = readLogFile(logFile, tickerName, dtf.format(now));
				if (datafromLogFile.contentEquals("data not present"))
					// The call for the day has never been made - grab the data from the api
					stockProcessor(getStockOption, companyStock, api,tickerName, logFile,dtf.format(now));
				else
					System.out.println("Reading data from the File");
					System.out.println(datafromLogFile);
				break;
				
			case 2:
				 // call smart stock picker API - yet to be implemented
				 System.out.println("Call smart stock picker API");
				 break;
			case 3:
				// call get the list of all major tech industry stocks names - yet to be implemented
				System.out.println();
				break;
			default:
				System.out.println("Wrong option selected!");
				break;
				
		}	
		}

 	}
	
	/*
	 * Intent : Passes the User input to the API and gets the Stock Quote back.
	 */
	public static void stockProcessor(int whatIndustry, AbstractCompany companyStock, GetAPI api,String tickerName, File logFile,String todaysDate) throws IOException, ParseException
	{
		/*
		 * PRE_CONDITION: tickerName should be a valid ticker Name.
		 * tickerName should be a one of the ticker name that's available in the respective
		 * ticker File (Automobile/Finance/Healthcare/Insurance/Technology)
		 */
		
		/*
		 * POST_CONDITION : If all precondition posts are satisfied, the stock quote, high price,
		 * low price, volume are returned back
		 */

		final String DELIMITER_LINE = "********************"; 
		String result = "";
		if(whatIndustry == 1) //  .contentEquals("Technology")
		{
			// Create Technology Object - Objects are created dynamically using runtime Polymorphism
			companyStock = new TechnologyCompany(api);
			result = companyStock.getstockDetails(tickerName);
			// get active Users count
			
			// Downcasting the object to Tech object to access TechnologyCompany Class related Methods
			 TechnologyCompany techCompnyForActiveUsersCount = (TechnologyCompany)companyStock;
			 techCompnyForActiveUsersCount.getActiveSubscribersCount();
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
		
		if(result == null || result == "")
		{
			System.out.println("Something went wrong while performing the API call!");
			System.exit(99);
		}
		
		/*
		 * Parse the JSON object using JSONParser to grab the 
		 * intended data only from the JSON Dump
		 */
		System.out.println("what is in the result string " +  result);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(result);
		JSONObject obj2 = (JSONObject)obj.get("Global Quote");
		
        // Write data to the file to reduce API calls
        companyStock.writeDailyLogs(logFile, todaysDate + "-" + tickerName + "\n" + "Current Price " + obj2.get("05. price") + "\n" + "Previous High " + obj2.get("03. high") + "\n" + "Previous Low " + obj2.get("04. low") + "\n" + "Current Volume " + obj2.get("06. volume") + "\n" + DELIMITER_LINE);
        
        System.out.println("Current Price " + obj2.get("05. price"));
        System.out.println("Previous High " + obj2.get("03. high"));
        System.out.println("Previous Low " + obj2.get("04. low"));
        System.out.println("Current Volume " + obj2.get("06. volume"));				
	}
	
	public static String readLogFile(File file,String tickerName, String todaysDate) throws FileNotFoundException {	
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			if(scanner.next().trim().contentEquals(todaysDate + "-" + tickerName))
				return scanner.nextLine() + "\n" + scanner.nextLine() + "\n" + scanner.nextLine() + "\n" + scanner.nextLine();
		}
		scanner.close();
		
		return "data not present";
	}

}
