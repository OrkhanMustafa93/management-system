package az.hamburg.managementsystem.model.user.request;

import az.hamburg.managementsystem.validation.user.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @Name
    private String name;

    @UserName
    private String username;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email formatı düzgün deyil"
    )
    private String email;

    @Password
    private String password;

    @PhoneNumber
    private String phoneNumber;

}
