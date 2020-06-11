package mate.academy.cinemaproject.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.MovieSessionRequestDto;
import mate.academy.cinemaproject.dto.MovieSessionResponseDto;
import mate.academy.cinemaproject.dto.OrderResponseDto;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.service.CinemaHallService;
import mate.academy.cinemaproject.service.MovieService;
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
    /*
    Add moviesession - POST: /moviesessions
Get all available movie sessions - GET: /moviesessions/available?movieId=1&date=29.02.2020
     */

    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieService movieService, CinemaHallService cinemaHallService, MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
    }


    @PostMapping
    public String completeMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(prepare(movieSessionRequestDto));
        return "Movie session creat";

    }

    @GetMapping("/available{movieId, date}")
    public List<MovieSessionResponseDto> allAvailableMovieSessions(@RequestParam Long movieId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return movieSessionService.findAvailableSessions(movieId, date).stream().map(movieSession -> prepareTODO(movieSession)).collect(Collectors.toList());
    }

    private MovieSession prepare(MovieSessionRequestDto movieSessionRequestDto) {
        Movie movie = movieService.findById(movieSessionRequestDto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.findById(movieSessionRequestDto.getCinemaHallId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }
    private MovieSessionResponseDto prepareTODO(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime());
        return movieSessionResponseDto;
    }
}
