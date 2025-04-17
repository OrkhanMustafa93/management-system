package az.hamburg.managementsystem.model.dto;

import az.hamburg.managementsystem.validation.contactlink.ContactLinkAnchorText;
import az.hamburg.managementsystem.validation.contactlink.ContactLinkHyperLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLinkDTO {

    private Long id;

    @ContactLinkHyperLink
    private String hyperLink;

    @ContactLinkAnchorText
    private String anchorText;

}
