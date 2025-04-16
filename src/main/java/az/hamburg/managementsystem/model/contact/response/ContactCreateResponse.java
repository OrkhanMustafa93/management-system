package az.hamburg.managementsystem.model.contact.response;

import az.hamburg.managementsystem.domain.Address;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateResponse {

    private Long id;
    private String email;
    private String phoneNumber;
    private String callCenter;
    private LocalDateTime created;
    private String createdBy;

    private AddressCreateResponse address;

    private List<ContactLinkDTO> links;

}
