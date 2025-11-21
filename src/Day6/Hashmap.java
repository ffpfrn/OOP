package Day6;

import java.util.HashMap;

public class Hashmap{
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        students.put(101, "John");
        students.put(102, "Emma");
        students.put(103, "Ryan");
        students.put(104, "Sophia");
        System.out.println("Student Records: " + students);
        System.out.println("Student with Roll No 102: " + students.get(102));
        students.remove(103);
        System.out.println("After removing roll no 103: " + students);
        if (students.containsKey(104)) {
            System.out.println("Roll No 104 exists in the records.");
        }
        if (students.containsValue("Emma")) {
            System.out.println("Emma is in the student list.");
        }
        System.out.println("Total student entries: " + students.size());
    }
}
