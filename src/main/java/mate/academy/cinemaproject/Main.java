package mate.academy.cinemaproject;

import mate.academy.cinemaproject.lib.Injector;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.cinemaproject");

    public static void main(String[] args) {

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
