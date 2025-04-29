package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {
}
