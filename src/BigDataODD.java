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

        System.out.println(findHighest("Happiness.Score"));
        System.out.println(findLowest("Happiness.Score"));

    }


//    private static void menuSelection() {
//
//        boolean countryChoice = false;
//        while(!countryChoice) {
//
//            //This crap ain't working, imma play some d2
//            System.out.print("Provide a countries name: ");
//            String countryName = scanner.nextLine().toUpperCase();
//            int result = searchString(new ArrayList<>(happyData.keySet()), countryName);
//
//            if(result != -1) {
//                System.out.println("That country exists!");
//            } else if(countryName.equals("EXIT")) {
//                System.out.println("Goodbye!");
//
//                countryChoice = true;
//                divider();
//            } else {
//                //More code here
//            }
//        }
//
//    }

    private static double findHighest(String colName) {
        List<Object> colData = happyData.get(colName);
        double highest = 0;

        for (Object data : colData) {
            if ((Double) data > highest) {
                highest = Double.parseDouble(data.toString());
            }
        }

        return highest;
    }

    private static double findLowest(String colName) {
        List<Object> colData = happyData.get(colName);
        double lowest = findHighest(colName);

        for (Object data : colData) {
            if ((Double) data < lowest) {
                lowest = Double.parseDouble(data.toString());
            }
        }

        return lowest;
    }


}