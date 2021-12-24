public class BinarySearchTree {

    Node root;          // Establish global root
    int dumpNumber = 0;
}

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(7);
        bst.insert(21);
        bst.dumpTree();
        bst.delete(20);
        bst.delete(21);
        bst.dumpTree();
        bst.delete(35);
    }



    public void insert(int val) {
        boolean inserted = false;
        if (root == null) {     // For inserting the root
            root = new Node();
            root.val = val;
            root.left = null;
            root.right = null;
            inserted = true;
        }
        Node temp = root;
        while (!inserted) { // Search through the tree
            if (val < temp.val) {   // Move left if value is less
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    temp.left = new Node(); // Insertion steps
                    temp.left.val = val;
                    inserted = true;
                }
            }
            if (val > temp.val) {   // Move right if value is greater
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    temp.right = new Node(); // Insertion steps
                    temp.right.val = val;
                    inserted = true;
                }
            }
        }
        System.out.println(val + " has been inserted!");
    }

    public void delete(int val) {
        Node parent = root; // Establish parent
        Node temp = root;   // Establish searcher start point

        while (temp != null && temp.val != val) { // Search through tree
            parent = temp;
            if (val < temp.val) {   // Move left if value is less
                temp = temp.left;
            } else {                // Move right if value is greater
                temp = temp.right;
            }
        }
        if (temp == null) { // For the value not existing in the bst
            System.out.println(val + " is not valid!");
            return; // Prevent null pointer exception
        }
        if (temp.left == null && temp.right == null) {  // Handling a leaf
            if (temp != root) {
                if (parent.left == temp) {  // Assign parent to next value
                    parent.left = null;     // above temp as to safely
                } else {                    // delete it and not produce
                    parent.right = null;    // a null pointer.
                }
            } else {  // Handling if the bst is only the root.
                root = null;
            }
            System.out.println(val + " has been deleted!");
        } else if (temp.left != null && temp.right != null) { // Handling two children
            Node replacement = temp.left;   // Will replace the deleted node

            int value = replacement.val;    // Replacement node's value
            delete(replacement.val);    // Recursively use the delete method on the
            temp.val = value;           // replacement to shift up the rest of the values.
            System.out.println(val + " has been deleted!");
        } else {        // Handling one child
            Node child; // Establish child
            if (temp.left != null) {    // Left or right of temp becomes the child
                child = temp.left;      // so that the parent can safely delete
            } else {                    // the child.
                child = temp.right;
            }
            if (temp == parent.left) {  // Replace temp with child node,
                parent.left = child;    // shifting the correct left or
            } else {                    // right child node up.
                parent.right = child;
            }
        }
    }


    public void dumpTree() {
        Node temp = root;
        if (dumpNumber == 0) {
            if (root != null) {
                System.out.println("  " + root.val);
            }
            if (temp.left != null && temp.right != null) {
                System.out.println(" / \\");
                System.out.println(temp.left.val + " " + temp.right.val);
                if (temp.left.left != null && temp.right.left != null) {
                    System.out.println("/  /");
                    System.out.println(temp.left.left.val + "  " + temp.right.left.val);
                }
                else if (temp.left.right != null) {
                    System.out.println("\\");
                    System.out.println(temp.left.left.right.val);
                }
                if (temp.left.left.right != null) {
                    System.out.println("\\");
                    System.out.println(temp.left.left.right.val);
                }
            }
            dumpNumber = 1;
        } else {
            if (root != null) {
                System.out.println("  " + root.val);
                System.out.println(" / \\");
            }
            if (root.left != null && root.right != null) {
                System.out.println(root.left.val + "  " + root.right.val);
            }
            if (root.left.right != null) {
                System.out.println(" \\");
                System.out.println(" " + root.left.right.val);
            }
        }
    }
}



class Node {

    Node left = null;
    Node right = null;
    int val;

    public Node() { int val; }
}