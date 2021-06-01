package pl.edu.wat.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.backend.entities.DemoEntity;

@Repository
public interface DemoRepository extends CrudRepository<DemoEntity, Integer> {
}
