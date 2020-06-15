package mate.academy.cinemaproject.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinemaproject.model.MovieSession;

public interface MovieSessionService {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession findById(Long id);
}
