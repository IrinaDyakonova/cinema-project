package mate.academy.cinemaproject.dao;

import java.util.Optional;
import mate.academy.cinemaproject.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);

}
