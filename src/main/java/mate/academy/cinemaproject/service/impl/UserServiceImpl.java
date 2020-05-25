package mate.academy.cinemaproject.service.impl;

import java.util.Optional;
import mate.academy.cinemaproject.dao.UserDao;
import mate.academy.cinemaproject.lib.Inject;
import mate.academy.cinemaproject.lib.Service;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
