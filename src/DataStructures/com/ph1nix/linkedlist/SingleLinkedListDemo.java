/* Linked List
    1. using node for storing
    2. each node has `data` area and `next` area (point to next node)
    3. each node does not need to be sequential storing
    4. two types: has `head` node or not
    5. head node don't store data
    6. tail node data is null
 */

package com.ph1nix.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "Baga", "Holyshit");
        HeroNode hero2 = new HeroNode(2, "Yoshi", "Dame");
        HeroNode hero3 = new HeroNode(3, "JOJO", "EveryGreen");
        HeroNode hero4 = new HeroNode(4, "jinjin", "BBC");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        // test without order
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        // with order
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
//        // already exist
//        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        // update list
        HeroNode newHeroNode = new HeroNode(3, "Hoa", "hey");
        singleLinkedList.update(newHeroNode);
        System.out.println("\nUpdated Linked List: ");
        singleLinkedList.list();

        // delete node
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
        singleLinkedList.deleteNode(2);
//        singleLinkedList.deleteNode(3);
        System.out.println("\nDeleted Linked List: ");
        singleLinkedList.list();
    }
}

class SingleLinkedList {
    // int head node, should not be modified
    private HeroNode head = new HeroNode(0, "", "");

    // add nodes to single linked list
    // added node's next node should point to tail node
    // IGNORING no (Sequence)
    public void add (HeroNode heroNode) {
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
