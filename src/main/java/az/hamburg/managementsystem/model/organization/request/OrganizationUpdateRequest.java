package az.hamburg.managementsystem.model.organization.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUpdateRequest {
    private String name;
    private Boolean status;
}
