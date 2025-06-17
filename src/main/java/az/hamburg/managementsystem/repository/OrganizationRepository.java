package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> getAllOrganizationsByStatus(Boolean status);

    boolean existsByName(String name);
}