/* Bobby Lau
   APCS Period 1
   December 13, 2018
   Final */

import java.util.*;

public class FracCalc {

    /**
     * Continually prompts user to input an arithmetic expression, passes that input to produceAnswer() and then prints the result of the expression
     * until user enters "quit".
     */
    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	String expression = console.nextLine();
    	while (!expression.equalsIgnoreCase("quit")) {
        	System.out.println(produceAnswer(expression));
    		expression = console.nextLine();
    	}
    }
    
    /**
     * produceAnswer - This function takes a String 'expression' and produces the result.
     * @param expression - A fraction string that needs to be evaluated
     *      Example: input ==> "1/2 + 3/4"
     * @return the calculated result of the expression
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String expression) {
        // Break up expression into first operand, arithmetic operator (+ - * /) and second operand
    	String operand1 = expression.substring(0, expression.indexOf(" "));
    	String restOfExpression = expression.substring(expression.indexOf(" ") + 1);
    	String operator = restOfExpression.substring(0, restOfExpression.indexOf(" "));
    	String operand2 = restOfExpression.substring(restOfExpression.indexOf(" ") + 1);
    	
    	// Break up first operand into whole number, numerator and denominator
    	int whole1;
    	int numerator1;
    	int denominator1;
    	if (operand1.contains("_")) { // The first operand is a mixed number
    		whole1 = Integer.parseInt(operand1.substring(0, operand1.indexOf('_')));
    		numerator1 = Integer.parseInt(operand1.substring(operand1.indexOf('_') + 1, operand1.indexOf('/')));
    		denominator1 = Integer.parseInt(operand1.substring(operand1.indexOf('/') + 1));
    	}
    	else if (operand1.contains("/")) { // The first operand is a fraction (whole number is 0)
    		whole1 = 0;
    		numerator1 = Integer.parseInt(operand1.substring(0, operand1.indexOf('/')));
    		denominator1 = Integer.parseInt(operand1.substring(operand1.indexOf('/') + 1));
    	}
    	else { // The first operand is an integer (numerator is 0, denominator is 1)
    		whole1 = Integer.parseInt(operand1);
			numerator1 = 0;
			denominator1 = 1;
    	}
    	
    	// Break up second operand into whole number, numerator and denominator
    	int whole2;
    	int numerator2;
    	int denominator2;
    	if (operand2.contains("_")) { // The second operand is a mixed number
    		whole2 = Integer.parseInt(operand2.substring(0, operand2.indexOf('_')));
    		numerator2 = Integer.parseInt(operand2.substring(operand2.indexOf('_') + 1, operand2.indexOf('/')));
    		denominator2 = Integer.parseInt(operand2.substring(operand2.indexOf('/') + 1));
    	}
    	else if (operand2.contains("/")) { // The second operand is a fraction (whole number is 0)
    		whole2 = 0;
    		numerator2 = Integer.parseInt(operand2.substring(0, operand2.indexOf('/')));
    		denominator2 = Integer.parseInt(operand2.substring(operand2.indexOf('/') + 1));
    	}
    	else { // The second operand is an integer (numerator is 0, denominator is 1)
    		whole2 = Integer.parseInt(operand2);
			numerator2 = 0;
			denominator2 = 1;
    	}
    	
    	// Calculate the result of the expression
        if (operator.equals("+") || operator.equals("-"))
        	return(addOrSubtract(whole1, numerator1, denominator1, whole2, numerator2, denominator2, operator.equals("+")));
        else
        	return(multiplyOrDivide(whole1, numerator1, denominator1, whole2, numerator2, denominator2, operator.equals("*")));
    }
    
    /**
     * Calculate the sum or difference of the expression.
     */
    public static String addOrSubtract(int whole1, int num1, int den1, int whole2, int num2, int den2, boolean add) {
    	int commonDen;
		int newNum;
    	if (num1 == 0) // The first operand is an integer
    		num1 = whole1;
    	else
    		if ((whole1 != 0) && (num1 != 0)) { // The first operand is a mixed number.  Convert it to an improper fraction before calculating.
    			num1 = (den1 * Math.abs(whole1)) + num1;
    			if (whole1 < 0)
    				num1 *= -1;
    		}
    	if (num2 == 0) // The second operand is an integer
    		num2 = whole2;
    	else
    		if ((whole2 != 0) && (num2 != 0)) { // The second operand is a mixed number.  Convert it to an improper fraction before calculating.
    			num2 = (den2 * Math.abs(whole2)) + num2;
    			if (whole2 < 0)
    				num2 *= -1;
			}
    	
    	if (den1 != den2) { // If the denominators of the two operands are different, then rewrite both values using their lowest common denominator
    		commonDen = leastCommonMultiple(den1, den2);
    		int multiplier1 = commonDen / den1;
    		int multiplier2 = commonDen / den2;
    		int newNum1 = num1 * multiplier1;
    		int newNum2 = num2 * multiplier2;
    		if (add) // Add the numerators
    			newNum = newNum1 + newNum2;
    		else // Subtract the numerators
    			newNum = newNum1 - newNum2;
    	}
    	else { // The denominators are the same
    		commonDen = den1;
    		if (add) // Add the numerators
    			newNum = num1 + num2;
    		else // Subtract the numerators
    			newNum = num1 - num2;
    	}
    	
    	// Reduce the result to its simplest form or lowest terms
    	int divisor = greatestCommonDivisor(newNum, commonDen);
    	int reducedNum = newNum / divisor;
    	int reducedDen = commonDen / divisor;
    	if (Math.abs(reducedNum) > Math.abs(reducedDen)) { // Intermediate result is an improper fraction
    		int finalWhole = reducedNum / reducedDen;
    		int finalNum = Math.abs(reducedNum) % Math.abs(reducedDen);
    		int finalDen = Math.abs(reducedDen);
    		if (finalNum == 0) // Result is an integer
    			return(Integer.toString(finalWhole));
    		else //  Result is a mixed number
    			return(finalWhole + "_" + finalNum + "/" + finalDen);
    	}
    	else if (reducedNum == 0) // Result is zero
    		return(Integer.toString(0));
    	else if (reducedDen == 1) // Result is an integer
     		return(Integer.toString(reducedNum));
    	else // Result is a fraction
    		return(reducedNum + "/" + reducedDen);
    }
    
    /**
     * Calculate the product or quotient of the expression.
     */
    public static String multiplyOrDivide(int whole1, int num1, int den1, int whole2, int num2, int den2, boolean multiply) {
    	int newNum;
    	int newDen;
    	if (num1 == 0) // The first operand is an integer
    		num1 = whole1;
    	else
    		if ((whole1 != 0) && (num1 != 0)) { // The first operand is a mixed number.  Convert it to an improper fraction before calculating.
    			num1 = (den1 * Math.abs(whole1)) + num1;
    			if (whole1 < 0)
    				num1 *= -1;
			}
    	if (num2 == 0) // The second operand is an integer
    		num2 = whole2;
    	else
    		if ((whole2 != 0) && (num2 != 0)) { // The second operand is a mixed number.  Convert it to an improper fraction before calculating.
    			num2 = (den2 * Math.abs(whole2)) + num2;
    			if (whole2 < 0)
    				num2 *= -1;
			}
    	if (multiply) { // When multiplying fractions, multiply the numerators and then separately multiply the denominators
    		newNum = num1 * num2;
    		newDen = den1 * den2;
    	}
    	else { // When dividing fractions, multiply by the reciprocal of the second fraction (operand)
    		newNum = num1 * den2;
    		newDen = den1 * num2;
    	}
    	if (newNum > 0 && newDen < 0) { // When a fraction is negative, the numerator should be negative and the denominator should be positive
    		newNum *= -1;
    		newDen *= -1;
    	}
    	
    	// Reduce the result to its simplest form or lowest terms
    	int divisor = greatestCommonDivisor(newNum, newDen);
    	int reducedNum = newNum / divisor;
    	int reducedDen = newDen / divisor;
     	if (Math.abs(reducedNum) > Math.abs(reducedDen)) { // Intermediate result is an improper fraction
    		int finalWhole = reducedNum / reducedDen;
    		int finalNum = Math.abs(reducedNum) % Math.abs(reducedDen);
    		int finalDen = Math.abs(reducedDen);
    		if (finalNum == 0) // Result is an integer
    			return(Integer.toString(finalWhole));
    		else //  Result is a mixed number
    			return(finalWhole + "_" + finalNum + "/" + finalDen);
    	}
    	else if (reducedNum == 0) // Result is zero
    		return(Integer.toString(0));
     	else if (reducedDen == 1) // Result is an integer
     		return(Integer.toString(reducedNum));
    	else // Result is a fraction
    		return(reducedNum + "/" + reducedDen);
    }

    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b) {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}