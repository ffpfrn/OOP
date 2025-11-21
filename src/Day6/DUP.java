package Day6;

import java.util.*;
public class Dup {
    public static void main(String[] args) {

        int[] numbers = {10, 20, 30, 20, 10, 40, 30};

        // Using HashSet (it does not allow duplicates)
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for (int num : numbers) {
            uniqueNumbers.add(num);
        }

        System.out.println("Original Array: " + Arrays.toString(numbers));
        System.out.println("Array after removing duplicates: " + uniqueNumbers);
    }
}
