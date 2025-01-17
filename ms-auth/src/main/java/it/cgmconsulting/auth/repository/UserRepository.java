package it.cgmconsulting.auth.repository;

import it.cgmconsulting.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    boolean existsByIdAndEnabledTrue(int id);

    @Query(value="SELECT u FROM User u WHERE u.username = :username")
    Optional<User> getUserByUsername(String username);
}
