package az.hamburg.managementsystem.model.organization.request;

import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.validation.organization.OrganizationName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateDetailRequest {

    @OrganizationName
    private String name;

    private Boolean status;

    private ContactCreateRequest contact;

    private List<ContactLinkDTO> links;

}
