package mate.academy.cinemaproject.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.MovieSessionRequestDto;
import mate.academy.cinemaproject.dto.MovieSessionResponseDto;
import mate.academy.cinemaproject.mapper.MovieSessionMapper;
import mate.academy.cinemaproject.service.MovieSessionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(
                    MovieSessionService movieSessionService,
                    MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public String completeMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.toEntity(movieSessionRequestDto));
        return "Movie session creat";

    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> allAvailableMovieSessions(
            @RequestParam Long movieId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
