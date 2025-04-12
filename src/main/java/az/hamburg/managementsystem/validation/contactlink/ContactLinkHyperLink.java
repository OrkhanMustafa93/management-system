package az.hamburg.managementsystem.validation.contactlink;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(
        regexp = "^(https?://)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
        message = "Website must be a valid URL (e.g. google.com or https://google.com)"
)
public @interface ContactLinkHyperLink {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
