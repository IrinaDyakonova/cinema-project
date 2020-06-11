package mate.academy.cinemaproject.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.MovieRequestDto;
import mate.academy.cinemaproject.dto.MovieResponseDto;
import mate.academy.cinemaproject.dto.MovieSessionRequestDto;
import mate.academy.cinemaproject.dto.UserResponseDto;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    /*
    Add movie - POST: /movies
Get all movies - GET: /movies
     */

    @PostMapping
    public String completeMovies(@RequestBody MovieRequestDto movieRequestDto) {
       movieService.add(prepare(movieRequestDto));
        return "Complete movie creat";

    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream().map(movie -> prepare(movie)).collect(Collectors.toList());
    }

    private Movie prepare(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    private MovieResponseDto prepare(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
