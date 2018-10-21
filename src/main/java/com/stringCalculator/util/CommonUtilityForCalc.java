package com.stringCalculator.util;

import java.util.regex.Pattern;

import com.stringCalculator.constants.Constant;
/**
 * 
 * @author Ashish
 *	Having common methods required through out the application.
 */
public class CommonUtilityForCalc {

	// To avoid instantiation.
	private  CommonUtilityForCalc()
	{		
	}
	
	public static short validateTestCaseInput(String input){
		if (Pattern.matches("[0-9]{1,2}|100", input)) {
			return Short.parseShort(input);
		}
		return 0;
    }

	public static  String validateExpression(String expression){
		if (Pattern.matches("[\\d+[+,/,*,.,\\-,^,),(]\\d+]+", expression)) {
			return  startAndEndDelimeterValidation(expression) ;
		}
		return Constant.INVALID_EXPRESSION;
    }
	
	
public static  String startAndEndDelimeterValidation(String expression){
	if (Pattern.matches("[+,/,*,\\-,^,)](.)*|(.)*[+,/,*,\\-,^,(]", expression)) {
		return  Constant.INVALID_EXPRESSION;
	}
	return (expression) ;
    }

}
