package az.hamburg.managementsystem.model.organization.request;

import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.validation.organization.OrganizationName;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationUpdateDetailRequest {

    @OrganizationName
    private String name;

    private Boolean status;

    private Long contactId;

    @Valid
    private ContactUpdateRequest contact;

    private AddressUpdateRequest address;

    private List<ContactLinkUpdateRequest> contactLinks;

}
