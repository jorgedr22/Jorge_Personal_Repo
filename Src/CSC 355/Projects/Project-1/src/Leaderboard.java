import java.util.Arrays;

// Leaderboard.java
public class Leaderboard {
    private Contestant[] topScores;
    private int currentIndex;

    public Leaderboard(int M) {
        topScores = new Contestant[M];
        currentIndex = 0;
    }

    public void add(Contestant contestant) {
        if (currentIndex < topScores.length) {
            topScores[currentIndex++] = contestant;
        } else {
            // Check if the contestant has a higher score than the highest score on the leaderboard
            int highestScoreIndex = findHighestScoreIndex();
            if (contestant.compareTo(topScores[highestScoreIndex]) > 0) {
                topScores[highestScoreIndex] = contestant;
            }
        }
    }    

    private int findHighestScoreIndex() {
        int highestScoreIndex = 0;
        for (int i = 1; i < topScores.length; i++) {
            if (topScores[i].compareTo(topScores[highestScoreIndex]) > 0) {
                highestScoreIndex = i;
            }
        }
        return highestScoreIndex;
    }
    

    public Contestant[] finalBoard() {
        // Sort the array before returning
        Arrays.sort(topScores);
        return Arrays.copyOf(topScores, topScores.length);
    }
}
