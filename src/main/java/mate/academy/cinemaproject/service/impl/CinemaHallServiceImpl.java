package mate.academy.cinemaproject.service.impl;

import java.util.List;
import mate.academy.cinemaproject.dao.CinemaHallDao;
import mate.academy.cinemaproject.lib.Inject;
import mate.academy.cinemaproject.lib.Service;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
