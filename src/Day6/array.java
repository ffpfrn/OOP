package Day6;
import java.util.ArrayList;

public class array {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Emma");
        names.add("Michael");
        names.add("Sophia");
        System.out.println("Names List: " + names);
        System.out.println("Element at index 1: " + names.get(1));
        names.remove("Michael");
        System.out.println("After removing 'Michael': " + names);
        if (names.contains("Sophia")) {
            System.out.println("Sophia is present in the list");
        } else {
            System.out.println("Sophia is not present in the list");
        }
        System.out.println("Total names in list: " + names.size());
    }
}