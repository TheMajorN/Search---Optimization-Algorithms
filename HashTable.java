import java.util.Scanner;

public class HashTable {

    static Scanner keyboard = new Scanner(System.in); // Scanner for user input.
    static Node[] table = new Node[10];               // Establish hash table array of nodes.
    static String[] options = {"insert", "delete", "dumphash", "quit"}; // Input commands.

    public static void main(String[] args) {

        HashTable ht = new HashTable(); // For calling non-static methods in main
        boolean run = true;             // Looping run boolean

        intro();    // Flair

    while (run) {   // While loop for user to input aforementioned commands
        String output = keyboard.nextLine();
        if (output.equals(options[0])) {
            ht.insert();
        } else if (output.equals(options[1])) {
            ht.delete();
        } else if (output.equals(options[2])) {
            ht.dumpHash();
        } else if (output.equals(options[3])) {
            run = false;
        }
    }
        System.out.println("Goodbye.");
    }

    public void insert() {  // O(1) time
        System.out.print("Enter an int to insert:>");
        int value = keyboard.nextInt(); // User input
        Node placed = new Node();   // Make a new node at the front.
        placed.val = value;         // Make the value of that node the inputted value.
        placed.next = table[value % 10];    // Make the next value the next open hash.
        table[value % 10] = placed; // Make the hash location the node.
        System.out.println(value + " has been inserted!");  // Flair
    }

    public void delete() {  // O(n) time
        System.out.print("Enter an int to delete:>");
        int value = keyboard.nextInt(); // User input
        Node temp = table[value % 10];  // Make the node at the front temp.
        try {   // A try loop to catch the value potentially not existing.
            if (table[value % 10].val == value) {   // Deletion if it's the first node.
                table[value % 10] = table[value % 10].next;
            } else {
                while (temp.next.val != value) {    // Deletion if it's anywhere in the middle.
                    temp = temp.next;
                }
                if (temp.next.next == null) {   // Deletion if the desired value is at the end.
                    temp.next = null;
                } else {
                    temp.next = temp.next.next; // Move on if it's not the value.
                }
            }
            System.out.println(value + " has been deleted!");   // Flair
        } catch(NullPointerException e) { System.out.println("Value not found!"); } // Catch no value.
    }

    public void dumpHash() {    // O(n)
        System.out.println();
        for (int i = 0; i < table.length; i++) {    // Going down set hash table.
            System.out.print("[" + i + "]:");   // Print key location on hash.
            Node temp = table[i];   // Establishes temp node on selected hash.
            while (temp != null) {  // Loop to print all nodes on hash.
                System.out.print(" (" + temp.val + ")");    // Print node values.
                temp = temp.next;   // Move on after node is printed until the end.
            }
            System.out.print("\n"); // Next line.
        }
    }

    public static void intro() {    // Flair
        System.out.println("+-----------------------------------+");
        System.out.println("|               Hello!              |");
        System.out.println("|   Input the following commands:   |");
        System.out.println("| insert | delete | dumphash | quit |");
        System.out.println("+-----------------------------------+");
        System.out.print(":>");
    }
}

class Node {

    Node next = null;   // Establishes the existence of a node after the current.
    int val;    // Establishes that a node can have a value.

    public Node() {
        int val;    // Establishes that the value of the node must be an integer.
    }
}