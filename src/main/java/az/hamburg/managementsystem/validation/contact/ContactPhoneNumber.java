package az.hamburg.managementsystem.validation.contact;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(
    regexp = "^\\+994\\d{9,10}$",
    message = "Əlaqə nömrəsi +994 ilə başlamalı və ardınca 9 rəqəm olmalıdır"
)
public @interface ContactPhoneNumber {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
