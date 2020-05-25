package mate.academy.cinemaproject.service;

import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);

}
