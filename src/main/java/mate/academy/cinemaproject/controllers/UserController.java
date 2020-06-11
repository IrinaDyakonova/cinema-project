package mate.academy.cinemaproject.controllers;

import mate.academy.cinemaproject.dto.UserResponseDto;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*
    Get user by email - GET: /users/byemail?email

     */
    @GetMapping("/by-email{email}")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return prepare(userService.findByEmail(email).get());
    }

    private UserResponseDto prepare(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
