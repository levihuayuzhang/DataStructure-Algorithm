package com.ph1nix.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; // get user input
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("\ns for show");
            System.out.println("e for exit");
            System.out.println("a for add");
            System.out.println("g for get");
            System.out.println("h for head");

            key = scanner.next().charAt(0); // get a char

            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("Input data: ");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("The got data is: %d", res);
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("The data at head is: %d", res);
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Program exited...");
    }
}

// using array to simulate a queue
class ArrayQueue {
    private int maxSize; // max store capability
    private int front; // point to head of queue (index - 1)
    private int rear; // point to tail of queue (last data)
    private int[] arr; // array for storing data

    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1; // init to -1, when 1st data added, point to 0 (index of 1st data)
    }

    // whether the queue is full
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // whether the queue is empty
    public boolean isEmpty() {
        return rear == front;
    }

    // add data into queue
    public void addQueue(int n) {
        if (this.isFull()) {
            System.out.println("Already full, cannot add more data...");
            return;
        }

//        rear++;
        arr[++rear] = n;
    }

    public int getQueue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is empty, cannot get data...");
        }

//        front++;
        return arr[++front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty...");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // just show font data (not get data) (not moving front)
    public int headQueue () {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is empty, cannot get data...");
        }

        return arr[front + 1];
    }
}
