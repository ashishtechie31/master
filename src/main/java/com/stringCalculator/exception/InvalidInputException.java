package com.stringCalculator.exception;

import com.stringCalculator.constants.Constant;

/**
 * 
 * @author Ashish
 *		
 */

public class InvalidInputException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return Constant.INVALID_INPUT;
	}
	
}
