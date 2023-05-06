import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BigDataODD {

    private static Scanner scanner = new Scanner(System.in);
    public static Map<String, List<Object>> happyData = new LinkedHashMap<>();

    public static void main(String[] args) {
        fileReader fr = new fileReader("data/happyData2017.csv");
        happyData = fr.storeCsv();
        System.out.println(happyData);

        menuSelection();

    }

    private static int searchString (ArrayList<String> list, String searchTerm) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equalsIgnoreCase(searchTerm)) {
                //returns index of found item
                return i;
            }
        }
        //returns -1 if not found
        return -1;
    }

    private static void menuSelection() {

        boolean countryChoice = false;
        while(!countryChoice) {

            //This crap ain't working, imma play some d2
            System.out.print("Provide a countries name: ");
            String countryName = scanner.nextLine().toUpperCase();
            int result = searchString(new ArrayList<>(happyData.keySet()), countryName);

            if(result != -1) {
                System.out.println("That country exists!");
            } else if(countryName.equals("EXIT")) {
                System.out.println("Goodbye!");

                countryChoice = true;
                divider();
            } else {
                //More code here
            }
        }

    }

    private static void divider() {
        System.out.println("---------------");
    }

}