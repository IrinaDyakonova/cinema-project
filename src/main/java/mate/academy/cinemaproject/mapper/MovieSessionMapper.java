package mate.academy.cinemaproject.mapper;

import mate.academy.cinemaproject.dto.MovieSessionRequestDto;
import mate.academy.cinemaproject.dto.MovieSessionResponseDto;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.service.CinemaHallService;
import mate.academy.cinemaproject.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {

    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession toEntity(MovieSessionRequestDto movieSessionRequestDto) {
        Movie movie = movieService.findById(movieSessionRequestDto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService
                .findById(movieSessionRequestDto.getCinemaHallId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }

    public MovieSessionResponseDto toDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime());
        return movieSessionResponseDto;
    }
}
