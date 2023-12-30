package utils;

public class ReverseList {

    static int M = 3;
    static int N = 3;

    // A utility function
    // for swapping two elements.
    private static int[][] swap(int[][] arr, int start,
                                int i, int end, int j) {

        int temp = arr[start][i];
        arr[start][i] = arr[end][j];
        arr[end][j] = temp;
        return arr;
    }

    // Print the arr[][]
    static void printMatrix(int arr[][]) {

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to reverse
    // the given 2D arr[][]
    public static void reverseColumnArray(int arr[][]) {

        for (int i = 0; i < N; i++) {
            // Initialise start and end index
            int start = 0;
            int end = M - 1;

            // Till start < end, swap the
            // element at start and end index
            while (start < end) {

                // Swap the element
                arr = swap(arr, start, i, end, i);

                // Increment start and decrement

                start++;
                end--;
            }
        }

    }
}
