package az.hamburg.managementsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String name;
    private Boolean status;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
