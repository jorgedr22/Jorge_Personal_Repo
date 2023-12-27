import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements AutoCloseable {

    private BufferedReader reader;

    public CSVReader(FileReader fileReader) {
        this.reader = new BufferedReader(fileReader);
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> csvData = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = parseLine(line);
            csvData.add(row);
        }

        return csvData;
    }

    private static String[] parseLine(String line) {
        List<String> columns = new ArrayList<>();
        StringBuilder column = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == ',' && !insideQuotes) {
                columns.add(column.toString());
                column.setLength(0); // Clear the column StringBuilder
            } else if (c == '"') {
                insideQuotes = !insideQuotes;
            } else {
                column.append(c);
            }
        }

        columns.add(column.toString()); // Add the last column

        // Convert the List<String> to a String[]
        String[] rowArray = new String[columns.size()];
        columns.toArray(rowArray);

        return rowArray;
    }

    @Override
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
