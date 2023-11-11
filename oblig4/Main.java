package oblig4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class MyHashSet {
    private final int capacity = 10000; // Choose a suitable capacity based on your constraints
    private List<Set<Integer>> table;

    public MyHashSet() {
        table = new ArrayList<>(Collections.nCopies(capacity, null));
    }

    private int hash(int x) {
        return x % capacity;
    }

    public boolean contains(int x) {
        int index = hash(x);
        Set<Integer> bucket = table.get(index);
        return bucket != null && bucket.contains(x);
    }

    public void insert(int x) {
        int index = hash(x);
        Set<Integer> bucket = table.get(index);
        if (bucket == null) {
            bucket = new HashSet<>();
            table.set(index, bucket);
        }
        bucket.add(x);
    }

    public void remove(int x) {
        int index = hash(x);
        Set<Integer> bucket = table.get(index);
        if (bucket != null) {
            bucket.remove(x);
        }
    }

    public int size() {
        int count = 0;
        for (Set<Integer> bucket : table) {
            if (bucket != null) {
                count += bucket.size();
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:\\UiO\\semester 7\\in2010\\IN2010\\oblig4\\inputs\\input_100");
        Scanner scanner = new Scanner(file);

        int N = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        MyHashSet hashSet = new MyHashSet();

        for (int i = 0; i < N; i++) {
            String[] operation = scanner.nextLine().split(" ");
            if (operation[0].equals("contains")) {
                System.out.println(hashSet.contains(Integer.parseInt(operation[1])));
            } else if (operation[0].equals("insert")) {
                hashSet.insert(Integer.parseInt(operation[1]));
            } else if (operation[0].equals("remove")) {
                hashSet.remove(Integer.parseInt(operation[1]));
            } else if (operation[0].equals("size")) {
                System.out.println(hashSet.size());
            }
        }

        scanner.close();
    }
}