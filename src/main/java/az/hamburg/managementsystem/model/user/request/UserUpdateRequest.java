package az.hamburg.managementsystem.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
