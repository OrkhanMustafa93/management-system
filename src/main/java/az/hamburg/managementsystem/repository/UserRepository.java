package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
