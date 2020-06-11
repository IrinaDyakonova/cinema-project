package mate.academy.cinemaproject.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.CinemaHallRequestDto;
import mate.academy.cinemaproject.dto.CinemaHallResponseDto;
import mate.academy.cinemaproject.mapper.CinemaHallMapper;
import mate.academy.cinemaproject.service.CinemaHallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(
            CinemaHallService cinemaHallService,
            CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public String completeCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper.toEntity(cinemaHallRequestDto));
        return "Complete cinema hall creat";

    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService
                .getAll()
                .stream()
                .map(cinemaHallMapper::toDto)
                .collect(Collectors.toList());
    }

}
