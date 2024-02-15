import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private double runtime;
    private double userRating;
    private ArrayList<Movie> movies = new ArrayList<>();


    public MovieCollection() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                double price = Double.parseDouble(splitData[1]);
                userRating = Double.parseDouble(splitData[5]);
                movies.add(new Movie(splitData[0], splitData[1], splitData[2],splitData[3],runtime,userRating));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println(movies);
    }
}
