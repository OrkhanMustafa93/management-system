package az.hamburg.managementsystem.model.contact.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateRequest {

    private String email;
    private String phoneNumber;
    private Integer callCenter;

}
