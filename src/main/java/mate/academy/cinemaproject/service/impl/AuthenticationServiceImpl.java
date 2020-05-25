package mate.academy.cinemaproject.service.impl;

import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.lib.Inject;
import mate.academy.cinemaproject.lib.Service;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.AuthenticationService;
import mate.academy.cinemaproject.service.UserService;
import mate.academy.cinemaproject.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email).orElseThrow(() ->
                new AuthenticationException("Incorrect user name or password"));

        if (HashUtil.hashPassword(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect user name or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        return userService.add(user);
    }
}
