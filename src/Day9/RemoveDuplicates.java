package Day9;

import java.util.Arrays;

    public class RemoveDuplicates {
        public static int[] removeDuplicates(int[] arr) {
            Arrays.sort(arr);
            int n = arr.length;
            int j = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] != arr[j]) {
                    j++;
                    arr[j] = arr[i];
                }
            }
            return Arrays.copyOf(arr, j + 1);
        }

        public static void main(String[] args) {
            int[] arr = {4, 2, 9, 2, 4, 1, 9, 5};
            int[] result = removeDuplicates(arr);
            System.out.println(Arrays.toString(result));
        }
    }


