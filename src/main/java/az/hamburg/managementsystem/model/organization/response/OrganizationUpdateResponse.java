package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
