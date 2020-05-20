package mate.academy.cinemaproject.service;

import java.util.List;
import mate.academy.cinemaproject.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
