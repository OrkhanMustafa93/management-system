package az.hamburg.managementsystem.model.contact.request;

import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.validation.contact.ContactCallCenter;
import az.hamburg.managementsystem.validation.contact.ContactEmail;
import az.hamburg.managementsystem.validation.contact.ContactPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateRequest {

    @ContactEmail
    private String email;

    @ContactPhoneNumber
    private String phoneNumber;

    @ContactCallCenter
    private String callCenter;

    private List<ContactLinkDTO> links;

}
