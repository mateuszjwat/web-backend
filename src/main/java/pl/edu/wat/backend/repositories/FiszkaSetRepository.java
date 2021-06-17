package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.UserImpl;

public interface FiszkaSetRepository extends JpaRepository<FiszkaSet, Long> {
}
