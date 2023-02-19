package com.ph1nix.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * PolandNotation
 *
 * @author Huayu Zhang
 * @since 2023-02-19 14:54
 */
public class PolandNotation {
    public static void main(String[] args) {
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        // separate the numbers and operators with spaces
        String suffixExpression = "3 4 + 5 * 6 -";

        /*
        1. put string into ArrayList
        2. pass ArrayList to a method, then ergodic the ArrayList and use stack to calculate
         */

        List<String> listString = getListString(suffixExpression);
        int result = calculate(listString);

        System.out.println("Result is: " + result);
    }

    /**
     * put the suffix expression into an ArrayList
     *
     * @param suffixExpression input a string contains suffix expression (seperated by spaces)
     * @return return a List of string contains suffix expression
     */
    public static List<String> getListString (String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split);
        System.out.println("Suffix Expression: " + list);
        return list;
    }

    /**
     *     1. scan from left to right, push 3 and 4 into stack
     *     2. when meet +, pop 4 and 3 (4 is stack top)
     *     3. calculate 3 + 4 = 7, then push 7 into stack
     *     4. scan to 5, push 5 into stack
     *     5. operator *, then pop 5 and 7, push 5 * 7 = 35 push into stack
     *     6. operator -, 35 - 6 = 9 push into stack
     *
     * @param listString input a List<String> contain the suffix expression
     * @return return the calculation result
     */
    public static int calculate(List<String> listString) {
        Stack<String> stack = new Stack<>();
        for (String item : listString) {
            // take out numbers by using regularExpression
            if (item.matches("\\d+")) { // match multi digits numbers
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result;
                switch (item) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> result = num1 / num2;
                    default -> throw new RuntimeException("Operator is not correct...");
                }
                stack.push(result + "");
            }
        }
        // the last data remain in stack is the final result
        return Integer.parseInt(stack.pop());
    }
}
