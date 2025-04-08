package az.hamburg.managementsystem.model.contact.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateResponse {

    private Long id;
    private String email;
    private String phoneNumber;
    private Integer callCenter;
    private LocalDateTime modified;
    private String modifiedBy;

}
