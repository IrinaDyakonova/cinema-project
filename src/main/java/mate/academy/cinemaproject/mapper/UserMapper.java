package mate.academy.cinemaproject.mapper;

import mate.academy.cinemaproject.dto.UserResponseDto;
import mate.academy.cinemaproject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

}
