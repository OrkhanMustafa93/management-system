package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCreateDetailResponse {
    private String name;

    private Boolean status;

    private Long contactId;

    private List<ContactLinkDTO> links;
}
