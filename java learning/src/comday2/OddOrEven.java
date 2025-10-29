package comday2;
import java.util.Scanner;
public class OddOrEven {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int a=s.nextInt();
        if (a%2==0){
            System.out.println("Number is even");
        }
        else {
            System.out.println("Number is odd");
        }
    }
}
