package mate.academy.cinemaproject.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mate.academy.cinemaproject.annotation.EmailConstraint;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        String regex = ".+\\.+.+@.+\\..+";
        return email != null
                && email.matches(regex)
                && email.length() >= 8
                && email.length() <= 28;
    }
}
