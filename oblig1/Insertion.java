class Insertion extends Sorter {

    void sort() {
        for (int i = 1; i <= A.length - 1; i++) {
            int j = i;
            while (j > 0 && gt(A[j - 1], A[j])) {
                swap(j - 1, j);
                j--;
            }
        }

    }

    String algorithmName() {
        return "insertion";
    }
}
