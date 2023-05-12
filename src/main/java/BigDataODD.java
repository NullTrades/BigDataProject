import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;


import java.util.*;


public class BigDataODD {

    private static final Scanner scanner = new Scanner(System.in);
    public static Map<String, List<Object>> happyData = new LinkedHashMap<>();

    // Code By Dilshaan
    public static void main(String[] args) {
        _FileReader fr = new _FileReader("data/happyData2017.csv");
        happyData = fr.storeCsv();

        menuSelection();

        //Closing the scanner
        scanner.close();

    }

    //Code by Daniel/Osy/Dilshaan
    private static void menuSelection() {

        boolean selectionChoice = false;
        while (!selectionChoice) {
            divider();
            System.out.println("Happy Data 2017");
            divider();

            String input = "";
            while (!input.equals("EXIT")) {

                System.out.println("Choose Your Option: " +
                        "\n\t ABOUT - What our project is about" +
                        "\n\t PARAMETERS - An explanation of the parameters used within the csv file" +
                        "\n\t HIGHEST_LOWEST - Ranks the parameters from Highest to Lowest" +
                        "\n\t AVERAGE - Finds the average happiness score" +
                        "\n\t COUNTRY_SEARCH - Prints out all the data associated with the specific country" +
                        "\n\t FREEDOM_TRUST - Analyzes the top and bottom 10 nations freedom and trust parameters" +
                        "\n\t FAMILY_PERCENTAGE - Finds the percentage at which family makes up the top 10 nations happiness scores" +
                        "\n\t ML_Predictions - Predict the overall happiness score of country x based on various values (Supervised Machine Learning Algorithim)" +
                        "\n\t OSY1 - Sample"+
                        "\n\t OSY1 - Sample" +
                        "\n\t OSY3 - Sample" +
                        "\n\t EXIT - Terminates the program");

                divider();

                System.out.print("Your Choice: ");

                input = scanner.nextLine().toUpperCase();
                switch (input) {
                    case "ABOUT":
                        about();
                        break;
                    case "PARAMETERS":
                        parameters();
                        break;
                    case "AVERAGE":
                        averageHappinessScore();
                        break;
                    case "HIGHEST_LOWEST":
                        findLowestHighest();
                        break;
                    case "FREEDOM_TRUST":
                        freedomGovTrustCorrelation();
                        break;
                    case "FAMILY_PERCENTAGE":
                        findFamilyPercentage();
                        break;
                    case "COUNTRY_SEARCH":
                        findCountryData();
                        break;
                    case "ML_PREDICT":
                        ML_Predict();
                        break;
                    case "OSY2":
                        //Method here
                        break;
                    case "OSY3":
                        //Method here
                        break;
                    case "EXIT":
                        System.out.println("Exiting the program...");
                        selectionChoice = true;
                        break;
                    default:
                        divider();
                        System.out.println("That is an invalid input. Please try again!");
                        divider();
                        break;
                }

            } //End of while loop - main method

            System.out.println("Thank you for running the program!");

        }

    }

    //Code by Mr. Artym (General Usage)
    private static int searchString(List<Object> list, String searchTerm) {
        for (int i = 0; i < list.size(); i++) {
            String obj = list.get(i).toString().replace("\"", "");
            if (obj.equalsIgnoreCase(searchTerm)) {
                return i;
            }
        }
        return -1;
    }

    // Dilshaan
    private static void findCountryData() {

        boolean countryChoice = false;
        while (!countryChoice) {

            System.out.print("Select a Country: ");
            // make it so the user input starts with an uppercase letter

            String countryNameRaw = scanner.nextLine().toLowerCase();
            String countryName = countryNameRaw.substring(0, 1).toUpperCase() + countryNameRaw.substring(1);

            int result = searchString(happyData.get("Country"), countryName);
            if (result == -1) {
                System.out.println("Sorry, that country does not exist. Please try again!");
            } else {
                for (String key : happyData.keySet()) {
                    System.out.println(key + ": " + happyData.get(key).get(result));
                }
                countryChoice = true;
            }

        }

        System.out.println("This algorithm is a simple way to search thorough all the countries and print all the data associated with them." +
                "The reason I was interested in making this algorithim was to understand how I could deal with instances in which the user input did not contain" +
                "a country in the CSV file");
        System.out.println("In a real world case, this would be one of the most used functions as it is " +
                "the most direct way to query the data if the user already has a country in mind and doesn't need advanced statistics");

    }


    //Code by Dilshaan
    private static void findLowestHighest() {
        parameters();
        System.out.println("Please enter the parameter you would like to find the sorted values of: ");
        String colName = scanner.nextLine();
        List<Object> colData = happyData.get(colName);

        for (int i = 0; i < colData.size() - 1; i++) {
            for (int j = 0; j < colData.size() - i - 1; j++) {
                if (((Number) colData.get(j)).doubleValue() < ((Number) colData.get(j + 1)).doubleValue()) {
                    // swap the adjacent elements
                    double temp = ((Number) colData.get(j)).doubleValue();
                    colData.set(j, colData.get(j + 1));
                    colData.set(j + 1, temp);
                }
            }
        }
        divider();
        System.out.println(colName + " sorted in descending order: ");
        divider();
        System.out.println(colData);
        for (Object num : colData) {
            System.out.println(happyData.get("Country").get(colData.indexOf(num)) + ": " + num);
        }

        System.out.println("\nI found this algorithm interesting to make because it used bubble sort, a process which " +
                "involves repeatedly changing the position of adjacent elements in a data structure based on a given condition, which in this case was size of the number." +
                "This algorithm dynamic, making it applicable to any parameter in the data");
        System.out.println("From this data, it very easy to compare one country against another. In this direct comparison organizations which" +
                "help countries on the lower end of the spectrum can compare how higher ranking countries are finding success" +
                "in a given parameter and try to implement it into the countries they are trying to aid");
        divider();
    }

    //Code by Daniel
    private static void freedomGovTrustCorrelation() {
        List<Object> combinedScores = new ArrayList<>();

        for (int i = 0; i < happyData.get("Freedom").size(); i++) {
            Object fs = happyData.get("Freedom").get(i);
            Object gt = happyData.get("Trust").get(i);

            //https://www.programiz.com/java-programming/instanceof
            if (fs instanceof Integer) {
                fs = ((Integer) fs).doubleValue();
            }
            if (gt instanceof Integer) {
                gt = ((Integer) gt).doubleValue();
            }

            double score = (Double) fs;
            double gdpValue = (Double) gt;

            combinedScores.add(score + gdpValue);
        }

        //Sorting through combinedScores in descending order so that the highest combined scores are displayed first
        combinedScores.sort(Collections.reverseOrder());

        System.out.println("Top 10 Nations:");
        for (int i = 0; i < 10; i++) {
            double fsValue = (Double) happyData.get("Freedom").get(i);
            double gtValue = (Double) happyData.get("Trust").get(i);
            double happyValue = (Double) happyData.get("Happiness Score").get(i);

            String country = (String) happyData.get("Country").get(i);
            System.out.println(country + " - Freedom: " + fsValue + ", Government Trust: " + gtValue + ", Happiness Score: " + happyValue);
        }

        divider();

        System.out.println("Bottom 10 Nations:");
        for (int i = combinedScores.size() - 10; i < combinedScores.size(); i++) {
            double fsValue = (Double) happyData.get("Freedom").get(i);
            double gtValue = (Double) happyData.get("Trust").get(i);
            double happyValue = (Double) happyData.get("Happiness Score").get(i);

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
    private static void findFamilyPercentage() {

        List<Object> familyCol = happyData.get("Family");
        List<Object> scoreCol = happyData.get("Happiness Score");

        System.out.println("Top 10 Nations:");
        for (int i = 0; i < 10; i++) {
            Double fcValue = Double.parseDouble(familyCol.get(i).toString());
            Double scValue = Double.parseDouble(scoreCol.get(i).toString());
            double percentage = (fcValue / scValue) * 100;

            String country = (String) happyData.get("Country").get(i);
            System.out.println(country + " - Percentage: " + percentage + "%");
        }

        divider();

        System.out.println("Bottom 10 Nations:");
        for (int i = 0; i < familyCol.size(); i++) {
            if (i >= (familyCol.size() - 10)) {
                //Part of this code was thanks to the valiant efforts of an unknown soldier named Quinn, who skipped Chemistry 20 to heed our call
                Double fcValue = Double.parseDouble(familyCol.get(i).toString());
                Double scValue = Double.parseDouble(scoreCol.get(i).toString());
                double percentage = (fcValue / scValue) * 100;

                String country = (String) happyData.get("Country").get(i);
                System.out.println(country + " - Percentage: " + percentage + "%");
            }
        }

        divider();

        System.out.println("This algorithm finds the percentage at which family contributes to the happiness scores of the top and bottom 10 countries");
        System.out.println("It stores the appropriate data in their own list from the hashmap before retrieving each nations individual happiness scores and family scores");
        System.out.println("Finally, after making sure each value is a double, we calculate the average percentage and print it out");

        divider();

        System.out.println("What can be inferred from these results is that despite there being a drastic difference of happiness between the top and bottom 10 nations, family in the majority of these countries make up a similar percentage of total happiness");
        System.out.println("Furthermore, Some countries like Rwanda, despite having a lower happiness score compared to Norway, has a bigger percentage of their happiness score made up from family");
        System.out.println("This can suggest that people from nations with a lower happiness find family to be more important, whether it be due to cultural or religious reasons");

        divider();
    }

    //Code by Daniel/Dilshaan
    private static void averageHappinessScore() {
        List<Object> happinessScore = happyData.get("Happiness Score");

        double total = 0;
        int count = 0;

        for (Object value : happinessScore) {
            total += (double) value;
            count++;
        }

        double average = total / count;

        System.out.println("Average Happiness Score: " + average);

        divider();

        System.out.println("This algorithm finds the average happiness score by adding all happiness scores together, and dividing by the total amount of countries");
        System.out.println("It makes sure that the happiness scores within the csv file are doubles, and not integers so that no errors pop up when collecting the total");

        divider();

        System.out.println("I was intrigued by this result because with all this collected data, I did want to find out what the average happiness score was");
        System.out.println("By doing so, I'm able to compare if a certain countries happiness score is above or below the average, along with potentially why");

        divider();
    }

    //Code by Osy
    private static double rn(double value, int decimalPlace) {
        // create a rounding method

        double a = (Math.pow(value, 10.0 * decimalPlace)) / 10.0 * decimalPlace;
        return a;
    }

    // Dilshaan
    // http://home.apache.org/~luc/commons-math-3.6-RC2-site/apidocs/org/apache/commons/math3/stat/regression/OLSMultipleLinearRegression.html
    // ^ This helped A LOT as I have only done linear regression in python before
    public static void ML_Predict(){
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        double[] y = new double[happyData.get("Happiness Score").size()];
        double[][] x = new double[happyData.get("Happiness Score").size()][7];
        for (int i = 0; i < happyData.get("Happiness Score").size(); i++) {

            y[i] = Double.parseDouble(happyData.get("Happiness Score").get(i).toString());
            x[i][0] = Double.parseDouble(happyData.get("Freedom").get(i).toString());
            x[i][1] = Double.parseDouble(happyData.get("Trust").get(i).toString());
            x[i][2] = Double.parseDouble(happyData.get("Family").get(i).toString());
            x[i][3] = Double.parseDouble(happyData.get("Generosity").get(i).toString());
            x[i][4] = Double.parseDouble(happyData.get("Life Expectancy").get(i).toString());
            x[i][5] = Double.parseDouble(happyData.get("Economy").get(i).toString());
            x[i][6] = Double.parseDouble(happyData.get("Dystopia Residual").get(i).toString());
        }
        regression.newSampleData(y, x);
        // predict the happiness score of a country based on the 7 factors
        double[] beta = regression.estimateRegressionParameters();

        double y_predict = 0;

        System.out.print("Enter the values of the 7 factors in order with spaces in between (Freedom, Trust, Generosity," +
                "Life Expectancy, Economy, Dystopia Residual): ");
        String[] x_input = scanner.nextLine().split(" ");

        if (x_input.length != 7) {
            System.out.println("Invalid input, Please try again");
            ML_Predict();
            divider();
        }

        for (int i = 0; i < x_input.length; i++) {
            y_predict += Double.parseDouble(x_input[i]) * beta[i];
        }

        System.out.println("Predicted Happiness Score: " + y_predict);
        divider();
        System.out.println("This algorithm uses the OLSMultipleLinearRegression class from the Apache Commons Math library to predict the happiness score of a country based on the 7 factors. " +
                "It was interesting to make because I have never has an opportunity to use linear regression in Java before and gave me a higher understanding" +
                "of the actual algorithm involved ");
        System.out.println("This algorithm is extremely useful because it can predict the overall happiness values of countries which may not be specified in our dataset. It can also" +
                "predict values for countries in the future which have not been created yet");
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

    //Code by Dilshaan
    private static void parameters() {
        divider();
        System.out.println("Country - The name of a country");
        System.out.println("Happiness Rank - Rank of the country based on happiness score");
        System.out.println("Happiness Score - A metric measured by asking people how happy they were on a scale from 0 (lowest) to 10 (highest)");
        System.out.println("Whisker High - Lower confidence interval of the happiness score");
        System.out.println("Whisker Low - Upper confidence interval of the happiness score");
        System.out.println("Economy - The extent to which GDP contributes to the calculation of the happiness score");
        System.out.println("Family - The extent to which family contributes to the calculation happiness score");
        System.out.println("Health - The extent to which life expectancy contributes to the calculation happiness score");
        System.out.println("Freedom - The extent to which freedom contributes to the calculation happiness score");
        System.out.println("Trust - The extent to which perception of corruption contributes to the calculation happiness score");
        System.out.println("Generosity - The extent to which generosity contributes to the calculation happiness score");
        System.out.println("Dystopia Residual - The extent to which dystopia residual contributes to the calculation happiness score");
        divider();
    }

}