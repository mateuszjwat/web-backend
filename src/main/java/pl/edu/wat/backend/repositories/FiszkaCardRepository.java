package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.backend.entities.FiszkaCard;

public interface FiszkaCardRepository extends JpaRepository<FiszkaCard, Long> {
}
