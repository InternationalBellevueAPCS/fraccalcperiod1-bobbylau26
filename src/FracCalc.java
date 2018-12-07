/* Bobby Lau
   APCS Period 1
   December 6, 2018
   Checkpoint 2 */

import java.util.*;

public class FracCalc {

    /**
     * Continually prompts user to input an arithmetic expression, passes that input to produceAnswer() and then prints the components of the second operand
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
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    
    public static String produceAnswer(String input) {
        /* Break up line of input into the following strings:
           1. First operand
           2. Arithmetic operator (+ - * /)
           3. Second operand
        */
    	String operand1 = input.substring(0, input.indexOf(" "));
    	String restOfExpression = input.substring(input.indexOf(" ") + 1);
    	String operator = restOfExpression.substring(0, restOfExpression.indexOf(" "));
    	String operand2 = restOfExpression.substring(restOfExpression.indexOf(" ") + 1);
    	// Break up operands into whole number, numerator, and denominator
    	String whole1;
    	String numerator1;
    	String denominator1;
    	String whole2;
    	String numerator2;
    	String denominator2;
    	if (operand2.contains("_")) { // If the second operand is a mixed number
    		whole2 = operand2.substring(0, operand2.indexOf('_'));
    		numerator2 = operand2.substring(operand2.indexOf('_') + 1, operand2.indexOf('/'));
    		denominator2 = operand2.substring(operand2.indexOf('/') + 1);
    	}
    	else if (operand2.contains("/")) { // If the second operand is only a fraction (whole number is 0)
    		whole2 = "0";
    		numerator2 = operand2.substring(0, operand2.indexOf('/'));
    		denominator2 = operand2.substring(operand2.indexOf('/') + 1);
    	}
    	else { // If the second operand is only an integer (numerator is 0, denominator is 1)
    		whole2 = operand2;
			numerator2 = "0";
			denominator2 = "1";
    	}   		
    	
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        return ("whole:" + whole2 + " numerator:" + numerator2 + " denominator:" + denominator2);
    }

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