package az.hamburg.managementsystem.model.organizationuser.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUserReadResponse {

    private Long id;
    private Long userId;
    private Long organizationId;
    private Long roleId;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
}
