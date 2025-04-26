package az.hamburg.managementsystem.repository;

import az.hamburg.managementsystem.domain.ContactLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactLinkRepository extends JpaRepository<ContactLink, Long> {

    List<ContactLink> findByContactId(Long contactId);
}
