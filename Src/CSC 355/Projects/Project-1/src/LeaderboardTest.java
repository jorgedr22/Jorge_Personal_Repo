import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LeaderboardTest {
    public static void main(String[] args) {
        // Adjust the file path accordingly
        String filePath = "/Users/jorgedelriocuriel/Desktop/Project-1/src/names.txt";
        //String filePath = "/Users/jorgedelriocuriel/Desktop/Project-1/src/temp.txt";

        // Ask for the size of the leaderboard
        Scanner LeaderboardSize = new Scanner(System.in);
        System.out.println("Enter the size of the final leaderboard to be tested:");

        int size = LeaderboardSize.nextInt();
        LeaderboardSize.close();

        // Initialize the leaderboard with size M
        Leaderboard leaderboard = new Leaderboard(size);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each line in the file contains name and score separated by a comma
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim(); // Remove leading/trailing whitespaces
                    int score = Integer.parseInt(parts[1].trim());

                    // Create a Contestant and add to the leaderboard
                    Contestant contestant = new Contestant(name, score);
                    leaderboard.add(contestant);
                }       
            }
        } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
        }

        // Get the final leaderboard and print the results
        Contestant[] finalBoard = leaderboard.finalBoard();
        System.out.println("Final Leaderboard:");
        for (Contestant contestant : finalBoard) {
            System.out.println(contestant);
        }
    }
}
