package mate.academy.cinemaproject.service;

import java.util.List;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie findById(Long id);

}
