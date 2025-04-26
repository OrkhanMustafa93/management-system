package az.hamburg.managementsystem.model.contact.request;

import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateRequest {

    private String email;
    private String phoneNumber;
    private String callCenter;

//    private List<ContactLinkDTO> links;


}
