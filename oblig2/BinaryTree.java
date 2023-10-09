
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
        searchTree(root, data);
    }

    void searchTree(Node node, int data) {
        if (node == null) {
            root = new Node(data);
            length++;
            return;
        }

        if (node.returnData() < data) {
            if (node.right == null) {
                node.right = new Node(data);
                length++;
                return;
            }
            searchTree(node.right, data);
        } else if (node.returnData() > data) {
            if (node.left == null) {
                node.left = new Node(data);
                length++;
                return;
            }
            searchTree(node.left, data);
        }
    }

    Boolean contains(int data) {
        return hasTree(root, data);
    }

    Boolean hasTree(Node node, int data) {
        if (node == null) {
            return false;
        }

        if (node.returnData() == data) {
            return true;
        }

        if (node.returnData() < data) {
            return hasTree(node.right, data);
        }

        if (node.returnData() > data) {
            return hasTree(node.left, data);
        }

        return false;
    }

    void remove(int data) {
        length--;
        removeTree(root, data);
    }

    Node findMin(Node node) {
        if (node.left.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    void removeTree(Node node, int data) {
        if (node == null) {
            return;
        }

        // hvis noden vi prøver å få vekk er roten
        if (node.returnData() == data) {
            if (node.left != null && node.right != null) {
                if (node.right.left != null) {
                    Node erstattNode = findMin(node.right);
                    erstattNode.left.left = node.left;
                    erstattNode.left.right = node.right;
                    root = erstattNode.left;
                    erstattNode.left = null;
                } else {
                    root = node.right;
                    node.right.left = node.left;
                }
            } else if (node.left != null) {
                root = node.left;
            } else if (node.right != null) {
                root = node.right;
            } else {
                root = null;
            }
            return;
        }

        if (node.left != null && node.left.returnData() == data) {
            if (node.left.left != null && node.left.right != null) {
                if (node.left.right.left != null) {
                    Node erstattNode = findMin(node.left.right);
                    erstattNode.left.left = node.left.left;
                    erstattNode.left.right = node.left.right;
                    node.left = erstattNode.left;
                    erstattNode.left = null;
                } else {
                    node.left.right.left = node.left.left;
                    node.left = node.left.right;
                }
            } else if (node.left.left != null) {
                node.left = node.left.left;
            } else if (node.left.right != null) {
                node.left = node.left.right;
            } else {
                node.left = null;
            }
            return;
        } else if (node.right != null && node.right.returnData() == data) {
            if (node.right.left != null && node.right.right != null) {
                if (node.right.right.left != null) {
                    Node erstattNode = findMin(node.left.right);
                    erstattNode.left.left = node.right.left;
                    erstattNode.left.right = node.right.right;
                    node.right = erstattNode.left;
                    erstattNode.left = null;
                } else {
                    node.right.left.right = node.right.right;
                    node.right = node.right.left;
                }
            } else if (node.right.left != null) {
                node.right = node.right.left;
            } else if (node.right.right != null) {
                node.right = node.right.right;
            } else {
                node.right = null;
            }
            return;
        }

        if (node.returnData() > data && node.left != null) {
            removeTree(node.left, data);
        }

        if (node.returnData() < data && node.right != null) {
            removeTree(node.right, data);
        }
    }

    int size() {
        return length;
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

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(10);
        binaryTree.insert(46);
        binaryTree.insert(10);
        binaryTree.insert(2);
        System.out.println(binaryTree.toString());
        System.out.println(binaryTree.contains(46));
        binaryTree.remove(46);
        binaryTree.insert(-5);
        binaryTree.insert(-7);
        binaryTree.insert(-3);
        binaryTree.remove(-5);
        System.out.println(binaryTree.toString());
        System.out.println(binaryTree.size());
    }
}