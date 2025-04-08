package az.hamburg.managementsystem.model.contactlink.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLinkUpdateResponse {
    private Long id;

    private String hyperLink;
    private String anchorText;
    private LocalDateTime modified;
    private String modifiedBy;
}
