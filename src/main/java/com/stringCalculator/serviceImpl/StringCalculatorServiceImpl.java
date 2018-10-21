/**
 * 
 */
package com.stringCalculator.serviceImpl;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.stringCalculator.constants.Constant;
import com.stringCalculator.exception.InvalidInputException;
import com.stringCalculator.service.IStringCalculatorService;
import com.stringCalculator.util.CommonUtilityForCalc;

/**
 * @author Ashish
 * This class validate and process the expression with results.  
 */
public class StringCalculatorServiceImpl implements IStringCalculatorService {

	@Override
	public void processExpresion() {
		Scanner input=null;
		try {			
			input = new Scanner(System.in);
			String number = input.next();
			short validateInput = CommonUtilityForCalc.validateTestCaseInput(number);
			if (validateInput == 0) {
				throw new InvalidInputException();
			}
			String []mathExp = new String[validateInput]; 
			for (short i = 0; i < validateInput; i++) {
				input = new Scanner(System.in);
				mathExp[i] = input.next();
			}

			for (short i = 1; i <= validateInput; i++) {
				try
				{
					String data = CommonUtilityForCalc.validateExpression(mathExp[i-1]);
					if (data.equalsIgnoreCase(Constant.INVALID_EXPRESSION)) {
						System.out.println("Case #" + i + ": " + data);
						continue;
					} else {
						try {
							String expression=IStringCalculatorService.expressionEvalution("("+data+")");
							if (data.equalsIgnoreCase(Constant.INVALID_EXPRESSION)) {
								System.out.println("Case #" + i + ": " + data);
								continue;
							}
							else if (data.contains(".")) {
								System.out.println("Case #" + i + ": " + new DecimalFormat("0.00").format(expression));
							} else {
								System.out.println("Case #" + i + ": " + expression.substring(0,expression.indexOf(".")));
							}
						} catch (Exception e) {

							System.out.println("Case #" + i + ": " + Constant.INVALID_EXPRESSION);
							continue;
						}
					}
				} catch (Exception Exception) {
					System.out.println("Case #" + i + ": " + Constant.INVALID_EXPRESSION);
				}

			}
		} catch (InputMismatchException mismatchException) {
			System.out.println("USER INPUT WRONG");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		finally
		{	
			Optional<Scanner> optional = Optional.ofNullable(input);
			if(!optional.isPresent())
				input.close();
		}
	}	
}