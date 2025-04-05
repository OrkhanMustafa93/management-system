package az.hamburg.managementsystem.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDateTime modified;
    private String modifiedBy;
}
