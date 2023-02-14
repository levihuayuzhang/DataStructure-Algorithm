package com.ph1nix.linkedlist;

/**
 * @author Huayu Zhang
 * @create 2023-02-14-2:22 AM
 */
public class Joseph {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5); // has 5 node
        circleSingleLinkedList.showBoy();
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
