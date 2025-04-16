package az.hamburg.managementsystem.model.organizationjoinrequest.response;

import az.hamburg.managementsystem.domain.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinRequestReadResponse {
    private Long id;

    private Long userId;
    private Long organizationId;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
}
