import java.io.IOException;

class BinaryTree {
    Node root;
    int length = 0;

    class Node {
        int data;
        Node right;
        Node left;

        Node(int data) {
            this.data = data;
            right = null;
            left = null;
        }

        // return data er for sorteringsalgoritmene
        int returnData() {
            return data;
        }

        // set data er for sorteringsalgoritmene
        void setData(int integer) {
            data = integer;
        }
    }

    void insert(int data) {
        length++;
        searchTree(root, data);
    }

    void searchTree(Node node, int data) {
        if (node == null) {
            root = new Node(data);
            return;
        }

        if (node.returnData() < data) {
            if (node.right == null) {
                node.right = new Node(data);
                return;
            }
            searchTree(node.right, data);
        } else if (node.returnData() > data) {
            if (node.left == null) {
                node.left = new Node(data);
                return;
            }
            searchTree(node.left, data);
        }
    }

    void contains(int data) {
        
    }

    // denne toString metoden er ikke spurt av obligen men er her for å debugge
    // lettere
    @Override
    public String toString() {
        String returnString = "Noder: ";
        return searchString(root, returnString);
    }

    String searchString(Node node, String returnString) {
        if (node == null) {
            return "";
        }

        return node.returnData() + ", " + searchString(node.left, returnString) + ""
                + searchString(node.right, returnString);
    }

    // returnerer lengen til array brukes for insertionsortmetoden og merge metoden
    int returnLength() {
        return length;
    }

    public static void main(String[] args) throws IOException {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(10);
        binaryTree.insert(46);
        binaryTree.insert(50);
        binaryTree.insert(2);
        System.out.println(binaryTree.toString());

    }
}