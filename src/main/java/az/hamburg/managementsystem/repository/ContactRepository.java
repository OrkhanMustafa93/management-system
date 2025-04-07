package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
