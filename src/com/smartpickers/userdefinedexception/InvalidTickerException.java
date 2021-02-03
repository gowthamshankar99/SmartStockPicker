package com.smartpickers.userdefinedexception;

public class InvalidTickerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTickerException(String s)
	{
		/**
		 * Call constructor of parent Exception
		 */
		super(s);
	}

}


