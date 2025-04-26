package az.hamburg.managementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationStatusReadResponse {
    private Long id;
    private Boolean status;
}
