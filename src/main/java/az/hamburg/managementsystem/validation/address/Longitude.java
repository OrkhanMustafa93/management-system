package az.hamburg.managementsystem.validation.address;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotNull(message = "Longitude boş ola bilməz.")
@DecimalMin(value = "-180.0", message = "Longitude -180 dərəcədən az ola bilməz.")
@DecimalMax(value = "180.0", message = "Longitude 180 dərəcədən çox ola bilməz.")
public @interface Longitude {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
