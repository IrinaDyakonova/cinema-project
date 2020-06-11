package mate.academy.cinemaproject.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.Order;

public interface MovieSessionService {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession findById(Long id);

}
