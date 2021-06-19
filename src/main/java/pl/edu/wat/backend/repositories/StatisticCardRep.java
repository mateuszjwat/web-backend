package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.backend.entities.PublicFiszkaSet;
import pl.edu.wat.backend.entities.StatisticCard;
import pl.edu.wat.backend.entities.UserImpl;

import java.util.Optional;

public interface StatisticCardRep  extends JpaRepository<StatisticCard, Long> {

    // "Student" is Student class
    @Query("SELECT u FROM StatisticCard u WHERE u.parentId = ?1")
    Optional<StatisticCard> findStatisticByParentId(long id);
}
