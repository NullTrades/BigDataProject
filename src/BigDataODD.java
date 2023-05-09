import java.util.*;

public class BigDataODD {

    private static Scanner scanner = new Scanner(System.in);
    public static Map<String, List<Object>> happyData = new LinkedHashMap<>();

    public static void main(String[] args) {
        fileReader fr = new fileReader("data/happyData2017.csv");
        happyData = fr.storeCsv();

        System.out.println(findHighest("Happiness.Score"));
        System.out.println(findLowest("Happiness.Score"));
        happinessScoreGDPCorrelation();

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

    //A --- method that adds "Happiness.Score" and "Economy..GDP.per.Capita." together
    //It then displays the top 10 nations with the highest combined score, and displays their happiness score and GDP separately
    //This is so that we can identify a correlation between a high GDP per capita and their happiness score
    private static void happinessScoreGDPCorrelation() {

        List<Object> happinessScore = happyData.get("Happiness.Score");
        List<Object> economyGDP = happyData.get("Economy..GDP.per.Capita.");
        List<Object> combinedScores = new ArrayList<>();

        for(int i = 0; i < happinessScore.size(); i++) {
            Object hs = happinessScore.get(i);
            Object gdp = economyGDP.get(i);

            //This is to make sure that any integers within the .csv file are converted into doubles for adding together
            //https://www.programiz.com/java-programming/instanceof
            if(hs instanceof Integer) {
                hs = ((Integer) hs).doubleValue();
            }
            if(gdp instanceof Integer) {
                gdp = ((Integer) gdp).doubleValue();
            }

            double score = (Double) hs;
            double gdpValue = (Double) gdp;

            combinedScores.add(score + gdpValue);
        }

        //Sorting through combinedScores in descending order so that the highest combined scores are displayed first
        Collections.sort(combinedScores, Collections.reverseOrder());

        //Printing out the top 10
        for(int i = 0; i < 10; i++) {
            double score = (Double) happinessScore.get(i);
            double gdpValue = (Double) economyGDP.get(i);

            String country = (String) happyData.get("Country").get(i);
            System.out.println(country + " - GDP: " + gdpValue + ", Happiness Score: " + score);
        }

        System.out.println("---------------");
        System.out.println("This algorithm adds the scores of the happiness score and GDP per capita together");
        System.out.println("It then sorts through the new combined scores in order so that the highest numbers are first");
        System.out.println("The algorithm iterates through the first 10 (top 10) instances within 'combinedScores' and displays the countries name, happiness score, and gdp per capita");

        System.out.println("I was interested in this result as I wanted to know if there was a correlation between top countries happiness scores, their GDP per capita, and location");
        System.out.println("Does a country with a high happiness score necessarily mean that they'd have a high GDP per capita?");
        System.out.println("Do certain regions play a part in a countries happiness score and GDP per capita?");

    }

    private static void averageLifeExpectancy() {

        List<Object> lifeExpectancy = happyData.get("Health..Life.Expectancy.");

        double total = 0;
        for(int i = 0; i < lifeExpectancy.size(); i++) {
            //total += lifeExpectancy.get(i);
        }




        for(int i = 0; i < lifeExpectancy.size(); i++) {
            Object le = lifeExpectancy.get(i);
            double totalLifeExpectancy = (Double) le;

        }


    }

}