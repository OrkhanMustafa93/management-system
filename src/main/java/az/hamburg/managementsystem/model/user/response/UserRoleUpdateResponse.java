package az.hamburg.managementsystem.model.user.response;

import az.hamburg.managementsystem.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleUpdateResponse {
    private Long id;
    private RoleType roleType;
    private String modifiedBy;
}
