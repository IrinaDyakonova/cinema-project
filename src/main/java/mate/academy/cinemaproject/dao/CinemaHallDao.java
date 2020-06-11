package mate.academy.cinemaproject.dao;

import java.util.List;
import mate.academy.cinemaproject.model.CinemaHall;

public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall findById(Long id);

}
