package mate.academy.cinemaproject.service.impl;

import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.AuthenticationService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;
import mate.academy.cinemaproject.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;

    private ShoppingCartService shoppingCartService;

    private HashUtil hashUtil;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     HashUtil hashUtil) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.hashUtil = hashUtil;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email).orElseThrow(() ->
                new AuthenticationException("Incorrect user name or password"));

        if (hashUtil.hashPassword(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect user name or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user.setSalt(hashUtil.getSalt());
        user.setPassword(hashUtil.hashPassword(user.getPassword(), user.getSalt()));
        User userDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userDB);
        return userDB;
    }
}
