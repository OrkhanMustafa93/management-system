package az.hamburg.managementsystem.model.contactlink.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLinkCreateRequest {

    private String hyperLink;
    private String anchorText;

}
