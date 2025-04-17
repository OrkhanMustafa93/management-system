package az.hamburg.managementsystem.model.organization.request;

import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUpdateRequest {
    private String name;
    private Boolean status;

    private Long contactId;


}
