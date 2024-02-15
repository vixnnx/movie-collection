import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private double runtime;
    private double userRating;


    public Movie(String m, String c, String d, String o, double r, double u) {
        title = m;
        cast = c;
        director = d;
        overview = o;
        runtime = r;
        userRating = u;
    }
    public String getTitle() {
        return title;
    }
    public String getCast() {
        return cast;
    }
    public String getDirector() {
        return director;
    }
    public String getOverview() {
        return overview;
    }
    public double getRuntime() {
        return runtime;
    }
    public double getUserRating() {
        return userRating;
    }

}
