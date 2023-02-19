package com.ph1nix.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Polish Notation
 *
 * @author Huayu Zhang
 * @since 2023-02-19 14:54
 */
public class PolishNotation {
    public static void main(String[] args) {
        /*
        infix expression to suffix expression:
        1. 1 + (( 2 + 3 ) * 4) - 5 => 1 2 3 + 4 * + 5
        2. for convenience, transform string of "1 + (( 2 + 3 ) * 4) - 5"into corresponding List in
        infix expression (ArrayList [1, +, (. (, 2, +, 3, ), *, 4, ), -, 5])
         */
        String infixExpression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println("Infix expression: " + infixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("Suffix expression: " + suffixExpressionList);

        System.out.println("Result is: " + calculate(suffixExpressionList));


//        suffixExpressionTest();
    }

    private static void suffixExpressionTest() {
    /*
    define the polish notation
    (3+4)*5-6 => 3 4 + 5 * 6 -
    separate the numbers and operators with spaces
    */
        String suffixExpression = "30 4 + 5 * 6 -";
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

    /**
     * transform infix expression string into a corresponding ArrayList
     *
     * @param s infix expression string
     * @return corresponding ArrayList
     */
    public static List<String> toInfixExpressionList (String s) {
        List<String> listString = new ArrayList<>();
        int i = 0;
        String str; // for multi digits splicing
        char c; // temperately store while ergodic
        do {
            // if is an operator (not number), put c into listString
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                listString.add("" + c);
                i++;
            } else { // is number
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                listString.add(str);
            }
        } while (i < s.length());

        return listString;
    }

    public static List<String> parseSuffixExpressionList(List<String> listString) {
        Stack<String> stack = new Stack<>();
//        // stack2 has no pop(), need reverse order to export, thus use List<String>
//        Stack<String> stack2 = new Stack<>();
        List<String> list = new ArrayList<>();

        for (String item : listString) {
            if (item.matches("\\d+")) {
                list.add(item);
            } else if (item.equals("(")) {
                stack.push(item);
            } else if (item.equals(")")) {
                // if is ")", pop the operators from stack into list, until meet "(" (drop these pair of brackets)
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop(); // pop() the "(" from stack (drop the inner bracket)
            } else {
                // when priority of item is less than the operator in stack top
                // then push the stack top into list
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }

        while (stack.size() != 0) {
            list.add(stack.pop());
        }

        return list;
    }
}

// return priority of operator
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue (String operation) {
        int result = 0;
        switch (operation) {
            case "+" -> result = ADD;
            case "-" -> result = SUB;
            case "*" -> result = MUL;
            case "/" -> result = DIV;
            default -> System.out.println("Invalid operator..."); // dropping brackets
        }
        return result;
    }
}
