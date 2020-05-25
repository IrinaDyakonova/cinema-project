package mate.academy.cinemaproject.dao;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinemaproject.model.MovieSession;

public interface MovieSessionDao {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}