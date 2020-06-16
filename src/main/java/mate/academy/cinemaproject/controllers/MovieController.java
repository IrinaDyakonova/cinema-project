package mate.academy.cinemaproject.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mate.academy.cinemaproject.dto.MovieRequestDto;
import mate.academy.cinemaproject.dto.MovieResponseDto;
import mate.academy.cinemaproject.mapper.MovieMapper;
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
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public String completeMovies(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.toEntity(movieRequestDto));
        return "Complete movie creat";
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream().map(movieMapper::toDto).collect(Collectors.toList());
    }

}
