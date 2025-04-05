package az.hamburg.managementsystem.model.user.request;

import az.hamburg.managementsystem.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;


}
