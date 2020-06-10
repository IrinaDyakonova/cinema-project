package mate.academy.cinemaproject.service.impl;

import java.util.List;
import mate.academy.cinemaproject.dao.MovieDao;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
