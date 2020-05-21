package mate.academy.cinemaproject.service;

import java.util.List;
import mate.academy.cinemaproject.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
