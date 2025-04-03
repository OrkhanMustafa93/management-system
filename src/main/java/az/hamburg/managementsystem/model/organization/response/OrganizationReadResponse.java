package az.hamburg.managementsystem.model.organization.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationReadResponse {
    private Long id;
    private String name;
    private Boolean status;
}