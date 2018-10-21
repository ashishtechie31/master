package com.stringCalculator;

import com.stringCalculator.service.IStringCalculatorService;
import com.stringCalculator.serviceImpl.StringCalculatorServiceImpl;



/**
 * 
 * @author Ashish
 *	This is main class which is required to run.
 */
public class StringCalculator {
	public static void main(String[] args) {
		IStringCalculatorService stringCalculatorService  = new StringCalculatorServiceImpl();
		stringCalculatorService.processExpresion();
	}	
}
