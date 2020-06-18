package mate.academy.cinemaproject.controllers;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.cinemaproject.model.Role;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.RoleService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectController {

    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectController(
            RoleService roleService,
            ShoppingCartService shoppingCartService,
            UserService userService,
            PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public String inject() {

        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("dyakonova.irina.v@gmail.com");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRoles(Set.of(adminRole));
        User user = new User();
        user.setEmail("pavlichenko26071991@gmail.com");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(Set.of(userRole));
        userService.add(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(admin);
        shoppingCartService.registerNewShoppingCart(user);
        return "Inject add to DB!";
    }
}
