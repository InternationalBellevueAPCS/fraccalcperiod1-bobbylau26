/* Bobby Lau
   APCS Period 1
   December 10, 2018
   Checkpoint 3 */

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
        	String expressionAnswer = produceAnswer(expression);
        	System.out.println(expressionAnswer);
    		expression = console.nextLine();
    	}
    }
    
    /**
     * produceAnswer - This function takes a String 'expression' and produces the result.
     * @param expression - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String expression) {
        /* Break up line of input into the following strings:
           1. First operand
           2. Arithmetic operator (+ - * /)
           3. Second operand
        */
    	String operand1 = expression.substring(0, expression.indexOf(" "));
    	String restOfExpression = expression.substring(expression.indexOf(" ") + 1);
    	String operator = restOfExpression.substring(0, restOfExpression.indexOf(" "));
    	String operand2 = restOfExpression.substring(restOfExpression.indexOf(" ") + 1);
    	
    	int whole1;
    	int numerator1;
    	int denominator1;
    	int whole2;
    	int numerator2;
    	int denominator2;
    	
    	// Break up each operand into whole number, numerator and denominator
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
    
    // Final project: All answers must be reduced.
    //                Example "4/5 * 1_2/4" returns "1_1/5".
    public static String addOrSubtract(int whole1, int num1, int den1, int whole2, int num2, int den2, boolean add) {
    	int commonDen;
		int newNum;
    	if (num1 == 0) // The first operand is an integer
    		num1 = whole1;
    	else
    		if ((whole1 != 0) && (num1 != 0)) { // The first operand is a mixed number
    			num1 = (den1 * Math.abs(whole1)) + num1;
    			if (whole1 < 0)
    				num1 *= -1;
    		}
    	if (num2 == 0) // The second operand is an integer
    		num2 = whole2;
    	else
    		if ((whole2 != 0) && (num2 != 0)) { // The second operand is a mixed number
    			num2 = (den2 * Math.abs(whole2)) + num2;
    			if (whole2 < 0)
    				num2 *= -1;
			}
    	
    	if (den1 != den2) { // If denominators are different, then find the lowest common denominator
    		commonDen = leastCommonMultiple(den1, den2);
    		int multiplier1 = commonDen / den1;
    		int multiplier2 = commonDen / den2;
    		int newNum1 = num1 * multiplier1;
    		int newNum2 = num2 * multiplier2;
    		if (add)
    			newNum = newNum1 + newNum2;
    		else
    			newNum = newNum1 - newNum2;
    	}
    	else { // The denominators are the same
    		commonDen = den1;
    		if (add)
    			newNum = num1 + num2;
    		else
    			newNum = num1 - num2;
    	}
    	return(newNum + "/" + commonDen);
    }
    
    public static String multiplyOrDivide(int whole1, int num1, int den1, int whole2, int num2, int den2, boolean multiply) {
    	int newNum;
    	int newDen;
    	if (num1 == 0) // The first operand is an integer
    		num1 = whole1;
    	else
    		if ((whole1 != 0) && (num1 != 0)) { // The first operand is a mixed number
    			num1 = (den1 * Math.abs(whole1)) + num1;
    			if (whole1 < 0)
    					num1 *= -1;
			}
    	if (num2 == 0) // The second operand is an integer
    		num2 = whole2;
    	else
    		if ((whole2 != 0) && (num2 != 0)) { // The second operand is a mixed number
    			num2 = (den2 * Math.abs(whole2)) + num2;
    			if (whole2 < 0)
    					num2 *= -1;
			}
    	if (multiply) {
    		newNum = num1 * num2;
    		newDen = den1 * den2;
    	}
    	else { // When dividing fractions, multiply by the reciprocal of the second fraction (operand)
    		newNum = num1 * den2;
    		newDen = den1 * num2;
    	}
    	return(newNum + "/" + newDen);
    }
    
    /* public static String improperToMixed(String fraction) {
    	int num;
    	int den;
    	if (num > den) {
    		int whole = num / den;
    		int numerator = num - (whole * den);
    	}
    }
    */

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
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
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b) {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}