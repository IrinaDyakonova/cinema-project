package mate.academy.cinemaproject.service.impl;

import java.util.Optional;
import mate.academy.cinemaproject.dao.UserDao;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
