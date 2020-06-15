package mate.academy.cinemaproject.service;

import java.util.Optional;
import mate.academy.cinemaproject.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);

}
