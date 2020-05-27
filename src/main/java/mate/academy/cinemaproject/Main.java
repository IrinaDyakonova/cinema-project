package mate.academy.cinemaproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.lib.Injector;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.AuthenticationService;
import mate.academy.cinemaproject.service.CinemaHallService;
import mate.academy.cinemaproject.service.MovieService;
import mate.academy.cinemaproject.service.MovieSessionService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.cinemaproject");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {

        Movie movie1 = new Movie();
        movie1.setTitle("Where'd You Go, Bernadette");
        movie1.setDescription("Bernadette Fox is a charming woman "
                + "and a talented architect who lives in a beautiful house "
                + "with a loving family. Once Bernadette disappears "
                + "without a trace. Now her family has to find out "
                + "where and why she went, and return her home.");
        movieService.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Just a Gigolo");
        movie2.setDescription("Aging Zhigot Alex, accustomed to living "
                + "at the expense of a rich girlfriend, was sent "
                + "to a forced pension. He settles with his sister "
                + "and nephew, but does not abandon attempts to find "
                + "a new patroness");
        movieService.add(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("Bombshell");
        movie3.setDescription("A drama based on personal stories "
                + "of Fox News television journalists who were "
                + "sexually harassed at work and sued company "
                + "founder Roger Isles. Their struggle marked "
                + "the beginning of a massive campaign against "
                + "harassment and discrimination.");
        movieService.add(movie3);

        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(12);
        cinemaHall1.setDescription("BLACK");
        cinemaHallService.add(cinemaHall1);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(2);
        cinemaHall2.setDescription("VIOLET");
        cinemaHallService.add(cinemaHall2);

        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movie1Session = new MovieSession();
        movie1Session.setShowTime(LocalDateTime.of(2020, 5, 22, 17, 45));
        movie1Session.setMovie(movie1);
        movie1Session.setCinemaHall(cinemaHall1);
        movieSessionService.add(movie1Session);

        MovieSession movie2Session = new MovieSession();
        movie2Session.setShowTime(LocalDateTime.now());
        movie2Session.setMovie(movie2);
        movie2Session.setCinemaHall(cinemaHall2);
        movieSessionService.add(movie2Session);

        LocalDate today = LocalDate.of(2020, 5, 22);

        movieSessionService.findAvailableSessions(movie1.getId(), today)
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(movie2.getId(), today)
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(movie3.getId(), today)
                .forEach(System.out::println);

        User irinkoPavlinko = authenticationService.register("irinko_pavlinko", "3344410782");
        authenticationService.login("irinko_pavlinko", "3344410782");
        System.out.println(userService.findByEmail("irinko_pavlinko").get());

        System.out.println(shoppingCartService.getByUser(irinkoPavlinko));

        shoppingCartService.addSession(movie1Session, irinkoPavlinko);

        System.out.println(shoppingCartService.getByUser(irinkoPavlinko));
    }
}
