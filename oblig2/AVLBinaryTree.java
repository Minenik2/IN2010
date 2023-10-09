class AVLBinaryTree {
    Node root;
    int length = 0;
    int height;

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

    // minHeight & height fra github og forelesning
    int minHeight(Node v) {
        if (v == null) {
            return -1;
        }
        return 1 + Math.min(minHeight(v.left), minHeight(v.right));
    }

    int height(Node v) {
        if (v == null) {
            return -1;
        }
        return 1 + Math.max(height(v.left), height(v.right));
    }
    //

    void setHeight(Node v) {
        height = height(v);
    }

    void insert(int x) {
        root = insert(root, x);
    }

    Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else if (data < node.returnData()) {
            node.left = insert(node.left, data);
        } else if (data > node.returnData()) {
            node.right = insert(node.right, data);
        }
        setHeight(node);

        return balance(node);
    }

    Node balance(Node node) {
        if (BalanceFactor(node) < -1) {
            if (BalanceFactor(node.right) > 0) {
                node.right = RightRotate(node.right);
            }
            return LeftRotate(node);
        }
        if (BalanceFactor(node) > 1) {
            if (BalanceFactor(node.left) < 0) {
                node.left = LeftRotate(node.left);
            }
            return RightRotate(node);
        }
        return node;
    }

    int BalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    Node LeftRotate(Node node) {
        Node nyNode = node.right;
        Node nyNodeT = nyNode.left;

        nyNode.left = node;
        node.right = nyNodeT;

        setHeight(node);
        setHeight(nyNode);

        return nyNode;
    }

    Node RightRotate(Node node) {
        Node nyNode = node.left;
        Node nyNodeT = nyNode.right;

        nyNode.right = node;
        node.left = nyNodeT;

        setHeight(node);
        setHeight(nyNode);

        return nyNode;
    }

    /*
     * void searchTree(Node node, int data) {
     * if (node == null) {
     * root = new Node(data);
     * length++;
     * return;
     * }
     * 
     * if (node.returnData() < data) {
     * if (node.right == null) {
     * node.right = new Node(data);
     * length++;
     * return;
     * }
     * searchTree(node.right, data);
     * } else if (node.returnData() > data) {
     * if (node.left == null) {
     * node.left = new Node(data);
     * length++;
     * return;
     * }
     * searchTree(node.left, data);
     * }
     * }
     */

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
        remove(root, data);
    }

    Node remove(Node node, int data) {
        if (node == null) {
            return null;
        }
        if (data < node.returnData()) {
            node.left = remove(node.left, data);
        } else if (data > node.returnData()) {
            node.right = remove(node.right, data);
        } else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        } else {
            Node nyNode = findMin(node.right);
            node.setData(nyNode.returnData());
            node.right = remove(node.right, nyNode.returnData());
        }
        setHeight(node);
        return balance(node);
    }

    Node findMin(Node node) {
        if (node.left == null) {
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
        return height(root);
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
        AVLBinaryTree binaryTree = new AVLBinaryTree();

        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(10);
        System.out.println(binaryTree.toString());
        binaryTree.insert(46);
        binaryTree.insert(10);
        binaryTree.insert(2);
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