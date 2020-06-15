package mate.academy.cinemaproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import mate.academy.cinemaproject.validator.PasswordsValueMatchValidator;

@Constraint(validatedBy = PasswordsValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsValueMatch {

    String message() default "Password mismatch\n";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
