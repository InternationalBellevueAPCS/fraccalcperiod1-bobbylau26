/* Bobby Lau
   APCS Period 1
   December 3, 2018 */

import java.util.*;

public class FracCalc {

    /**
     * Prompts user to input an arithmetic expression, passes that input to produceAnswer() and then prints the second operand.
     */
    public static void main(String[] args) {
        // Checkpoint 2: Accept user input multiple times.
    	
    	Scanner console = new Scanner(System.in);
    	String expression = console.nextLine();
    	String expressionAnswer = produceAnswer(expression);
    	System.out.print(expressionAnswer);
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
    	
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        return operand2;
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