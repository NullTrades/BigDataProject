public class BigDataODD {
    public static void main(String[] args) {
        fileReader fr = new fileReader("data/spotify_youtube_ranking.csv");
        fr.storeCsv();

    }
}