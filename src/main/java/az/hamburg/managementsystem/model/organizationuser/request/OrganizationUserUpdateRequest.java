package az.hamburg.managementsystem.model.organizationuser.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationUserUpdateRequest {

    private Long userId;
    private Long organizationId;
    private Long roleId;
}
