package az.hamburg.managementsystem.model.organizationjoinrequest.request;

import az.hamburg.managementsystem.domain.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinRequestUpdateRequest {

    private Long userId;
    private Long organizationId;
}
