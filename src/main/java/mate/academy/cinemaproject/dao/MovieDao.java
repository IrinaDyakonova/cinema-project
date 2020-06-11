package mate.academy.cinemaproject.dao;

import java.util.List;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie findById(Long id);

}
