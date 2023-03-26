package com.ph1nix.hashtable;

import java.util.Scanner;

/**
 * hashTableDemo
 *
 * @author Huayu Zhang
 * create time: 2023-03-26 02:30:38
 */
public class hashTableDemo {
    public static void main(String[] args) {
        HashTable hasTab = new HashTable(7);

        String key = "";
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Add");
            System.out.println("List");
            System.out.println("Find");
            System.out.println("Exit");

            key = s.next();
            switch (key) {
                case "add":
                    System.out.println("ID:");
                    int id = s.nextInt();
                    System.out.println("Name:");
                    String name = s.next();
                    Employee emp = new Employee(id, name);
                    hasTab.add(emp);
                    break;
                case "list":
                    hasTab.list();
                    break;
                case "find":
                    System.out.println("Input id:");
                    id = s.nextInt();
                    hasTab.findEmpByID(id);
                    break;
                case "exit":
                    s.close();
                    System.out.println("Exited...");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

class HashTable {
    private EmployeeLinkedList[] empLLA;
    private int size; // quantity of Linked List

    public HashTable (int size) {
        this.size = size;
        this.empLLA = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLLA[i] = new EmployeeLinkedList();
        }
    }

    public void add (Employee emp) {
        int empLLNO = hasFun(emp.id);
        empLLA[empLLNO].add(emp);
    }

    public void list () {
        for (int i = 0; i < this.size; i++) {
            empLLA[i].list();
        }
    }

    public int hasFun (int id) {
        return id % this.size;
    }

    public void findEmpByID (int id) {
        int empLinkedListNO = hasFun(id);
        Employee emp = empLLA[empLinkedListNO].findEmployeeByID(id);
        if (emp != null) {
            System.out.printf("Found in Linked list %d at id = %d\n", empLinkedListNO + 1, id);
        } else {
            System.out.println("Not found");
        }
    }
}

class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {

        this.id = id;
        this.name = name;
    }
}

class EmployeeLinkedList {
    private Employee head;

    public void add (Employee emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Employee tmp = head;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next; // move backward
        }
        tmp.next = emp;
    }

    public void list () {
        if (head == null) {
            System.out.println("Null Linked List...");
            return;
        }

        System.out.println("This linked list is");
        Employee tmp = head;
        while (true) {
            System.out.printf("==> id = %d name = %s\t", tmp.id, tmp.name);
            if (tmp.next == null) { // at last node
                break;
            }
            tmp = tmp.next;
        }
        System.out.println();
    }

    public Employee findEmployeeByID (int id) {
        if (head == null) {
            System.out.println("Linked List is empty...");
            return null;
        }

        Employee tmp = head;
        while (true) {
            if (tmp.id == id) {
                break;
            }

            if (tmp.next == null) {
                tmp = null;
                break;
            }
            tmp = tmp.next;
        }

        return tmp;
    }
 }
