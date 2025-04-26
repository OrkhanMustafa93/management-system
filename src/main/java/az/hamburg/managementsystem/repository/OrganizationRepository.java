package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> getAllOrganizationsByStatus(Boolean status);
}
