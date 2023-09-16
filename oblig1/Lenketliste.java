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
    }

    @Override
    public String toString() {
        String returnString = "senpai: ";
        Node currentNode = head;
        for (int i = 0; i < length; i++) {
            returnString += currentNode.data + ", ";
            currentNode = currentNode.next;
        }
        return returnString;
    }

    void push_back(String streng) {
        /// ##System.out.println("lengden er: " + length);
        getReturn(length - 1 + "").next = new Node(Integer.parseInt(streng));
        length++;
    }

    void push_front(String streng) {
        // kan bruke try catch for å skrive ut feilmelding om brukeren ikke skriver et
        // tall
        Node newNode = new Node(Integer.parseInt(streng));
        newNode.next = head;
        head = newNode;
        /// ##System.out.println("hihihihi " + head.data);
        length++;
    }

    void push_middle(String integer) {
        Node nodeA = new Node(Integer.parseInt(integer));
        System.out.println("arraynumber: " + ((Math.floor((length + 1) / 2))) + " Total: " + (length + 1) + " ");
        Node nodeEBehind = getReturn((int) (Math.floor((length + 1) / 2)) - 1 + "");

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

    void get(String integer) {
        // printer ut elemtet som spurt av obligen MEN JEG RESPECTFULLY OG PERSONALLY
        // hadde printet ut alt i main metoden
        System.out.println(getReturn(integer).data);
    }

    // lager denne metoden fordi jeg bruker den i andre metoder også, men har et
    // annet navn fordi den ikke printer ut men istenfor returnerer noden
    Node getReturn(String integer) {
        Node currentNode = head;
        for (int i = 0; i < Integer.parseInt(integer); i++) {
            currentNode = currentNode.next;
        }
        // enten er vi out of bounds of får en errror som betyr at brukeren vår på lese
        // dokumentasjonen litt mer
        // eller så funker alt det skal og vi er på den noden som er i indexen brukeren
        // valgte
        return currentNode;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:\\UiO\\semester 7\\in2010\\IN2010\\oblig1\\input_100");
        Scanner scan = new Scanner(file);

        // String node1 = "1";
        Lenketliste nodeList = new Lenketliste();
        // nodeList.push_front(node1);

        /// ##System.out.println("numberu uno");

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
                nodeList.get(scan.next());
            }
        }
    }
}