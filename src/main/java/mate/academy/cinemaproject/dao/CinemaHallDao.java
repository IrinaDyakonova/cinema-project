package mate.academy.cinemaproject.dao;

import java.util.List;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;

public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall findById(Long id);

}
