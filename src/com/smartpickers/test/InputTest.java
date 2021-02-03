package com.smartpickers.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class InputTest {

	@Test
	public void testingAutomobileFileAvailablity() {
		File file = new File("Automobile.txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testingFinanceFileAvailablity() {
		File file = new File("Finance.txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testingTechnologyFileAvailablity() {
		File file = new File("Technology.txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testingHealthcareFileAvailablity() {
		File file = new File("Healthcare.txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testingLogFileAvailablity() {
		File file = new File("DailyTickerLog.txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testingTickerAvailablityonIndustryFiles() throws FileNotFoundException
	{
		int counter = 0;
		int expected = 0;
		File file = new File("Technology.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			counter++;
		}
		
		assertTrue(counter != 0);
		
	}

	
}
