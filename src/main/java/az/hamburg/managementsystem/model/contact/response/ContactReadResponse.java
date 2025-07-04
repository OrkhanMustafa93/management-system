package az.hamburg.managementsystem.model.contact.response;

import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactReadResponse {

    private Long id;
    private String email;
    private String phoneNumber;
    private String callCenter;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;

    private List<ContactLinkDTO> links;

    private AddressReadResponse  address;


}
