import java.util.Arrays;

class Merge extends Sorter {

    void sort() {
        mergeSort(A);
    }

    int[] merge(int[] A1, int[] A2, int[] A) {
        int i = 0;
        int j = 0;
        while (i < A1.length && j < A2.length) {
            if (leq(A1[i], A2[j])) {
                A[i + j] = A1[i];
                swaps++;
                i++;
            } else {
                A[i + j] = A2[j];
                swaps++;
                j++;
            }
        }
        while (i < A1.length) {
            A[i + j] = A1[i];
            swaps++;
            i++;
        }
        while (j < A2.length) {
            A[i + j] = A2[j];
            swaps++;
            j++;
        }
        return A;
    }

    int[] mergeSort(int[] A) {
        if (A.length <= 1) {
            return A;
        }
        int i = A.length / 2;
        int[] A1 = mergeSort(Arrays.copyOfRange(A, 0, i - 1));
        int[] A2 = mergeSort(Arrays.copyOfRange(A, i, A.length - 1));
        return merge(A1, A2, A);
    }

    String algorithmName() {
        return "merge";
    }
}
