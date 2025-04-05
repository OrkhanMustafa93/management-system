package az.hamburg.managementsystem.model.user.response;

import az.hamburg.managementsystem.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadResponse {

    private Long id;
    private String name;
    private Boolean status;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleType roleType;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;

}