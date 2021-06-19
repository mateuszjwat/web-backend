package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.backend.entities.FiszkaCard;
import pl.edu.wat.backend.entities.PublicFiszkaCard;

public interface PublicFiszkaCardRepository extends JpaRepository<PublicFiszkaCard, Long> {
}
