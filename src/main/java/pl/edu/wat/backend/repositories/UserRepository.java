package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wat.backend.entities.UserImpl;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<UserImpl, Long> {

    // "Student" is Student class
    @Query("SELECT u FROM UserImpl u WHERE u.email = ?1")
    Optional<UserImpl> findUserByEmail(String email);

    @Query("SELECT u FROM UserImpl u WHERE u.username = ?1")
    Optional<UserImpl> findUserByUsername(String username);
}
