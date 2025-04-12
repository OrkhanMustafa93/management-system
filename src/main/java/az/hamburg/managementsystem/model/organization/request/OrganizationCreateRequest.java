package az.hamburg.managementsystem.model.organization.request;

import az.hamburg.managementsystem.validation.organization.OrganizationName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCreateRequest {
    @OrganizationName
    private String name;
    private Boolean status;
}
