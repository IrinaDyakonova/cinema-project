package mate.academy.cinemaproject.controllers;

import mate.academy.cinemaproject.dto.UserRequestDto;
import mate.academy.cinemaproject.dto.UserResponseDto;
import mate.academy.cinemaproject.exeption.AuthenticationException;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    /*
        Register - POST: /register

         */
  /*  @PostMapping("/register")
    public void register(@RequestParam UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),userRequestDto.getPassword());
    }*/

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return prepare( authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword()));

    }


    private UserResponseDto prepare(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
