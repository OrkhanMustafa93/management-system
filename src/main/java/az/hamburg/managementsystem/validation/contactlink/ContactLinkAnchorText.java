package az.hamburg.managementsystem.validation.contactlink;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(min = 2 , max = 100 , message = " Metnin uzunlugu min = 2 , max = 100 simvol olmalidir..")
public @interface ContactLinkAnchorText {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
