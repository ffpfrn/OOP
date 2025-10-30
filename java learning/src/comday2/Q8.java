package comday2;

import java.util.Scanner;

public class Q8 {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        char c=s.next().charAt(0);
        if(c>='a'&&c<='z'||c>='A'&&c<='Z' ){
            System.out.println("It is an alphabet");
        } else if (c>='0'&&c<='9') {
            System.out.println("It is a number");
            
        }
        else {
            System.out.println("Special character");
        }
    }
}
