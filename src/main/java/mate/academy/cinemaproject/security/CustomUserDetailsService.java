package mate.academy.cinemaproject.security;

import mate.academy.cinemaproject.model.Role;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).get();

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user == null) {
            throw new UsernameNotFoundException("Check the entered email or password!");
        }
        builder = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(role -> role.getRoleName().toString())
                .toArray(String[]::new));
        return builder.build();
    }
}
