package com.ph1nix.stack;

import java.util.Scanner;

/**
 * @author Huayu Zhang
 * @create 2023-02-16-12:30 AM
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("\nshow: show stack");
            System.out.println("exit: exit program");
            System.out.println("push: push stack");
            System.out.println("pop: pop stack\n");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("Please input a int for push:");
                    arrayStack.push(scanner.nextInt());
                    break;
                case "pop":
                    System.out.println("Pop out data: " + arrayStack.pop());
                    break;
                default:
                    break;
            }
        }
        System.out.println("Program exited...");
    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
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
}
