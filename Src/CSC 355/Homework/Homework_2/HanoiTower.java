import java.util.Scanner;

public class HanoiTower {
    public static void hanoi(int n, char source, char auxiliary, char destination) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        hanoi(n - 1, source, destination, auxiliary);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        hanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the number of disks:");
        int numberOfDisks = inp.nextInt();

        hanoi(numberOfDisks, 'A', 'B', 'C');
        
        inp.close();
    }
}
