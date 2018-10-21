package com.stringCalculator.service;

import java.util.Arrays;
import java.util.Stack;

import com.stringCalculator.constants.Constant;

/**
 * 
 * @author Ashish
 *
 */
@FunctionalInterface
public interface IStringCalculatorService {

	public void processExpresion();

	public static String expressionEvalution(String expression) {
		Stack<String> operator = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		String push = "";
		try
		{
			while (!expression.isEmpty()) {
				push = expression.substring(0, 1);
				expression = expression.substring(1);
				switch (push) {
				case "(":
					operator.push(push);
					break;
				case "-":
				case "+":
				case "*":
				case "/":
				case "^":
					while (!operator.empty()
							&& precedenceOrder(push, operator.peek())) {
						String op = operator.pop();
						double v = vals.pop();
						switch (op) {
						case "+":
							v = vals.pop() + v;
							break;
						case "-":
							v = vals.pop() - v;
							break;
						case "*":
							v = vals.pop() * v;
							break;
						case "/":
							if (v == 0) {
								return Constant.INVALID_EXPRESSION;
							}
							v = vals.pop() / v;
							break;
						case "^":
							v = Math.pow(vals.pop(), v);
							break;
						}
						vals.push(v);
					}
					operator.push(push);
					break;

				case ")":
					String op = "";
					double v;
					while (!operator.peek().equalsIgnoreCase("(")) {
						op = operator.pop();
						v = vals.pop();
						switch (op) {
						case "+":
							v = vals.pop() + v;
							break;
						case "-":
							v = vals.pop() - v;
							break;
						case "*":
							v = vals.pop() * v;
							break;
						case "/":
							v = vals.pop() / v;
							break;
						case "^":
							v = Math.pow(vals.pop(), v);
							break;
						}
						vals.push(v);
					}
					op = operator.pop();
					break;
				default:
					StringBuilder value = new StringBuilder();
					value.append(push.charAt(0));
					while (expression.length() >= 1 && expression.charAt(0) >= '0' && expression.charAt(0) <= '9') {
						value.append(expression.charAt(0));
						expression = expression.substring(1);
					}
					vals.push(Double.parseDouble(value.toString()));
				}
			}

			if (operator.size() > 0) {
				return "INVALID EXPRESSION";
			}
			if (vals.size() >= 2) {
				return "INVALID EXPRESSION";
			}
		} catch (Exception e) {
			return "INVALID EXPRESSION";
		}
		return vals.pop().toString();
	}

	public static boolean precedenceOrder(String op1, String op2) 
	{ 
		if (op2.equalsIgnoreCase("(") || op2.equalsIgnoreCase(")")) 
			return false; 
		if ((op1.equalsIgnoreCase("*") || op1.equalsIgnoreCase("/")) && (op2.equalsIgnoreCase("+") || op2.equalsIgnoreCase("-"))) 
			return false;
		else if (op1.equalsIgnoreCase("^") && (Arrays.asList("*","/","+","-").contains(op2)))
			return false;
		else
			return true; 
	}

}
