package com.ph1nix.linkedlist;

/**
 * @author Huayu Zhang
 * @create 2023-02-12-6:56 PM
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("Test for Double Linked List:\n");

        HeroNodeDouble hero1 = new HeroNodeDouble(1, "Baga", "Holyshit");
        HeroNodeDouble hero2 = new HeroNodeDouble(2, "Yoshi", "Dame");
        HeroNodeDouble hero3 = new HeroNodeDouble(3, "JOJO", "EveryGreen");
        HeroNodeDouble hero4 = new HeroNodeDouble(4, "jinjin", "BBC");

//        HeroNodeDouble hero5 = new HeroNodeDouble(5, "Baga2", "Holyshit2");
//        HeroNodeDouble hero6 = new HeroNodeDouble(6, "Yoshi2", "Dame2");
//        HeroNodeDouble hero7 = new HeroNodeDouble(7, "JOJO2", "EveryGreen2");
//        HeroNodeDouble hero8 = new HeroNodeDouble(8, "jinjin2", "BBC2");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);

        doubleLinkedList.list();

        HeroNodeDouble heroNew = new HeroNodeDouble(4, "ass", "shit");
        doubleLinkedList.update(heroNew);
        System.out.println("\nUpdated Double Linked List:");
        doubleLinkedList.list();

        doubleLinkedList.deleteNode(1);
        doubleLinkedList.deleteNode(4);
        System.out.println("\nDeleted Double Linked List:");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNodeDouble head = new HeroNodeDouble(0, "", "");

    public HeroNodeDouble getHead() {
        return this.head;
    }

    private boolean isEmpty() {
        if (head.next == null) {
            System.err.println("Linked List is empty...");
            return true;
        }
        return false;
    }

    // ergodic Double Linked List
    public void list() {
        if (isEmpty()) {
            return;
        }

        HeroNodeDouble tmp = head.next;
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    public void add (HeroNodeDouble heroNode) { // append at end
        // head not should not be modified, need a tmp reference
        HeroNodeDouble tmp = head;
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
        heroNode.pre = tmp;
    }

    public void addByOrder(HeroNodeDouble heroNode) {
        // tmp.next is target position
        HeroNodeDouble tmp = head;

        boolean flag = false; // whether the target no has already exist
        while (true) {
            if (tmp.next == null) {
                break; // empty or at the end
            }
            if (tmp.next.no > heroNode.no) { // founded
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
            if (tmp.next == null) {
                tmp.next = heroNode;
                heroNode.pre = tmp;
            } else {
                heroNode.next = tmp.next;
                heroNode.pre = tmp;
                tmp.next.pre = heroNode;
                tmp.next = heroNode;
            }
        }
    }

    // update (modify) node data by following new HeroNode.no
    // (not modify no, referencing with new HeroNode.no)
    public void update (HeroNodeDouble newHeroNode) {
        if (isEmpty()) { // empty
            return;
        }

        // find the node
        HeroNodeDouble tmp = head.next;
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
        1. node in double linked list can be found directly
     */
    public void deleteNode(int no) {
        if (isEmpty()) {
            return;
        }

        HeroNodeDouble tmp = head.next;
        boolean flag = false;
        while (true) {
            if (tmp == null) {
                break; // reach to the end of linked list (has finished ergodic)
            }
            if (tmp.no == no) { // tmp.next = target
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag != true) {
            System.err.printf("The target node %d cannot be found...", no);
        } else {
            tmp.pre.next = tmp.next; // remove the reference to target node and point to target.next
            // if tmp is not the last valid node
            if (tmp.next != null) {
                tmp.next.pre = tmp.pre;
            }
        }
    }
}


/**
 * Node for Double Linked List
 * one more attribute: pre
 *
 */
class HeroNodeDouble{
    public int no;
    public String name;
    public String nickname;
    public HeroNodeDouble next; // default to null
    public HeroNodeDouble pre; // default to null

    public HeroNodeDouble(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + this.no + ", name=" + this.name + ", nickname=" + this.nickname + "]";
    }
}
