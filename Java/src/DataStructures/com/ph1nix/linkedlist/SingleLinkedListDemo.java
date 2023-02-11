/* Linked List
    1. using node for storing
    2. each node has `data` area and `next` area (point to next node)
    3. each node does not need to be sequential storing
    4. two types: has `head` node or not
    5. head node don't store data
    6. tail node data is null
 */

package com.ph1nix.linkedlist;

import java.awt.*;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "Baga", "Holyshit");
        HeroNode hero2 = new HeroNode(2, "Yoshi", "Dame");
        HeroNode hero3 = new HeroNode(3, "JOJO", "EveryGreen");
        HeroNode hero4 = new HeroNode(4, "jinjin", "BBC");

        HeroNode hero5 = new HeroNode(5, "Baga2", "Holyshit2");
        HeroNode hero6 = new HeroNode(6, "Yoshi2", "Dame2");
        HeroNode hero7 = new HeroNode(7, "JOJO2", "EveryGreen2");
        HeroNode hero8 = new HeroNode(8, "jinjin2", "BBC2");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

//        // test without order
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        // with order
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero7);
//        // already exist
//        singleLinkedList.addByOrder(hero3);

        // with order
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero6);
        singleLinkedList2.addByOrder(hero8);

        System.out.println("Original list1: ");
        singleLinkedList.list();

        System.out.println("\nOriginal list2: ");
        singleLinkedList2.list();

        // merge two ordered list
        System.out.println("\nMerged list: ");
        SingleLinkedList merged = merge(singleLinkedList, singleLinkedList2);
        merged.list();

        // not modify list
        System.out.println("\nReverse print list: ");
        singleLinkedList.reversePrint(singleLinkedList.getHead());


        // reverse single linked list
        singleLinkedList.reverseList(singleLinkedList.getHead());
        System.out.println("\nReversed list: ");
        singleLinkedList.list();

        // update list
        HeroNode newHeroNode = new HeroNode(3, "Hoa", "hey");
        singleLinkedList.update(newHeroNode);
        System.out.println("\nUpdated Linked List: ");
        singleLinkedList.list();

        // delete node
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
//        singleLinkedList.deleteNode(2);
//        singleLinkedList.deleteNode(3);
        System.out.println("\nDeleted Linked List: ");
        singleLinkedList.list();

        // count node (list length)
        System.out.println("\nLength for now is (count for valid nodes): "+ singleLinkedList.getLength(singleLinkedList.getHead()));

        // last k node in list
        HeroNode result = singleLinkedList.findLastNodeByIndex(singleLinkedList.getHead(), 2);
        System.out.println(result);

    }

    // merge 2 list by order
    public static SingleLinkedList merge (SingleLinkedList firstList, SingleLinkedList secondList) {

        SingleLinkedList targetList  = new SingleLinkedList();

        HeroNode tmp1 = firstList.getHead().next;
        HeroNode tmp2 = secondList.getHead().next;
        HeroNode tmp3 = targetList.getHead();

        while (tmp1 != null && tmp2 != null) {
            if (tmp1.no > tmp2.no) {
                tmp3.next = tmp2;
                tmp2 = tmp2.next;
            } else {
                tmp3.next = tmp1;
                tmp1 = tmp1.next;
            }
            tmp3 = tmp3.next;
        }


        tmp3.next = tmp1 == null ? tmp2 : tmp1;

        return targetList;
    }
}

class SingleLinkedList {
    // int head node, should not be modified
    private HeroNode head = new HeroNode(0, "", "");

    // add nodes to single linked list
    // added node's next node should point to tail node
    // IGNORING no (Sequence)
    public void add (HeroNode heroNode) { // append at end
        // head not should not be modified, need a tmp reference
        HeroNode tmp = head;
        while(true) {
            // founded last node of single linked list
            if (tmp.next == null) {
                break;
            }
            // not found
            tmp = tmp.next;
        }
        // when exit while, tmp pointed to last valid node
        tmp.next = heroNode; // new node added to linked list
    }

    // add node with sequence no
    /*
        1. find target location with tmp reference
        2. newNode.next -> tmp.next
        3. tmp.next -> newNode
     */
    public void addByOrder(HeroNode heroNode) {
        // tmp should be previous node of target position
        HeroNode tmp = head;
        boolean flag = false; // whether the target no has already exist
        while (true) {
            if (tmp.next == null) { // tmp at the last valid node
                break;
            }
            if (tmp.next.no > heroNode.no) { // position found, add (insert) to tmp.next
                break;
            } else if (tmp.next.no == heroNode.no){ // the target heroNode already exist
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag) {
            System.err.printf("The hero no.%d has already exist...", heroNode.no);
        } else { // insert
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }

    //whether the link list is empty
    private boolean isEmpty() {
        if (head.next == null) {
            System.err.println("Linked List is empty...");
            return true;
        }
        return false;
    }

    // update (modify) node data by following newHeoroNode.no
    // (not modify no, referencing with newHeoroNode.no)
    public void update (HeroNode newHeroNode) {
        if (isEmpty()) { // empty
            return;
        }

        // find the node
        HeroNode tmp = head.next;
        boolean flag = false; // whether we have found the target node
        while (true) {
            if (tmp == null) {
                break; // reach to the end of linked list (has finished ergodic)
            }

            if (tmp.no == newHeroNode.no) { // founded
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag == false) { // not found
            System.err.printf("Hasn't found the target node %d...\n", newHeroNode.no);
        } else {
            tmp.name = newHeroNode.name;
            tmp.nickname = newHeroNode.nickname;
        }
    }

    /* delete node
        1. find the previous node of target node (tmp)
        2. tmp.next = tmp.next.next
        3. the deleted node has node reference pointed to, the GCC will collect the node (thus, just remove the reference is sufficient)
     */
    public void deleteNode(int no) {
        if (isEmpty()) {
            return;
        }

        HeroNode tmp = head;
        boolean flag = false;
        while (true) {
            if (tmp == null) {
                break; // reach to the end of linked list (has finished ergodic)
            }
            if (tmp.next.no == no) { // tmp.next = target
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag != true) {
            System.err.printf("The target node %d cannot be found...", no);
        } else {
            tmp.next = tmp.next.next; // remove the reference to target node and point to target.next
        }
    }

    /** get quantity of valid node (length) of linked list (if has head node, remove head node)
     *
     * @param head the head node of linked list
     * @return return quantity of valid node in linked list
     */
    public int getLength(HeroNode head) {
        if (isEmpty()) {
            return 0;
        }

        int length = 0;
        HeroNode tmp = head.next;
        while (tmp != null) {
            length ++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * find the last k (index) node in single linked list
     * 1. find ergodic the list, getLength()
     * 2. then ergodic (length - index) times from the head to find the target
     * 3. if found, return node, otherwise, return null
     *
     * @param head the head node
     * @param index the descending index (start from tail to head)
     * @return if found, return node, otherwise, return null
     */
    public HeroNode findLastNodeByIndex(HeroNode head, int index) {
        if (isEmpty()) {
            return null; // not found
        }
        // first ergodic to get the length
        int size =  getLength(head);
        //second ergodic (size - index) to find the target node
        if (index <= 0 || index > size) { // index out of range
            return null;
        }

        HeroNode tmp = head.next;
        for (int i = 0; i < size - index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    /**
     * Reverse the Single linked list
     * 1. new node called reverseHead
     * 2. ergodic original list, pick up each node and put into start of new list (reverseHead.next)
     * 3. when ergodic finished, head.next = reverseHead.next
     *
     * @param head
     */
    public void reverseList (HeroNode head) {
        // empty or only have 1 node, no need to reverse
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode tmp = head.next;
        HeroNode next = null; // point to tmp.next
        HeroNode reverseHead =  new HeroNode(0, "", "");

        while(tmp != null) {
            next = tmp.next; // cache the next node of current tmp reference, needed later

            tmp.next = reverseHead.next; // point current tmp next to the new list head next
            reverseHead.next = tmp;

            tmp = next;
        }
        head.next = reverseHead.next;
    }

    // print list in descending order
    // 1. reverse then print (not good, because this will change the original structure of list)
    // 2. use stack
    public void reversePrint(HeroNode head) {
        if (isEmpty()) {
            return;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode tmp = head.next;

        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop().toString());
        }
    }

    public void list() {
        if (isEmpty()) {
            return;
        }

        HeroNode tmp = head.next;
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    public HeroNode getHead() {
        return this.head;
    }

}

// each object is a node (example for hero ranking)
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + this.no + ", name=" + this.name + ", nickname=" + this.nickname + "]";
    }
}
