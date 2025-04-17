package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationReadDetailResponse {

    private Long  id;

    private String name;

    private Boolean status;

    private ContactCreateResponse contact;
}
