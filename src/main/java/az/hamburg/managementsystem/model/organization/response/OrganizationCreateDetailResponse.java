package az.hamburg.managementsystem.model.organization.response;

import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.validation.organization.OrganizationName;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCreateDetailResponse {

    private Long  id;

    private String name;

    private Boolean status;

    private ContactCreateResponse contact;

}
