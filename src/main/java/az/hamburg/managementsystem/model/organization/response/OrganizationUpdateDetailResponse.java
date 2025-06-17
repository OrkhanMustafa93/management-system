package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import az.hamburg.managementsystem.validation.organization.OrganizationName;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationUpdateDetailResponse {

    @OrganizationName
    private String name;

    private Boolean status;

    private Long contactId;

    @Valid
    private ContactUpdateResponse contact;

    private AddressUpdateResponse address;

    private List<ContactLinkUpdateResponse> contactLinks;

    private LocalDateTime modified;
    private String modifiedBy;

}
