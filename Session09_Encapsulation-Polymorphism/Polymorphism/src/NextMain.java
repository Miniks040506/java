public class NextMain {
    public static void main(String[] args) {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();

        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();

        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();

        var plane = new Comedy("Airplane");
        plane.watchComedy();

        Object unknownedObject = Movie.getMovie("A", "Mr.Bean");
        if (unknownedObject.getClass().getSimpleName() == "Comedy" ) {
            Comedy c = (Comedy) unknownedObject;
            c.watchComedy();;
        } else if (unknownedObject instanceof Adventure) {
            ((Adventure) unknownedObject).watchAdventure();
        }
        else if (unknownedObject instanceof ScienceFiction sf) {
            sf.watchScienceFiction();
        }
    }
}
