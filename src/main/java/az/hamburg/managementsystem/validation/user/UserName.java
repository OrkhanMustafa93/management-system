package az.hamburg.managementsystem.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Size(min = 10, max = 100, message = "Uzunlugu 10 ve 100 araliginda olmalidi")
@NotBlank(message = "Username adi bos ola bilmez...")
public @interface UserName {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    //{
    //  "name": "name_Esger",
    //  "username": "username_Novruzov",
    //  "email": "esger@gmail.com",
    //  "password": "password_12345678",
    //  "phoneNumber": "1234567890"
    //}

}
