import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BigDataODD {
    public static Map<String, List<Object>> happyData = new LinkedHashMap<>();

    public static void main(String[] args) {
        fileReader fr = new fileReader("data/happyData2017.csv");
        happyData = fr.storeCsv();
        System.out.println(happyData);

    }
}