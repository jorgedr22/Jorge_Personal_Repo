package Homework.Homework_1;
import java.util.Scanner;

public class Factorial {

    public static int CalculateFactorial(int x){
        if(x == 0 | x == 1){
            return 1; // 1! = 0! = 1
        }
        else if (x>1){
            return x * CalculateFactorial(x-1); // Calculate the factorial of x
        }
        return -1; // returns -1 for ANY negative integer
    }
    public static void main(String[] args) {
        
        Scanner inp = new Scanner(System.in);

        System.out.println("Enter a number");
        int factorial = inp.nextInt();
        
        System.out.println("The factorial of " + factorial + " is: " + CalculateFactorial(factorial));
        inp.close();
    }
}