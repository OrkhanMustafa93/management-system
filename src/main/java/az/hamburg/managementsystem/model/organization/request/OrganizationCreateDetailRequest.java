package az.hamburg.managementsystem.model.organization.request;

import az.hamburg.managementsystem.model.address.request.AddressCreateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.validation.organization.OrganizationName;
import jakarta.validation.Valid;
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

    @Valid
    private ContactCreateRequest contact;

    private AddressCreateRequest address;

    private ContactLinkCreateRequest contactLink;


}
