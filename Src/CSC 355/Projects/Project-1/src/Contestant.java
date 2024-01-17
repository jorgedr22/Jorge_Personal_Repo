// Contestant.java
public class Contestant implements Comparable<Contestant> {
    private String name;
    private int score;

    public Contestant(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Contestant other) {
        // Compare first by scores and then by names if scores are equal
        int scoreComparison = Integer.compare(other.getScore(), this.getScore());
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        // If scores are equal, compare by names alphabetically
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}
