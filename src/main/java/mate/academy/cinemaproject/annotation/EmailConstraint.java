package mate.academy.cinemaproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import mate.academy.cinemaproject.validator.EmailValidator;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {

    String message() default "Email is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
