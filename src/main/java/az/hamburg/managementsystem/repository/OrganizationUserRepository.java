package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.domain.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {

    List<OrganizationUser> getAllByOrganizationId(Long organizationId);

    List<OrganizationUser> findAllByUserId(Long userId);

}
