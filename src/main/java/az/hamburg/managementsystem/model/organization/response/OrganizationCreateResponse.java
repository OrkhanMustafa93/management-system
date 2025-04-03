package az.hamburg.managementsystem.model.organization.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCreateResponse {
    private Long id;
    private String name;
    private String status;
    private LocalDateTime created;
    private String createdBy;
}
