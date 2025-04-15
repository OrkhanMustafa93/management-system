package az.hamburg.managementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLinkDTO {

    private Long id;
    private String hyperLink;
    private String anchorText;
}
