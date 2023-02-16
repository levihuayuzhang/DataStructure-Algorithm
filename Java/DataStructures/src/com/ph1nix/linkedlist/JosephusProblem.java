package com.ph1nix.linkedlist;


/**
 * Josephus Problem
 * 1. Using Circle Single Linked List
 * 2. Each people (Boy) as a node
 * 3. Not killing, but get out of circle
 *
 * @author Huayu Zhang
 * @create 2023-02-14-2:22 AM
 */
public class JosephusProblem {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5); // has 5 node
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

// Circle Single Linked List
class CircleSingleLinkedList {
    private Boy first = new Boy(-1);

    /**
     * Create circle single linked list and add node
     * @param nums the quantity of nodes
     */
    public void addBoy (int nums) {
        if (nums < 1) {
            System.err.println("Incorrect value for nums...");
            return;
        }

        Boy tmpBoy = null;
        // create nodes
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); // circle has only one node
                tmpBoy = first;
            } else {
                tmpBoy.setNext(boy);
                boy.setNext(first);
                tmpBoy = boy;
            }
        }
    }

    // ergodic current circle single list
    public void showBoy() {
        // empty
        if (first == null) {
            System.err.println("Circle Linked List is empty...");
            return;
        }

        Boy tmpBoy = first;
        while (true) {
            System.out.printf("Boy's no is %d\n", tmpBoy.getNo());
            if (tmpBoy.getNext() == first) { // only has one node
                break;
            }
            tmpBoy = tmpBoy.getNext();
        }
    }


    /**
     * count the sequence of node get out of circle linked list
     *
     * @param startNo no of start node
     * @param countNum count times (after how many times to get out)
     * @param nums original quantity of node
     */
    public void countBoy (int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.err.println("Incorrect input parameters...");
            return;
        }
        Boy tmp = first;
        while(true) {
            if (tmp.getNext() == first) { // tmp is pointing at last node
                break;
            }
            tmp = tmp.getNext();
        }
        // before count down, move first and tmp for (startNo - 1) times
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            tmp = tmp.getNext();
        }
        // start count down, move first and tmp for (countNum - 1) times
        // this would loop in the circle, until only the last one node remain in the circle
        while(true) {
            if (tmp == first) { // only has one node
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                tmp = tmp.getNext();
            }
            // now first point to the node should get out circle
            System.out.printf("Node %d out!\n", first.getNo());
            first = first.getNext();
            tmp.setNext(first);
        }
        System.out.printf("The node remain in circle is %d...\n", first.getNo());

    }


}

// boy as a node
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return this.next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
