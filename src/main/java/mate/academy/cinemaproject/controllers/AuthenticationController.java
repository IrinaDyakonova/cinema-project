package mate.academy.cinemaproject.controllers;

import mate.academy.cinemaproject.dto.UserRequestDto;
import mate.academy.cinemaproject.dto.UserResponseDto;
import mate.academy.cinemaproject.mapper.UserMapper;
import mate.academy.cinemaproject.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(
            AuthenticationService authenticationService,
            UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return userMapper
                .toDto(authenticationService
                        .register(userRequestDto.getEmail(), userRequestDto.getPassword()));

    }
}
