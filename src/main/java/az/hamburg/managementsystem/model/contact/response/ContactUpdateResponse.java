package az.hamburg.managementsystem.model.contact.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateResponse {

    private Long id;
    private String email;
    private String phoneNumber;
    private Integer callCenter;

}
