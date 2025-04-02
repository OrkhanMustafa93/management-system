package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
