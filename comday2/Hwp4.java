package comday2;
import java.util.Scanner;
/*
4. Write a program to check if a given integer number is

Positive, Negative, or Zero.
 */
public class Hwp4 {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int a=s.nextInt();
        if(a>0){
            System.out.println("Number is positive");
        }
        else if (a==0){
            System.out.println("Number is 0");
        }
        else{
            System.out.println("Number is negative");
        }
    }
}
