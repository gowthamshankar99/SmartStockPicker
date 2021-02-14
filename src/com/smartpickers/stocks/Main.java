package com.smartpickers.stocks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
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
import com.smartpickers.companies.StockDetailExtractor;
import com.smartpickers.companies.TechnologyCompany;
import com.smartpickers.stockdata.GetAPI;
import com.smartpickers.userdefinedexception.InvalidTickerException;

public class Main {

	/*
	 * Intent: main method  - the program starts executing from here.
	 * The application intends to ask a series of Questions to the User related to investment 
	 * to help the User make better investment decisions
	 */
	public static void main(String[] args) throws IOException, ParseException, InvalidTickerException {		
		
		// what is today's date 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  

	    LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		
		// Create File object for Log file
		File logFile = new File("DailyTickerLog.txt");
		String logFileString = "DailyTickerLog.txt";
		
		AbstractCompany companyStock = null;
		StockDetailExtractor<AbstractCompany> stockDetailExtractor = null;
		
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
					
				String datafromLogFile = readLogFile(logFileString, tickerName, dtf.format(now));
				if (datafromLogFile.contentEquals("data not present"))
					// The call for the day has never been made - grab the data from the api
				{
					try {
						stockProcessor(getStockOption, companyStock, api,tickerName, logFileString,dtf.format(now));	
					}
					catch(InvalidTickerException ex)
					{
						System.err.println(ex.getMessage());
					}
					
				}
					
				else
					System.out.println("Reading data from the File");
					System.out.println(datafromLogFile);
				break;
				
			case 2:
				// call get the list of all major tech industry stocks names - yet to be implemented	 
				 System.out.println("Call smart stock picker API");
				// call getstockquoteAPI
					
				System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Insurance\n4. Automobile\n");
				int getStockLister = scanner.nextInt();
				File listerFile = null;
				if(getStockLister == 1)
				{
					// create the technology Object
					companyStock = new TechnologyCompany(api);
					listerFile = new File("Technology.txt");
				}
				else if(getStockLister == 2) {
					// create the Finance Object
					companyStock = new FinanceCompany(api);
					listerFile = new File("Finance.txt");				
				}
				else if(getStockLister == 3) {
					// create the Insurance Object
					companyStock = new InsuranceCompany(api);
					listerFile = new File("Finance.txt");
					
				}
				else if(getStockLister == 4) {
					// create the Automobile Object
					companyStock = new AutomobileIndustryCompany(api);
					listerFile = new File("Automobile.txt");
				}
				System.out.println("\nThe Companies listed under the Requested Industry are\n");
				companyStock.printTickerFile(listerFile);
				
				break;
			case 3:
				System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Insurance\n4. Automobile\n");
				getStockLister = scanner.nextInt();
				
				// call smart stock picker API - yet to be implemented
				System.out.println("Pick 2 companies from the same Industry! Example - APPL,TWTR");
				System.out.println("Please make sure to input it in a comma separated String like APPL,TWTR");
				
				String twoTickers = scanner.next();
				if(twoTickers.split(",").length != 2)
				{
					System.err.println("The Input entered is not in the correct format!");
					System.err.println("Please enter the details in this format - AAPL,TWTR (comma separated)!!");
				
				}
				else
				{
					// Check and see if both companies are part of the Same Industry
					listerFile = null;
					if(getStockLister == 1)
					{
						// create the technology Object
						// IStockExtractor 
						companyStock = new TechnologyCompany(api);
						listerFile = new File("Technology.txt");
					}
					else if(getStockLister == 2) {
						// create the Finance Object
						companyStock = new FinanceCompany(api);
						listerFile = new File("Finance.txt");				
					}
					else if(getStockLister == 3) {
						// create the Insurance Object
						companyStock = new InsuranceCompany(api);
						listerFile = new File("Finance.txt");
						
					}
					else if(getStockLister == 4) {
						// create the Automobile Object
						companyStock = new AutomobileIndustryCompany(api);
						
						listerFile = new File("Automobile.txt");
					}
					
					stockDetailExtractor = new StockDetailExtractor(companyStock, api);
					// check if the tickers are available
					boolean firstTicker = companyStock.readFile(companyStock, listerFile, twoTickers.split(",")[0]);
					boolean secondTicker = companyStock.readFile(companyStock, listerFile, twoTickers.split(",")[1]);
					
					if(!(firstTicker && secondTicker))
					{
						System.err.println("One or Both tickers are not part of the same Industry! Please try again!\nFor example - AAPL,TWTR is a correct input as both Apple and Twitter as part of the same Industry!");
						
					}
					else
					{
						
						String betterStock =  Double.parseDouble(stockDetailExtractor.getPERatio(twoTickers.split(",")[0])) < Double.parseDouble(stockDetailExtractor.getPERatio(twoTickers.split(",")[1])) ? twoTickers.split(",")[0] : twoTickers.split(",")[1];
						
						System.err.println("Comparing the PERatio between the two stocks " + twoTickers.split(",")[0] + " and " +twoTickers.split(",")[1] + ", " + betterStock + " is the Better Buy!");
	                    System.out.println();				
					}
				}
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
	public static void stockProcessor(int whatIndustry, AbstractCompany companyStock, GetAPI api,String tickerName, String logFile,String todaysDate) throws IOException, ParseException, InvalidTickerException
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
	
	/*
	 * Intent : Read the Log File and check if the Log contains the Ticker Details
	 * if -> it contains, return the data from the log file
	 * else -> return  "data not present"
	 */
	public static String readLogFile(String file,String tickerName, String todaysDate) throws FileNotFoundException {	
		/*
		 * PRE_CONDITION: The File file needs to exist in order for the application to read the file. 
		 * The application assumes that, the file exist.
		 */
		
		/*
		 * POST_CONDITION: The method returns data from the log file or returns "no data present"
		 */
	// Using BufferedReader and InputStreamReader to read the Log File	
	try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
		
		String linesFromOutputFile = "";
		
		while((linesFromOutputFile = br.readLine()) != null)
		{
			/*
			 * if a line is present in the log file - it will always contain the 4 lines 
			 * 
			 * Sample Data from Log File
			 * 
			 *  2021/02/13-AAPL
				Current Price 135.3700
				Previous High 135.5300
				Previous Low 133.6921
				Current Volume 60145130
				********************
			 */
			
			if(linesFromOutputFile.trim().contentEquals(todaysDate + "-" + tickerName))
				return linesFromOutputFile + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine();
		}
			
	}
	catch(IOException ex)
	{
		System.err.println("Something wrong with the file read");
	}
		return "data not present";
	}
	
	/*
	 * Intent : Serialize Industry Object and write it into a file
	 */
	/*
	 * PRE_CONDITION: The file must exist for the method to work 
	 * Object should not be  null
	 */
	public void SerializeIndustryObject(AbstractCompany abstractCompany) throws FileNotFoundException, IOException {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("serializedObject"))) {
			// Write object into
			out.writeObject(abstractCompany);
			
		}
	}
	/*
	 * Intent : Serialize Industry Object and Read from the file.
	 */
	/*
	 * PRE_CONDITION: The file must exist for the method to work 
	 *  Class must exist in order for the deserialization to work
	 * 
	 */	
	public void deSerializeIndustryObject(String fileLocation) throws FileNotFoundException, IOException, ClassNotFoundException	 {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("serializedObject"))) {
		
				// Display the Object contents
					System.out.println((AbstractCompany) in.readObject());
			}
	}
}

