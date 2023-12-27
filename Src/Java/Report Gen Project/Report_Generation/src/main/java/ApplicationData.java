package main.java;

import java.util.ArrayList;

/**
 * The ApplicationData class represents a container for storing pairs of questions and answers
 * in the form of a 2D ArrayList.
 * @author Report Engine Team
 */
public class ApplicationData {
    // Static member representing a 2D ArrayList with pairs of questions and answers
    private ArrayList<ArrayList<String>> applicationDataSet = new ArrayList<>();

    /**
     * Adds a pair of question and answer to the 2D ArrayList.
     *
     * @param question The question to be added.
     * @param answer   The answer to the corresponding question.
     */
    public void addPair(String question, String answer) {
        ArrayList<String> pair = new ArrayList<>();
        pair.add(question);
        pair.add(answer);
        applicationDataSet.add(pair);
    }

    /**
     * Retrieves the 2D ArrayList containing pairs of questions and answers.
     *
     * @return The 2D ArrayList containing pairs of questions and answers.
     */
    public ArrayList<ArrayList<String>> getApplicationDataSet() {
        return applicationDataSet;
    }
}
