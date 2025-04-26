package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationCreateDetailResponse {

    private Long  id;

    private String name;

    private Boolean status;

    private ContactCreateResponse contact;

    private AddressCreateResponse address;

    private List<ContactLinkCreateResponse> contactLinks;
    private LocalDateTime created;
    private String createdBy;



}
