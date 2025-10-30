package comday2;
import java.sql.SQLOutput;
import java.util.Scanner;
public class InterestCalculator {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Principle amount");
        double p=s.nextFloat();
        System.out.println("Enter rate ");
        float r=s.nextFloat();
        System.out.println("Enter year");
        int years=s.nextInt();
        double i=(p*r*years)/100;
        System.out.println("Interest Earned"+ i);

        if (i>5000){
            System.out.println("High Interest Earned");
        } else if (i>2000 && i<5000) {
            System.out.println("Moderate interest earned");
        }
        else {
            System.out.println("Low interest earned");
        }
        for (int year = 1; year <= years; year++) {
            double interest = (p * r) / 100;
            p=p+interest;
            System.out.printf("After %d year: Interest is %.2f %n", year,interest);
        }

    }
}
