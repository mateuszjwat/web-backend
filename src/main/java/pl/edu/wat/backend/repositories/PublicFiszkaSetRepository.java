package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.PublicFiszkaSet;

public interface PublicFiszkaSetRepository extends JpaRepository<PublicFiszkaSet, Long> {
}
