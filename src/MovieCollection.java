import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Scanner scan;
    private ArrayList<String> people = new ArrayList<>();

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
        allActors();
        menu();
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
        ArrayList<Movie> m = new ArrayList<>();
        // list of all movies w term
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().toLowerCase().indexOf(term.toLowerCase()) != -1) {
                m.add(movies.get(i));
                index.add(i);
                count++;
            }
        }
        //sorts index and titles by alphabetical order
        Movie current;
        int num;
        int c;
        for (int i = 1; i < m.size(); i++) {
            current = m.get(i);
            c = index.get(i);
            num = i;
            while (num > 0 && current.getTitle().compareTo(m.get(num - 1).getTitle()) < 0) {
                //if current word is "less" than prevoius word
                m.set(num, m.get(num - 1));
                index.set(num, index.get(num - 1));
                num--;
            }
            m.set(num, current);
            index.set(num, c);
        }
        //prints all the movie choices
        int numbers = 1;
        for (Movie show: m) {
            System.out.println(numbers + ". " + show.getTitle());
            numbers++;
        }

        if (count > 1) {
            System.out.println("What movie would you like to learn more about? ");
            System.out.print("Enter number: ");
            int choice = scan.nextInt();
            scan.nextLine();
            printInfo(index.get(choice - 1));
        } else {
            System.out.println("No movie titles match that search term!");
        }
        System.out.println("*** Press Enter to Return to Main Menu ***");
        scan.nextLine();
    }


    private void searchCast() { // make a list of all the actors, wo duplicates
        // find all people with term in their names
        //go thru all the casts n if person is in the move, add to new movie list
        // use movie list like last method
        int count = 0;
        System.out.print("Enter a person to search for (first or last name): ");
        String term = scan.nextLine();
        ArrayList<Movie> m = new ArrayList<>();
        ArrayList<String> p = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).toLowerCase().indexOf(term.toLowerCase()) != -1) {
                p.add(people.get(i));
                count++;
            }
        }
        int numbers = 1;
        for (String name: p ) {
            System.out.println(numbers + ". " + name);
            numbers++;
        }
        if (count > 0 ) {
            System.out.println("Which would you like to see all movies for? ");
            System.out.print("Enter number: ");
            int choice = scan.nextInt();
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getCast().indexOf(p.get(choice - 1)) != -1) {
                    m.add(movies.get(i));
                    index.add(i);
                }
            }
            Movie current;
            int num;
            int c;
            for (int i = 1; i < m.size(); i++) {
                current = m.get(i);
                c = index.get(i);
                num = i;
                while (num > 0 && current.getTitle().compareTo(m.get(num - 1).getTitle()) < 0) {
                    //if current word is "less" than prevoius word
                    m.set(num, m.get(num - 1));
                    index.set(num, index.get(num - 1));
                    num--;
                }
                m.set(num, current);
                index.set(num, c);
            }
            numbers = 1;
            for (Movie s: m) {
                System.out.println(numbers + ". " + s.getTitle());
                numbers++;
            }
            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");
            choice = scan.nextInt();
            scan.nextLine();
            printInfo(index.get(choice - 1));


        } else {
            System.out.println("No results match your search");
        }
        System.out.println("*** Press Enter to Return to Main Menu ***");
        scan.nextLine();



    }



    private void printInfo(int idx) {
        System.out.println();
        System.out.println("Title: " + movies.get(idx).getTitle());
        System.out.println("Runtime: " + movies.get(idx).getRuntime() + " minutes");
        System.out.println("Directed by: " + movies.get(idx).getDirector());
        System.out.println("Cast: " + movies.get(idx).getCast());
        System.out.println("Overview: " + movies.get(idx).getOverview());
        System.out.println("User Rating: " + movies.get(idx).getUserRating());

    }

    private ArrayList<String> allActors() {
        boolean add = true;
        for (Movie s: movies) {
            String[] split = s.getCast().split("\\|");
            for (int i = 0; i < split.length; i++) {
                add = true;
                for (int j = 0; j < people.size(); j++) {
                    if (split[i].equals(people.get(j))) { // if current movie has an actor alr in the list, dont add
                        add = false;
                    }
                }
                if (add) {
                    people.add(split[i]);
                }
            }
        }

        String current;
        int num;
        for (int i = 1; i < people.size(); i++) {
            current = people.get(i);
            num = i;
            while (num > 0 && current.compareTo(people.get(num - 1)) < 0) {
                //if current word is "less" than prevoius word
                people.set(num, people.get(num - 1));
                num--;
            }
            people.set(num, current);
        }
        return people;
    }



}
