package Assignment;

import java.util.Scanner;

public class LinkedListStack {

    static Scanner keyboard = new Scanner(System.in);               // Input import
    static String[] options = {"push", "pop", "dumpstack", "quit"}; // Command Options
    //private Node head;                                              // Reference to head node

    public static void main(String[] args) {

        LinkedListStack lls = new LinkedListStack();    // For running non-static methods in main

        boolean run = true;                             // Creating a loop in the program

        intro();    // Flair

        while (run) {
        try {
            String output = keyboard.nextLine();

            if (output.equals(options[0])) {        // options[0] is "push"
                lls.push();
            } else if (output.equals(options[1])) { // options[1] is "pop"
                lls.pop();
            } else if (output.equals(options[2])) { // options[2] is "dumpstack"
                lls.dumpStack();
            } else if (output.equals(options[3])) { // options[3] is "quit"
                run = false;                        // Sets the run boolean to false so that the program terminates.
            }
        } catch (NullPointerException e) { System.out.println("The stack is empty!"); } // Output text rather than terminate the program in a null-pointer exception.
    }
        System.out.println("Goodbye."); // Flair
    }


    public void push() {    // O(1) Time
        System.out.print("Type an int to push:>");  // Input an int
        int a = keyboard.nextInt();
        Node temp = new Node(a);    // Value for new node is inputted int.
        temp.next = head;           // The node after temp is set to head so that the node "positions" itself in front of head.
        head = temp;                // Head switches places with temp as the new head so that it is now connected.
        System.out.println(a + " pushed");  // Flair
    }


    public void pop() { // O(1) Time
        Node temp = head;   // Sets temp as head.
        head = temp.next;   // Moves head to the next node over so that temp is the new head.
        Node next = temp.next.next; // Creates a node right after the newly declared head node.
        temp.next = next;   // Temp becomes the node after head so that its previous node now has nothing keeping track of it, so that it disconnects and is "popped"
        System.out.println("Popped");
    }

    public void dumpStack() {   // O(n) Time
        Node temp = head;
        if (head == null) {     // If the head is null, then there is nothing in the stack.
            System.out.println("[]");   // Will print this if nothing is in the stack.
        } else {
            System.out.print("[");
            while (temp.next != null) {     // Iterate through the list and print every value.
                System.out.print(temp.val + ", ");
                temp = temp.next;
            }
            System.out.print(temp.val); // This is here because the value at the bottom of the stack won't print otherwise.
            System.out.print("]\n");
        }
    }

    public static void intro() {    // Flair
        System.out.println("+-------------------------------+");
        System.out.println("|             Hello!            |");
        System.out.println("| Input the following commands: |");
        System.out.println("| push | pop | dumpstack | quit |");
        System.out.println("+-------------------------------+");
        System.out.print(":>");
    }
}

class Node {

    Node next = null;   // Establishes the existence of a node after the current.
    int val;    // Establishes that a node can have a value.

    public Node(int a) {
        val = a;    // Establishes that the value of the node must be an integer.
    }

     
}




