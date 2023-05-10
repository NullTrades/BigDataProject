import java.util.*;

public class BigDataODD {

    private static Scanner scanner = new Scanner(System.in);
    public static Map<String, List<Object>> happyData = new LinkedHashMap<>();

    public static void main(String[] args) {
        fileReader fr = new fileReader("data/happyData2017.csv");
        happyData = fr.storeCsv();

        //Dilshaan put this in the menu pls, ty
        System.out.println(findHighest("Happiness.Score"));
        System.out.println(findLowest("Happiness.Score"));

        //Running the menu method
        menuSelection();

        //Closing the scanner
        scanner.close();

    }

    //Code by Daniel
    private static void menuSelection() {

        boolean selectionChoice = false;
        while(!selectionChoice) {

            divider();
            System.out.println("Happy Data 2017");
            divider();

            String input = "";

            while(!input.equals("EXIT")) {

                System.out.println("Choose Your Option: " +
                        "\n\t ABOUT - What our project is about" +
                        "\n\t PARAMETERS - An explanation of the parameters used within the csv file" +
                        "\n\t MIN - Finds the minimum happiness score" +
                        "\n\t MAX - Finds the maximum happiness score" +
                        "\n\t AVERAGE - Finds the average happiness score" +
                        "\n\t DILSHAAN3 - Sample" +
                        "\n\t FREEDOM AND TRUST - Analyzes the top and bottom 10 nations freedom and trust parameters" +
                        "\n\t DANIEL3 - Sample" +
                        "\n\t OSY1 - Sample" +
                        "\n\t OSY1 - Sample" +
                        "\n\t OSY3 - Sample" +
                        "\n\t EXIT - Terminates the program");

                divider();

                System.out.print("Your Choice: ");

                input = scanner.nextLine().toUpperCase();

                if(input.equals("ABOUT")) {
                    about();
                } else if(input.equals("PARAMETERS")) {
                    parameters();
                } else if(input.equals("MIN")) {
                    //findLowest();
                } else if(input.equals("MAX")) {
                    //findHighest();
                } else if(input.equals("AVERAGE")) {
                    averageHappinessScore();
                } else if(input.equals("DILSHAAN3")) {
                    //Method here
                } else if(input.equals("FREEDOM AND TRUST")) {
                    freedomGovTrustCorrelation();
                } else if(input.equals("DANIEL3")) {
                    //Method here
                } else if(input.equals("OSY1")) {
                    //Method here
                } else if(input.equals("OSY2")) {
                    //Method here
                } else if(input.equals("OSY3")) {
                    //Method here
                } else {
                    if(!input.equals("EXIT")) {
                        System.out.println("That is an invalid input. Please try again!");
                    }
                }

            } //End of while loop - main method

            System.out.println("Thank you for running the program!");

        }

    }

    //WORK IN PROGRESS - RELATES TO THE 'findCountryData()' METHOD
    //Code by Mr. Artym
    private static int searchString (List<Object> list, String searchTerm) {
        for(int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if(obj instanceof String && ((String) obj).equalsIgnoreCase(searchTerm)) {
                //returns index of found item
                return i;
            }
        }
        //returns -1 if not found
        return -1;
    }


    //WORK IN PROGRESS - I MIGHT JUST DO ANOTHER METHOD IF I CAN'T SOLVE THIS OR GIVE THIS METHOD TO OSY CUZ HE NEEDS MORE ALGORITHMS
    //IPO
    //User input to ask for a country name
    //Prints out country, along with all other statistics in a cohesive and readable format
    private static void findCountryData() {
        List<Object> countryCol = happyData.get("Country");
        List<Object> happyRankCol = happyData.get("Happiness.Rank");
        List<Object> happyScoreCol = happyData.get("Happiness.Score");
        List<Object> whiskerHighCol = happyData.get("Whisker.high");
        List<Object> whiskerLowCol = happyData.get("Whisker.low");
        List<Object> economyCol = happyData.get("Economy..GDP.per.Capita.");
        List<Object> familyCol = happyData.get("Family");
        List<Object> healthCol = happyData.get("Health..Life.Expectancy.");
        List<Object> freedomCol = happyData.get("Freedom");
        List<Object> generosityCol = happyData.get("Generosity");
        List<Object> govTrustCol = happyData.get("Trust..Government.Corrpution.");
        List<Object> dystopiaCol = happyData.get("Dystopia.Residual");

        boolean countryChoice = false;
        while(!countryChoice) {

            System.out.print("Select a Country: ");
            String countryName = scanner.nextLine().toUpperCase();

            int result = searchString(countryCol, countryName);
            if(result != -1) {
                System.out.println("Sample: " + countryName);
            }

        }

    }

    //Code by Dilshaan
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

    //Code by Dilshaan
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

    //Code by Daniel
    private static void freedomGovTrustCorrelation() {
        List<Object> freedomScore = happyData.get("Freedom");
        List<Object> govTrust = happyData.get("Trust..Government.Corruption.");
        List<Object> happinessScore = happyData.get("Happiness.Score");
        List<Object> combinedScores = new ArrayList<>();

        for(int i = 0; i < freedomScore.size(); i++) {
            Object fs = freedomScore.get(i);
            Object gt = govTrust.get(i);

            //This is to make sure that any integers within the .csv file are converted into doubles for adding together
            //https://www.programiz.com/java-programming/instanceof
            if(fs instanceof Integer) {
                fs = ((Integer) fs).doubleValue();
            }
            if(gt instanceof Integer) {
                gt = ((Integer) gt).doubleValue();
            }

            double score = (Double) fs;
            double gdpValue = (Double) gt;

            combinedScores.add(score + gdpValue);
        }

        //Sorting through combinedScores in descending order so that the highest combined scores are displayed first
        Collections.sort(combinedScores, Collections.reverseOrder());

        System.out.println("Top 10 Nations:");
        for(int i = 0; i < 10; i++) {
            double fsValue = (Double) freedomScore.get(i);
            double gtValue = (Double) govTrust.get(i);
            double happyValue = (Double) happinessScore.get(i);

            String country = (String) happyData.get("Country").get(i);
            System.out.println(country + " - Freedom: " + fsValue + ", Government Trust: " + gtValue + ", Happiness Score: " + happyValue);
        }

        divider();

        System.out.println("Bottom 10 Nations:");
        for(int i = combinedScores.size() - 10; i < combinedScores.size(); i++) {
            double fsValue = (Double) freedomScore.get(i);
            double gtValue = (Double) govTrust.get(i);
            double happyValue = (Double) happinessScore.get(i);

            String country = (String) happyData.get("Country").get(i);
            System.out.println(country + " - Freedom: " + fsValue + ", Government Trust: " + gtValue + ", Happiness Score: " + happyValue);
        }

        divider();

        System.out.println("This algorithm adds the scores of freedom and trust in government (in relation to their happiness score) together");
        System.out.println("It then sorts through the new combined scores in order so that the highest numbers are first");
        System.out.println("The algorithm iterates through the first and last 10 (top/bottom 10) instances within 'combinedScores' and displays the countries name, freedom, and trust in government");

        divider();

        System.out.println("I was interested in this result as I wanted to visualize the significance of how freedom and trust in government contributed to the total happiness score of a country");
        System.out.println("By looking at the top and bottom 10 nations, we can witness how drastic of a change there is in happiness score, and how freedom and government trust contributed to that score");

        divider();
    }

    //Code by Daniel
    private static void averageHappinessScore() {
        List<Object> happinessScore = happyData.get("Happiness.Score");

        double total = 0;
        int count = 0;

        for (int i = 0; i < happinessScore.size(); i++) {
            Object value = happinessScore.get(i);

            if (value instanceof Double) {
                total += (double) value;
                count++;
            } else if (value instanceof Integer) {
                total += (double) ((int) value);
                count++;
            }
        }

        double average = total / count;

        System.out.println("Average Happiness Score: " + rn(average, 3));
        System.out.println("Real Average ahem Osy ahem: " + average);

        divider();

        System.out.println("This algorithm finds the average happiness score by adding all happiness scores together, and dividing by the total amount of countries");
        System.out.println("It makes sure that the happiness scores within the csv file are doubles, and not integers so that no errors pop up when collecting the total");

        divider();

        System.out.println("I was intrigued by this result because...");

        divider();
    }

    //Code by Osy
    private static double rn(double value, int decimalPlace) {
        // create a rounding method

        double a = (Math.pow(value,10.0* decimalPlace))/10.0*decimalPlace;
        return a;
    }

    //Code by Daniel - I love this
    private static void divider() {
        System.out.println("---------------");
    }

    //Code by Daniel
    private static void about() {
        divider();
        System.out.println("This program analyzes the happiness of many nations in 2017!");
        divider();
    }

    //Code by Daniel
    private static void parameters() {
        divider();
        System.out.println("Country - The name of a country");
        System.out.println("Happiness Rank - Rank of the country based on happiness score");
        System.out.println("Happiness Score - A metric measured by asking people how happy they were on a scale from 0 (lowest) to 10 (highest)");
        System.out.println("Whisker High - Lower confidence interval of the happiness score");
        System.out.println("Whisker Low - Upper confidence interval of the happiness score");
        System.out.println("Economy (GDP per Capita) - The extent to which GDP contributes to the calculation of the happiness score");
        System.out.println("Family - The extent to which family contributes to the calculation happiness score");
        System.out.println("Health (Life Expectancy) - The extent to which life expectancy contributes to the calculation happiness score");
        System.out.println("Freedom - The extent to which freedom contributes to the calculation happiness score");
        System.out.println("Trust (Government Corruption) - The extent to which perception of corruption contributes to the calculation happiness score");
        System.out.println("Generosity - The extent to which generosity contributes to the calculation happiness score");
        System.out.println("Dystopia Residual - The extent to which dystopia residual contributes to the calculation happiness score");
        divider();
    }

}