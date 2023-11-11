package oblig4.kattunge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Kattunge {

    static class Node {
        int data;
        Node parent;

        Node(int data) {
            this.data = data;
        }

        void setParent(Node node) {
            parent = node;
        }

        @Override
        public String toString() {
            return data + "";
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Node[] nodeArray = new Node[200];

        File file = new File("E:\\UiO\\semester 7\\in2010\\IN2010\\oblig4\\kattunge\\inputs\\input_2");
        Scanner scanner = new Scanner(file);

        Node cat = new Node(Integer.parseInt(scanner.nextLine()));
        nodeArray[0] = cat;
        Integer freshInt = 1;
        Node newNode; // mor noden som vi setter children i

        while (scanner.hasNext()) {
            String nodes = scanner.nextLine();
            if (nodes.equals("-1")) {
                break;
            } else {
                String[] nodesString = nodes.split(" ");
                // setter mor noden
                newNode = new Node(Integer.parseInt(nodesString[0]));
                // hvis mor noden fins i array erstatter
                for (int i = 0; i < nodeArray.length; i++) {
                    if (nodeArray[i] != null && nodeArray[i].data == Integer.parseInt(nodesString[0])) {
                        newNode = nodeArray[i];
                        System.out.println("mor noden " + nodeArray[i]);
                    }
                }
                for (int j = 1; j < nodesString.length; j++) {
                    boolean found = false;
                    // ser om noden fins fra før og connecter den med newNode
                    for (int k = 0; k < nodeArray.length; k++) {
                        if (nodeArray[k] != null && nodeArray[k].data == Integer.parseInt(nodesString[j])) {
                            System.out.println("!!setter " + nodeArray[k] + " inn i " + newNode);
                            nodeArray[k].setParent(newNode);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        // setter barna til mor noden
                        System.out.println("setter " + nodesString[j] + " inn i " + newNode);
                        nodeArray[freshInt] = new Node(Integer.parseInt(nodesString[j]));
                        nodeArray[freshInt].setParent(newNode);
                        freshInt++;
                    }
                }
                nodeArray[freshInt] = newNode;
                freshInt++;
            }
        }

        /*
         * for å printe ut arrayen
         * for (int i = 0; i < nodeArray.length; i++) {
         * System.out.println(nodeArray[i]);
         * }
         */

        Node catEscape = cat;
        System.out.println(catEscape);
        while (catEscape.parent != null) {
            System.out.println(catEscape.parent);
            catEscape = catEscape.parent;
        }

        scanner.close();
    }
}
