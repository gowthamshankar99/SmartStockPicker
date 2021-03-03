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
import com.smartpickers.stockdata.LoadData;
import com.smartpickers.userdefinedexception.InvalidTickerException;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Thread {
	
	
	private static boolean tableCreate = true;
	
	private String tickerName;
	private static String result;
	private static String logFileString = "DailyTickerLog.txt";
	private String logFile; 
	public static String tableName; 
	private static AbstractCompany companyStock;
	
	public static final String dbName = "stocks.db";

	
	public Main(String tickerName) {
		this.tickerName = tickerName;
	}	

	/*
	 * Intent: main method  - the program starts executing from here.
	 * The application intends to ask a series of Questions to the User related to investment 
	 * to help the User make better investment decisions
	 */
	public static void main(String[] args) throws IOException, ParseException, InvalidTickerException, InterruptedException {
		LoadData ld = new LoadData();
		if(tableCreate)
		{
			// create the technologyTables
			LoadData.createTechnologyRelatedTables(dbName, "TECHNOLOGY");
			LoadData.createTechnologyRelatedTables(dbName, "FINANCE");
			LoadData.createTechnologyRelatedTables(dbName, "AUTOMOBILE");
			LoadData.createTechnologyRelatedTables(dbName, "INSURANCE");
			LoadData.createTechnologyRelatedTables(dbName, "HEALTHCARE");
			ld.createStockOverviewTable(dbName, "COMPANY_OVERVIEW");
			ld.intialDataLoad(dbName);
			
			ld.checkForTickerAvailability("AAPL", "TECHNOLOGY" ,dbName);
		}
		
		// what is today's date 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  

	    LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		
		// Create File object for Log file
		File logFile = new File("DailyTickerLog.txt");

		
		//AbstractCompany companyStock = null;
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
		System.out.println("4. Get Stock Quote for Multiple Tickers");
		System.out.println("5. Add ticker to the watchlist");
		System.out.println("6. Exit the Application");
		
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
						SerializeIndustryObject(companyStock);
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
			case 5:
				System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Insurance\n4. Automobile\n");
				int getStockOption5 = scanner.nextInt();
				System.out.println("Enter the ticker Name and Company Name in comma separated list! For Example - AAPL\n");
				String tickerName5 = scanner.next();
				
				if(getStockOption5 == 1)
				{
					ld.insertDataIntoIndustryTable("TECHNOLOGY", dbName, tickerName5.split(",")[0], tickerName5.split(",")[1]);
				}
				else if(getStockOption5 == 2)
				{
					ld.insertDataIntoIndustryTable("FINANCE", dbName, tickerName5.split(",")[0], tickerName5.split(",")[1]);
				}
				else if(getStockOption5 == 3)
				{
					ld.insertDataIntoIndustryTable("INSURANCE",dbName , tickerName5.split(",")[0], tickerName5.split(",")[1]);
				}
				else if(getStockOption5 == 4)
				{
					ld.insertDataIntoIndustryTable("AUTOMOBILE", dbName, tickerName5.split(",")[0], tickerName5.split(",")[1]);
				}

				break;
			case 6:
				System.exit(0);
				break;
			case 4:
				// call getstockquoteAPI
				
				System.out.println("Select the Industry in which the Company is part of! \n1. Technology\n2. Finance\n3. Insurance\n4. Automobile\n");
				int getStockOptionMult = scanner.nextInt();
				System.out.println("Enter the ticker Name! For Example - AAPL,TWTR,PTON - in a comma separated list\n");
				String tickerNameMult = scanner.next();
				if(getStockOptionMult == 1)
					// create the technology Object
					companyStock = new TechnologyCompany(api);
				else if(getStockOptionMult == 2) 
					// create the Finance Object
					companyStock = new FinanceCompany(api);

				else if(getStockOptionMult == 3)
					// create the Insurance Object
					companyStock = new InsuranceCompany(api);

				else if(getStockOptionMult == 4) 
					// create the Automobile Object
					companyStock = new AutomobileIndustryCompany(api);
				

				// Create Thread Array 
				Main[] threadArray = new Main[tickerNameMult.split(",").length];
				
				
				// Split the ticker  names
				for(int i =0;i<threadArray.length;i++)
				{
					threadArray[i] = new Main(tickerNameMult.split(",")[i]);
					threadArray[i].start(); // Multiple threads will be spawned based on the number of ticker quotes asked for
				}
				
				// Split the ticker  names
				for(int i =0;i<threadArray.length;i++)
				{
					threadArray[i].join(); // Multiple threads will be spawned based on the number of ticker quotes asked for
				}
				
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
						tableName = "TECHNOLOGY";
						
					}
					else if(getStockLister == 2) {
						// create the Finance Object
						companyStock = new FinanceCompany(api);
						listerFile = new File("Finance.txt");		
						tableName = "FINANCE";
					}
					else if(getStockLister == 3) {
						// create the Insurance Object
						companyStock = new InsuranceCompany(api);
						listerFile = new File("Insurance.txt");
						tableName = "INSURANCE";
						
					}
					else if(getStockLister == 4) {
						// create the Automobile Object
						companyStock = new AutomobileIndustryCompany(api);
						
						listerFile = new File("Automobile.txt");
						tableName = "AUTOMOBILE";
					}
					
					stockDetailExtractor = new StockDetailExtractor(companyStock, api);
					// check if the tickers are available
					
					/*
					 * This statement queries the data from the database 
					 * and returns the count of rows which has the ticker. 
					 * if the count is < 1 -> then checkForTickerAvailablity returns false  - this means ticker is not part of the respective industry table
					 * if the count is > 1 -> then checkForTickerAvailablity returns true  - this means ticker is present in the respective industry table.
					 */
					boolean firstTicker = ld.checkForTickerAvailability(twoTickers.split(",")[0], tableName, dbName);
					boolean secondTicker = ld.checkForTickerAvailability(twoTickers.split(",")[1], tableName, dbName);
					//boolean firstTicker = companyStock.readFile(companyStock, listerFile, twoTickers.split(",")[0]);
					//boolean secondTicker = companyStock.readFile(companyStock, listerFile, twoTickers.split(",")[1]);
					
					if(!(firstTicker && secondTicker))
					{
						System.err.println("One or Both tickers are not part of the same Industry! Please try again!\nFor example - AAPL,TWTR is a correct input as both Apple and Twitter as part of the same Industry!");
						
					}
					else
					{
						
						// Insert data into the DB with the PERatio into the COMPANY_OVERVIEW table
						
						ld.insertDataIntoOverviewTable(dbName, twoTickers.split(",")[0], stockDetailExtractor.getPERatio(twoTickers.split(",")[0]));
						ld.insertDataIntoOverviewTable(dbName, twoTickers.split(",")[1], stockDetailExtractor.getPERatio(twoTickers.split(",")[1]));
						
						/*
						 *  using selection order by statement - Query against the COMPANY_OVERVIEW table 
						 *  and find the top 1st row to get the better buy of the two companies
						 */
						
						String betterStock = ld.readOverviewTable(dbName, twoTickers.split(",")[0], twoTickers.split(",")[1]);
						 
						
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
	public static void SerializeIndustryObject(AbstractCompany abstractCompany) throws FileNotFoundException, IOException {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ObjectSerializer.txt"))) {
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
	public static void deSerializeIndustryObject(String fileLocation) throws FileNotFoundException, IOException, ClassNotFoundException	 {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("ObjectSerializer.txt"))) {
		
				// Display the Object contents
					System.out.println((AbstractCompany) in.readObject());
			}
	}
	
	public void run() {
		System.out.println("Succesfully executed my first thread " + this.tickerName);
		

		// API calls with Alpha Stock Advantage 
		
		GetAPI getAPI = new GetAPI();
		try {
			
			result = getAPI.getApiData(this.tickerName, "GLOBAL_QUOTE");
			parseObject();
			//System.out.println(apiDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void parseObject() throws IOException, ParseException {
		/*
		 * Parse the JSON object using JSONParser to grab the 
		 * intended data only from the JSON Dump
		 */
		
		// what is today's date 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  

	    LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		
		System.out.println("what is in the result string " +  result);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(result);
		JSONObject obj2 = (JSONObject)obj.get("Global Quote");
        // Write data to the file to reduce API calls
        companyStock.writeDailyLogs(logFileString, dtf.format(now) + "-" + this.tickerName + "\n" + "Current Price " + obj2.get("05. price") + "\n" + "Previous High " + obj2.get("03. high") + "\n" + "Previous Low " + obj2.get("04. low") + "\n" + "Current Volume " + obj2.get("06. volume") + "\n" + "********************");
        
        System.out.println("Current Price " + obj2.get("05. price"));
        System.out.println("Previous High " + obj2.get("03. high"));
        System.out.println("Previous Low " + obj2.get("04. low"));
        System.out.println("Current Volume " + obj2.get("06. volume"));		
	}

}


