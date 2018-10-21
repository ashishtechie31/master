/**
 * 
 */
package com.stringCalculator.junit;

import org.junit.Assert;
import org.junit.Test;

import com.stringCalculator.constants.Constant;
import com.stringCalculator.service.IStringCalculatorService;
import com.stringCalculator.util.CommonUtilityForCalc;

/**
 * @author Ashish
 *	Junit test class for String Calculator Application 
 */
public class StringCalculatorTest  
{
	@Test
	public final void testValidIntegerExpression()
	{		
		String number="1";
		short validateInput = CommonUtilityForCalc.validateTestCaseInput(number);
		String []mathExp = new String[validateInput];
		mathExp[0]="(23+2)-6/3";				
			String data = CommonUtilityForCalc.validateExpression(mathExp[0]);
			String expression=IStringCalculatorService.expressionEvalution("("+data+")");			
			Assert.assertEquals("23.0", expression);		
	}

	@Test
	public void testValidDecimalExpression()
	{		
			String number="1";
			short validateInput = CommonUtilityForCalc.validateTestCaseInput(number);
			String []mathExp = new String[validateInput];		
			mathExp[0]="(23+2)-12/5";				
			String data = CommonUtilityForCalc.validateExpression(mathExp[0]);
			String expression=IStringCalculatorService.expressionEvalution("("+data+")");			
			Assert.assertEquals("22.6", expression);
		
	}
	
	@Test
	public final void testInvalidExpression()
	{			
		String number="2";
		short validateInput = CommonUtilityForCalc.validateTestCaseInput(number);
		String []mathExp = new String[validateInput];		
		mathExp[0]="(9-2)*(+55";
		mathExp[1]="(9-2))+9/";
		for (short i = 1; i <= validateInput; i++) {
			String data = CommonUtilityForCalc.validateExpression(mathExp[0]);			
			String expression=IStringCalculatorService.expressionEvalution("("+data+")");			
			Assert.assertEquals(Constant.INVALID_EXPRESSION, expression);
		}
	}	
}
