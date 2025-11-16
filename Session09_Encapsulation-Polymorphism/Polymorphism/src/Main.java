import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Movie theMovie = new Adventure("Star Wars");
//        theMovie.watchMovie();
//
//        Movie theMovie2 = new Comedy("Mr.Bean");
//        theMovie2.watchMovie();
//
//        Movie theMovie3 = new ScienceFiction("Dr.Strange");
//        theMovie3.watchMovie();

//        Movie theMovie = Movie.getMovie("Adventure", "Star Wars");
//        theMovie.watchMovie();
//
//        Movie theMovie2 = Movie.getMovie("Comedy", "Mr.Bean");
//        theMovie2.watchMovie();
//
//        Movie theMovie3 = Movie.getMovie("ScienceFiction", "Dr.Strange");
//        theMovie3.watchMovie();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter type (A for Adventure, C for Comedy," +
                    " S for Science Fiction, or Q to Quit) : ");
            String type = sc.nextLine();
            if ("Qq".contains(type)) {
                break;
            }
            System.out.println("Enter Movie title : ");
            String title = sc.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
    }
}
