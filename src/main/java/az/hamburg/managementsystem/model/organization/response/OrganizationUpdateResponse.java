package az.hamburg.managementsystem.model.organization.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUpdateResponse {
    private Long id;
    private String name;
    private Boolean status;
    private LocalDateTime modified;
    private String modifiedBy;
}
