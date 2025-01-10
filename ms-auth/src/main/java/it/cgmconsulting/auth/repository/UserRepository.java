package it.cgmconsulting.auth.repository;

import it.cgmconsulting.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsernameOrEmail(String username, String email);

    @Query(value="SELECT u from User u WHERE u.username= :username ")
    Optional<User> findByUsername(String username);
}
