package mate.academy.cinemaproject.controllers;

import mate.academy.cinemaproject.dto.ShoppingCartDtoByAddMovie;
import mate.academy.cinemaproject.dto.ShoppingCartResponseDto;
import mate.academy.cinemaproject.mapper.ShoppingCartMapper;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.MovieSessionService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(
            UserService userService,
            ShoppingCartService shoppingCartService,
            MovieSessionService movieSessionService,
            ShoppingCartMapper shoppingCartMapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/add-movie-session")
    public String addMovieSessionToUser(
            @RequestBody ShoppingCartDtoByAddMovie shoppingCartDtoByAddMovie) {
        MovieSession movieSession = movieSessionService
                .findById(shoppingCartDtoByAddMovie.getMovieSessionId());
        User user = userService.findById(shoppingCartDtoByAddMovie.getUserId());
        shoppingCartService.addSession(movieSession, user);
        return "Movie session added to shopping cart ";
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUserId(@RequestParam Long userId) {
        return shoppingCartMapper
                .toDto(shoppingCartService
                        .getByUser(userService
                                .findById(userId)));
    }
}
