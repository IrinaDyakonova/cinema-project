package mate.academy.cinemaproject.controllers;

import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.ShoppingCartDtoByAddMovie;
import mate.academy.cinemaproject.dto.ShoppingCartResponseDto;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.ShoppingCart;
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



    public ShoppingCartController(UserService userService, ShoppingCartService shoppingCartService, MovieSessionService movieSessionService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
    }


    /*
    Add movie session - POST: /shoppingcarts/addmoviesession?userId <— we will remove this param in the future
Get by user - GET: /shoppingcarts/byuser?userId
     */

    @PostMapping("/add-movie-session")
    public String addMovieSessionToUser(@RequestBody ShoppingCartDtoByAddMovie shoppingCartDtoByAddMovie) {
        MovieSession movieSession = movieSessionService.findById(shoppingCartDtoByAddMovie.getMovieSessionId());
        User user = userService.findById(shoppingCartDtoByAddMovie.getUserId());
        shoppingCartService.addSession(movieSession, user);

        return "Movie session added to shopping cart ";

    }


    @GetMapping("/by-user{userId}")
    public ShoppingCartResponseDto getByUserId(@RequestParam Long userId) {
        return prepare(shoppingCartService.getByUser(userService.findById(userId)));
    }

    private ShoppingCartResponseDto prepare(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketId(shoppingCart.getTickets().stream().map(t->t.getId()).collect(Collectors.toList()));
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());


        return shoppingCartResponseDto;
    }
}
