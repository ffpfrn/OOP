package Day6;
import java.util.HashSet;

public class hashset {
    public static void main(String[] args) {
        HashSet<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Apple");
        System.out.println("Fruits Set: " + fruits);
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);
        if (fruits.contains("Mango")) {
            System.out.println("Mango is present");
        } else {
            System.out.println("Mango is not present");
        }
        System.out.println("Total fruits in set: " + fruits.size());
    }
}
