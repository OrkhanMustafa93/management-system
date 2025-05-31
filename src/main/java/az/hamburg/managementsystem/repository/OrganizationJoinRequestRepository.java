package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationJoinRequestRepository extends JpaRepository<OrganizationJoinRequest, Long> {

    List<OrganizationJoinRequest> getAllOrganizationJoinRequestsByStatus(RequestStatus status);

    List<OrganizationJoinRequest> getAllOrganizationJoinRequestByOrganizationId(Long organizationId);


    @Query("SELECT o FROM OrganizationJoinRequest o WHERE o.organizationId = :organizationId and o.status = 'PENDING'")
    List<OrganizationJoinRequest> findOrganizationRequestByOrganizationId(@Param("organizationId") Long organizationId);

    Optional<OrganizationJoinRequest> findByUserIdAndOrganizationId(Long userId, Long organizationId);

    List<OrganizationJoinRequest> findAllByUserId(Long userId);




}



