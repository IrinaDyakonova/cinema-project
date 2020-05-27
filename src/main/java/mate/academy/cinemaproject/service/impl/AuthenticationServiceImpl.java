package mate.academy.cinemaproject.service.impl;

import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.lib.Inject;
import mate.academy.cinemaproject.lib.Service;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.AuthenticationService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;
import mate.academy.cinemaproject.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

    @Inject
    ShoppingCartService shoppingCartService;

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
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        User userDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userDB);
        return userDB;
    }
}
