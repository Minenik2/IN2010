import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Main {
    public static void main(String[] args) throws Exception {
        String filename = "oblig1\\nearly_sorted_1000";
        File file = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(file));
        int[] A = in.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
        in.close();

        SortRunner.runAlgsPart1(A, filename);
        SortRunner.runAlgsPart2(A, filename);
    }
}
