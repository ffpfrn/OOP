package Day9;
import java.util.Arrays;

    public class ArrayRotation {

        public static void leftRotateBruteForce(int[] arr, int k) {
            int n = arr.length;
            k = k % n;
            for (int i = 0; i < k; i++) {
                int temp = arr[0];
                for (int j = 0; j < n - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[n - 1] = temp;
            }
        }

        public static void leftRotateRecursive(int[] arr, int k) {
            int n = arr.length;
            k = k % n;
            if (k > 0) {
                rotateByOne(arr);
                leftRotateRecursive(arr, k - 1);
            }
        }

        private static void rotateByOne(int[] arr) {
            int n = arr.length;
            int temp = arr[0];
            for (int i = 0; i < n - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[n - 1] = temp;
        }

        public static void main(String[] args) {
            int[] arr1 = {1, 2, 3, 4, 5};
            int k = 2;

            System.out.println("Original Array: " + Arrays.toString(arr1));
            leftRotateBruteForce(arr1, k);
            System.out.println("After Brute Force Left Rotation by " + k + ": " + Arrays.toString(arr1));

            int[] arr2 = {1, 2, 3, 4, 5};
            leftRotateRecursive(arr2, k);
            System.out.println("After Recursive Left Rotation by " + k + ": " + Arrays.toString(arr2));
        }
    }


