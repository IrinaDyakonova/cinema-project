package mate.academy.cinemaproject.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dao.CinemaHallDao;
import mate.academy.cinemaproject.dto.CinemaHallRequestDto;
import mate.academy.cinemaproject.dto.CinemaHallResponseDto;
import mate.academy.cinemaproject.dto.MovieRequestDto;
import mate.academy.cinemaproject.dto.MovieResponseDto;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.service.CinemaHallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {

    private final CinemaHallService cinemaHallServise;

    public CinemaHallController(CinemaHallService cinemaHallServise) {
        this.cinemaHallServise = cinemaHallServise;
    }


    /*
    Add cinema hall - POST: /cinemahalls
Get all cinema halls - GET: /cinemahalls
     */


    @PostMapping
    public String completeCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallServise.add(prepare(cinemaHallRequestDto));
        return "Complete cinema hall creat";

    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallServise.getAll().stream().map(cinemaHall -> prepare(cinemaHall)).collect(Collectors.toList());
    }

    private CinemaHall prepare(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }

    private CinemaHallResponseDto prepare(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
