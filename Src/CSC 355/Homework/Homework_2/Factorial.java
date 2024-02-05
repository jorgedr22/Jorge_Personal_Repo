import java.util.Scanner;

public class Factorial {
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter a number:");
        int num = inp.nextInt();

        System.out.println("Factorial of " + num + " is: " + factorial(num));

        inp.close();
    }
}
