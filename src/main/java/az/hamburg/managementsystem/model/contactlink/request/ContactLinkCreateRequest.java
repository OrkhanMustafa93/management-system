package az.hamburg.managementsystem.model.contactlink.request;

import az.hamburg.managementsystem.validation.contactlink.ContactLinkAnchorText;
import az.hamburg.managementsystem.validation.contactlink.ContactLinkHyperLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLinkCreateRequest {

    @ContactLinkHyperLink
    private String hyperLink;

    @ContactLinkAnchorText
    private String anchorText;

}
