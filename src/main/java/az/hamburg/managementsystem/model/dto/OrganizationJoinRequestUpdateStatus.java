package az.hamburg.managementsystem.model.dto;

import az.hamburg.managementsystem.domain.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinRequestUpdateStatus {

    private RequestStatus status;

}