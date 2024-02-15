import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Scanner scan;

    public MovieCollection() {
        scan = new Scanner(System.in);
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                movies.add(new Movie(splitData[0], splitData[1], splitData[2],splitData[3],splitData[4],splitData[5]));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void searchTitles() {
        int count = 1;
        System.out.print("Enter a title search term: ");
        String term = scan.nextLine();
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().indexOf(term) != -1) {
                // make a list w all possible movies, then sort it alphabetically: them make ind list based on the new sorted list, then print the list

               // System.out.println(count + ". " + movies.get(i).getTitle());
                //count++;
               // index.add(i);
            }
        }
        if (count > 0) {
            System.out.println("What movie would you like to learn more about? ");
            System.out.print("Enter number: ");
            int choice = scan.nextInt();
            scan.nextLine();
            printInfo(index.get(choice - 1));
        } else {
            System.out.println("No movie titles match that search term");
        }
    }

    private void searchCast() { // make a list of all the actors, wo duplicates

    }



    private void printInfo(int idx) {
        System.out.println("Title: " + movies.get(idx).getTitle());
        System.out.println("Runtime: " + movies.get(idx).getRuntime());
        System.out.println("Directed by: " + movies.get(idx).getDirector());
        System.out.println("Cast: " + movies.get(idx).getCast());
        System.out.println("Overview: " + movies.get(idx).getOverview());
        System.out.println("User Rating: " + movies.get(idx).getUserRating());
        System.out.println("*** Press Enter to Return to Main Menu ***");
        String wait = scan.nextLine();

    }

}
