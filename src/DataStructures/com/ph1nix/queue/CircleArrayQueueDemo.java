/* Differ from Array Queue
    1. front point to first element, init to 0
    2. rear point to later position of last element init to 0
    3. whether queue is full, (rear + 1) % maxSize == front
    4. whether queue is empty, rear == front
    5. quantity of valid data in queue, (rear + maxSize) % maxSize
 */


package com.ph1nix.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        System.out.println("Testing Circle array demo:\n");

        CircleArray arrayQueue = new CircleArray(4);
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

class CircleArray {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isFull () {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    public boolean isEmpty() {
        return this.rear == this.front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("Queue full...");
            return;
        }

        arr[rear] = n; // already last element + 1
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty...");
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.err.println("Queue is empty...");
            return;
        }

        for (int i = front; i < front + size(); i ++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // quantity of valid data in arr
    private int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty...");
        }

        return arr[front];
    }

}
