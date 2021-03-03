package com.smartpickers.stockdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartpickers.companies.TechnologyCompany;

public class LoadData {
	
	List<String> technologyList = null;
	List<String> financeList = null;
	List<String> automobileList = null;
	List<String> insuranceList = null;
	
	/*
	 * Intent : Create arrayList for Intial Tech Companies
	 * PRE_CONDITION : NO PRECONDITION for this method
	 */
	
	
	public LoadData() {
		// Create arrayList for Intial Tech Companies
		/**
		 * this constructor loads the intial data into the ArrayList.
		 *  And in turn the data will get loaded into the database when 
		 *  the application is started for the first time.
		 */
		technologyList = new ArrayList<String>();
		financeList = new ArrayList<String>();
		automobileList = new ArrayList<String>();
		insuranceList = new ArrayList<String>();
		
		technologyList.add("AAPL,Apple Inc");
		technologyList.add("TWTR,Twitter Inc");
		technologyList.add("PTON, Pelaton");
		technologyList.add("AMZN, Amazon");
		
		
		financeList.add("WFC, Wells Fargo");
		financeList.add("BA, Bank of America");
		
		automobileList.add("F, Ford");
		automobileList.add("TSLA, Tesla");
		
		insuranceList.add("HIG,The Hartford Fire Insurance Company");
		insuranceList.add("TRVX, Travellers");	
	}
	
	/*
	 * Traditional way to use a method to connect to the database 
	 * before performing Insertion, deletion, updation process
	 * This method will be used for every connection establishment before
	 * a db request.
	 */
    private Connection ConnectToTheSQLDB(String DatabaseLocation) {
        // SQLite connection string
        String url = "jdbc:sqlite:"+DatabaseLocation;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
    /*
     * Intent - Load the initial data into the Table - Insurance, Technology, Finance, Automobile
     */
    public void intialDataLoad(String tableName) {
    	for(String techCom : this.technologyList)
    		/*
    		 * Load the Initial Data into the Technology database
    		 */
    		insertDataIntoIndustryTable("TECHNOLOGY","test.db",techCom.split(",")[0], techCom.split(",")[1]);
    	for(String financeCom : this.financeList)
       		/*
    		 * Load the Initial Data into the Finance database
    		 */
    		insertDataIntoIndustryTable("FINANCE","test.db",financeCom.split(",")[0], financeCom.split(",")[1]);
    	for(String insuranceCom : this.insuranceList)
       		/*
    		 */
    		insertDataIntoIndustryTable("INSURANCE","test.db",insuranceCom.split(",")[0], insuranceCom.split(",")[1]);
    	for(String autombileCom : this.automobileList)
       		/*
    		 * Load the Initial Data into the Automobile database
    		 */
    		insertDataIntoIndustryTable("AUTOMOBILE","test.db",autombileCom.split(",")[0], autombileCom.split(",")[1]);
    }
    
    /*
     * Create Industry Related Tables - Insurance, Automobile, Healthcare, Finance.
     */
    public static void createTechnologyRelatedTables(String databaseNameLocation,String companyTableName) {
        // SQLite connection string
        String url = "jdbc:sqlite:" + databaseNameLocation;
        
        // SQL statement for creating a new table
        // Using autoincrement to let the sqlite db create the Key field for us
        String sql = "CREATE TABLE IF NOT EXISTS " + companyTableName  + " (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	ticker text NOT NULL,\n"
                + "	companyName text NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a the Industry Related Table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * Intent - Create StockOverview table 
     */
    
    public void createStockOverviewTable(String databaseNameLocation,String companyTableName) { 
        // SQLite connection string
        String url = "jdbc:sqlite:" + databaseNameLocation;
        
        // SQL statement for creating a new table
        // Using autoincrement to let the sqlite db create the Key field for us
        String sql = "CREATE TABLE IF NOT EXISTS " + companyTableName  + " (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	ticker text NOT NULL,\n"
                + "	companyName text,\n"
                + " peRatio double NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a the Industry Related Table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * Intent : Insert data into the overview table 
     */
    /*
     * PRE_CONDITION -
     * 	1. Database must exists
     *  2. Table COMPANY_OVERVIEW must exists
     */
    public void insertDataIntoOverviewTable(String databaseLocation,String ticker, String companyName)
    {
        String sql = "INSERT INTO " + "COMPANY_OVERVIEW" + "(ticker, peRatio) VALUES(?,?)";

        try (Connection conn = this.ConnectToTheSQLDB(databaseLocation);
                PreparedStatement insertData = conn.prepareStatement(sql)) {
        	insertData.setString(1, ticker);
        	insertData.setString(2, companyName);
        	insertData.executeUpdate();
        	System.err.println("Inserted " + ticker + " data into the table successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
    /*
     * PRE_CONDITION -
     * 	1. Database must exists
     *  2. Healthcare, Automobile, Finance, Technology Tables must exists
     */
    public void insertDataIntoIndustryTable(String tableName,String databaseLocation,String ticker, String companyName)
    {
        String sql = "INSERT INTO " + tableName + "(ticker,companyName) VALUES(?,?)";

        try (Connection conn = this.ConnectToTheSQLDB(databaseLocation);
                PreparedStatement insertData = conn.prepareStatement(sql)) {
        	insertData.setString(1, ticker);
        	insertData.setString(2, companyName);
        	insertData.executeUpdate();
        	System.err.println("Inserted " + ticker + " data into the table successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * PRE_CONDITION -
     * 	1. Database must exists
     *  2. COMPANY_OVERVIEW table must exist 
     */
    public String readOverviewTable(String databaseLocation, String firsttickerName, String secondTickerName) {
    	String sql = "SELECT ticker from COMPANY_OVERVIEW where ticker IN (\"" + firsttickerName + "\",\"" + secondTickerName + "\") order by peRatio LIMIT 1";
    	
        try (Connection conn = this.ConnectToTheSQLDB(databaseLocation);
                PreparedStatement preparedStatement  = conn.prepareStatement(sql)){

               ResultSet rs  = preparedStatement.executeQuery();
               // loop through the result set
               if (rs.next()) {
                   
                   	return rs.getString("ticker");

               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
		return null;
    }
    
    /*
     * Intent : check for ticker availability in the respective Ticker Database
     */
    /*
     * PRE_CONDITION -
     * 	1. Database must exists
     *  2. Healthcare, Technology, Automobile, Insurance table must exist 
     */
    public boolean checkForTickerAvailability(String tickerName, String tableName, String databaseLocation) {
            String sql = "select count(*)  as countrow "
                       + "FROM "  + tableName +" WHERE ticker = ?";
            
            System.out.println(sql);
     
     try (Connection conn = this.ConnectToTheSQLDB(databaseLocation);
          PreparedStatement preparedStatement  = conn.prepareStatement(sql)){
         
         // set the value
    	 preparedStatement.setString(1,tickerName);
         //
         ResultSet rs  = preparedStatement.executeQuery();

         
         // loop through the result set
         while (rs.next()) {
             System.out.println(rs.getInt("countrow"));
             
             if(rs.getInt("countrow") > 0)
            	 return true;
         }
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
	return false;
    }
        

}
