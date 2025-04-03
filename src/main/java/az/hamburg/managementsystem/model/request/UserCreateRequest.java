package az.hamburg.managementsystem.model.request;

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
