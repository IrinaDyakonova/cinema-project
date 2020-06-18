package mate.academy.cinemaproject.service;

import mate.academy.cinemaproject.model.User;

public interface AuthenticationService {

    User register(String email, String password);

}
