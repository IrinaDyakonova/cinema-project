package mate.academy.cinemaproject.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mate.academy.cinemaproject.annotation.PasswordsValueMatch;
import mate.academy.cinemaproject.dto.UserRequestDto;

public class PasswordsValueMatchValidator implements
        ConstraintValidator<PasswordsValueMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        String password = userRequestDto.getPassword();
        String repeatPassword = userRequestDto.getRepeatPassword();
        return password != null
                && password.equals(repeatPassword);
    }
}
