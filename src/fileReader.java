// Import of various libraries for file reading and data management

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class fileReader {

    // Hashmap, in which the keys will be the csv columns and the values associated will be the data in the columns
    public static Map<String, List<Object>> animalMovData = new LinkedHashMap<>();

    private static String filePath;

    public fileReader(String filePath) {
        this.filePath = filePath;
    }
    public void storeCsv() {

        // using buffered reader to read the csv file
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {

            // Create a list of the columns in the csv file, and store them in the hashmap using a foreach loop
            String[] columns = br.readLine().replace("\"", "").split(",");


            // Place all the columns in the hashmap as keys
            for (String column : columns) {
                animalMovData.put(column, new ArrayList<>());
            }

            // While loop to read the csv file line by line
            String line;
            while ((line = br.readLine()) != null) {

                // Split at the commas, and store the values in an array
                String[] values = line.split(",");

                // Handling empty cells
                for (int i = 0; i < values.length; i++) {
                    if (values[i].matches("^\\s*$")) {
                        values[i] = "NA";
                    }
                }

                // For loop to iterate through the columns, and store the values in the hashmap
                for (int i = 0; i < columns.length; i++) {
                    List<Object> columnValues = animalMovData.get(columns[i]);

                    // Basic value cleaning
                    String value = values[i].strip().toLowerCase();


                    // Converting the values to the correct data type
                    if (value.matches("^[0-9\\-\\.]+$")) {
                        if (value.contains(".")) {
                            // Big decimal needed for precision
                            columnValues.add(new BigDecimal(value));
                        } else if (value.length() <= 10) {
                            // Convert to integer if the value is less than 10 digits
                            columnValues.add(Integer.parseInt(value));
                        } else {
                            // Otherwise convert to long
                            columnValues.add(Long.parseLong(value));
                        }
                    } else if (value.equals("true") || value.equals("false")) {
                        // Converting to boolean if the string value is true or false
                        columnValues.add(Boolean.parseBoolean(value));
                    } else {
                        // String is the default
                        columnValues.add(value);
                    }
                }
            }

            // Catching any errors that may occur (most likely a file not found error)
        } catch (IOException e) {
            System.out.println("Error reading file, please check the file path.");
        }

    }

    public void printCsv() {

        // Get column headers
        String[] columnHeaders = animalMovData.keySet().toArray(new String[0]);

        // Get the maximum number of elements in a column
        int maxNumElements = 0;
        for (List<Object> values : animalMovData.values()) {
            if (values.size() > maxNumElements) {
                maxNumElements = values.size();
            }
        }

        // Print column headers with 35 spaces between each
        for (String columnHeader : columnHeaders) {
            System.out.printf("%-35s", columnHeader);
        }
        System.out.println();

        // Print the table contents, with 35 spaces between each. Empty cells are filled with spaces
        for (int j = 0; j < maxNumElements; j++) {
            for (String columnHeader : columnHeaders) {
                List<Object> values = animalMovData.get(columnHeader);

                if (j < values.size()) {
                    System.out.printf("%-35s", values.get(j));
                } else {
                    System.out.printf("%-35s", "");
                }
            }
            System.out.println();
        }
    }

} // End of Main Class




