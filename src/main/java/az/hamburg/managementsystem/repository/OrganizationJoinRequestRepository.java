package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationJoinRequestRepository extends JpaRepository<OrganizationJoinRequest, Long> {

    List<OrganizationJoinRequest> getAllOrganizationJoinRequestsByStatus(RequestStatus status);

    List<OrganizationJoinRequest> getAllOrganizationJoinRequestByOrganizationId(Long organizationId);
}


