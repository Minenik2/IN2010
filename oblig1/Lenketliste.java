import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Lenketliste {
    Node head;
    int length = 0;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
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

    // denne toString metoden er ikke spurt av obligen men er her for å debugge
    // lettere
    @Override
    public String toString() {
        String returnString = "Noder: ";
        Node currentNode = head;
        for (int i = 0; i < length; i++) {
            returnString += currentNode.data + ", ";
            currentNode = currentNode.next;
        }
        return returnString;
    }

    void push_back(String streng) {
        // sjekker om det er noe i lenketlisten hvis brukeren bruker push back aller
        // først
        if (head == null) {
            head = new Node(Integer.parseInt(streng));
        } else {
            getReturn(length - 1).next = new Node(Integer.parseInt(streng));
        }
        length++;
    }

    void push_front(String streng) {
        // kan bruke try catch for å skrive ut feilmelding om brukeren ikke skriver et
        // tall
        Node newNode = new Node(Integer.parseInt(streng));
        newNode.next = head;
        head = newNode;
        length++;
    }

    void push_middle(String integer) {
        Node nodeA = new Node(Integer.parseInt(integer));
        System.out.println("arraynumber: " + ((Math.floor((length + 1) / 2))) + " Total: " + (length + 1));
        Node nodeEBehind = getReturn((int) (Math.floor((length + 1) / 2)) - 1);

        if (nodeEBehind == null) {
            head = nodeA; // hvis det er ingenting i lenketlisten fra før av og middle push blir kalt
        } else if (nodeEBehind.next == null) {
            nodeEBehind.next = nodeA; // hvis det er 1 eller 2 ellementer i listen
        } else { // alt annet
            nodeA.next = nodeEBehind.next;
            nodeEBehind.next = nodeA;
        }

        length++;
    }

    void get(int integer) {
        // printer ut elemtet som spurt av obligen MEN JEG RESPECTFULLY OG PERSONALLY
        // hadde printet ut alt i main metoden
        System.out.println(getReturn(integer).data);
    }

    // lager denne metoden fordi jeg bruker den i andre metoder også, men har et
    // annet navn fordi den ikke printer ut men istenfor returnerer noden
    Node getReturn(int integer) {
        Node currentNode = head;
        for (int i = 0; i < integer; i++) {
            currentNode = currentNode.next;
        }
        // enten er vi out of bounds of får en errror som betyr at brukeren vår må lese
        // dokumentasjonen litt mer
        // eller så funker alt det skal og vi er på den noden som er i indexen brukeren
        // valgte
        return currentNode;
    }

    // metode for sorteringsoppgaven insertionsort
    Lenketliste insertionSort(Lenketliste list) {
        for (int i = 1; i <= list.returnLength() - 1; i++) {
            int j = i;
            while (j > 0 && list.getReturn(j - 1).returnData() > list.getReturn(j).returnData()) {
                Node left = list.getReturn(j - 1);
                int leftData = left.returnData();
                Node right = list.getReturn(j);
                left.setData(right.returnData());
                right.setData(leftData);
                j--;
            }
        }
        return list;
    }

    // metode for sorteringsoppgaven merge sort
    Lenketliste merge(Lenketliste list1, Lenketliste list2, Lenketliste finalList) {
        int i = 0;
        int j = 0;

        while (i < list1.returnLength() && j < list2.returnLength()) {
            if (list1.getReturn(i).returnData() <= list2.getReturn(j).returnData()) {
                finalList.getReturn(i + j).setData(list1.getReturn(i).returnData());
                i += 1;
            } else {
                finalList.getReturn(i + j).setData(list2.getReturn(j).returnData());
                j += 1;
            }
        }
        while (i < list1.returnLength()) {
            finalList.getReturn(i + j).setData(list1.getReturn(i).returnData());
            i += 1;
        }
        while (j < list2.returnLength()) {
            finalList.getReturn(i + j).setData(list2.getReturn(j).returnData());
            j += 1;
        }
        return finalList;
    }

    Lenketliste mergeSort(Lenketliste nodeList) {
        if (nodeList.returnLength() <= 1) {
            return nodeList;
        }
        Lenketliste nodeListLeft = new Lenketliste();
        Lenketliste nodeListRight = new Lenketliste();
        // den for løkken går gjennom vært element og lager to lister der inneholdet er
        // splittet av den originale listen
        for (int j = 0; j < nodeList.returnLength(); j++) {
            // hvis vi er fra den første noden til den før midten plasser elementene i array
            // til venstre, hvis vi er over midten plasserer vi elemtet til arrayen på høyre
            if (j < ((int) Math.floor((nodeList.returnLength() + 1) / 2))) {
                nodeListLeft.push_back(nodeList.getReturn(j).returnData() + "");
            } else {
                nodeListRight.push_back(nodeList.getReturn(j).returnData() + "");
            }
        }
        nodeListLeft = mergeSort(nodeListLeft);
        nodeListRight = mergeSort(nodeListRight);
        return merge(nodeListLeft, nodeListRight, nodeList);
    }

    // returnerer lengen til array brukes for insertionsortmetoden og merge metoden
    int returnLength() {
        return length;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:\\UiO\\semester 7\\in2010\\IN2010\\oblig1\\input_100");
        Scanner scan = new Scanner(file);

        Lenketliste nodeList = new Lenketliste();

        String msg = scan.next();
        int amount = Integer.parseInt(msg);

        for (int i = 0; i < amount; i++) {
            msg = scan.next();
            System.out.println(i + " " + nodeList.toString() + "\n");

            if (msg.equals("push_back")) {
                nodeList.push_back(scan.next());
            } else if (msg.equals("push_front")) {
                nodeList.push_front(scan.next());
            } else if (msg.equals("push_middle")) {
                nodeList.push_middle(scan.next());
            } else {
                nodeList.get(Integer.parseInt(scan.next()));
            }
        }

        // Sortering for insertionSort, skriver ut på terminal
        System.out.println("Insertionsort:\n" + nodeList.insertionSort(nodeList).toString());

        // sortering for mergesort, skriver ut på terminal
        System.out.println("MergeSort:\n" + nodeList.mergeSort(nodeList).toString());

        scan.close();
    }
}