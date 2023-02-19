package com.ph1nix.stack;

/**
 * Calculator: Use stack handle numbers and symbols(operators) from a formula and calculate the result.
 *
 * @author Huayu Zhang
 * @since 2023-02-17-4:55 PM
 */
public class Calculator {
    public static void main(String[] args) {
        // single digit number test
//        String expression = "3+2*6-2"; // 13
//        String expression = "7+2*6-4"; // 15

        // multi digit number test
        String expression = "70+2*6-4"; // 78

        // two stack: one for numbers, another for operators
        CalculatorArrayStack numStack = new CalculatorArrayStack(10);
        CalculatorArrayStack operatorStack = new CalculatorArrayStack(10); // use int to store char (ASCII)

        int index = 0; // for scanning

        int num1, num2, operator, result;
        num1 = num2 = operator = result = 0;

        char ch; // current scanned char from formula string (tmp variable)
        String joinNum = ""; // for joining the number-chars (handling multi-digit numbers)

        // scanning
        while (index < expression.length()) {
            // get chars from expression
            ch = expression.substring(index, index + 1).charAt(0);
            // determine the ch
            if (operatorStack.isOperator(ch)) { // ch is operator
                // whether the operator stack is empty
                if (operatorStack.isEmpty()) {
                    operatorStack.push(ch);
                } else {  // not empty
                    // if the priority of `current operator` is smaller or equals to the operator in stack
                    // pop one operator from stack and calculate with 2 number popped from number stack
                    // then push the calculation result into number stack
                    // push `current operator` into operator stack
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        result = numStack.calculate(num1, num2, operator);
                        numStack.push(result);
                        operatorStack.push(ch);
                    } else { // priority greater
                        operatorStack.push(ch);
                    }
                }
            } else { // ch is number
//                numStack.push(ch - 48); // in ASCII, int 1 is 49, int 2 is 50... (only for 1 digit)

                /*
                    Multi-digit number:
                    1. if next char of number is not a number (an operator), then push
                    2. if the next char of number is still a number, keep this step until meet 1st step
                    3. define a String variable for joining the number-chars
                 */

                joinNum += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(joinNum));
                } else if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(joinNum));
                    // clear joinNum
                    joinNum = "";
                }
            }
            index++;
        }

        // if the scanning is finished, then pop out the numbers and operator from stack by order accordingly
        while (!operatorStack.isEmpty()) {
            num1  = numStack.pop();
            num2  = numStack.pop();
            operator = operatorStack.pop();
            result = numStack.calculate(num1, num2, operator);
            numStack.push(result);
        }
        System.out.printf("Expression %s = %d\n", expression, numStack.pop());


    }
}

// Not using inheritance (keep all implementation in single file)
class CalculatorArrayStack {
    private final int maxSize;
    private final int[] stack;
    private int top = -1;

    public CalculatorArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == this.maxSize - 1;
    }

    public  boolean isEmpty() {
        return top == -1;
    }

    public void push (int value) {
        if (isFull()) {
            System.err.println("Stack is full...");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty...");
        }
//        int value = stack[top];
//        top--;
//        return value;

        return stack[top--];
    }

    // ergodic stack from stack top
    public void list() {
        if (isEmpty()) {
            System.err.println("Stack is empty...");
            return;
        }

        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    // return priority of the operators
    // using numbers to indicate priority (bigger is higher)
    public int priority (int operator) { // int for char
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1; // assume this formula only contain `+` `-` `*` `/`
        }
    }

    public boolean isOperator (char value) {
        return value == '+' || value == '-'|| value == '*' || value == '/';
    }

    public int calculate(int num1, int num2, int operator) {
        int result = 0;
        switch (operator) { // enhanced switch statement
            case '+' -> result = num1 + num2;
            case '-' -> result = num2 - num1;
            case '*' -> result = num1 * num2;
            case '/' -> result = num2 / num1;
            default -> {
            }
        }

        return result;
    }

    // get the stack top data by not popping it out
    public int peek () {
        return stack[top];
    }
}



