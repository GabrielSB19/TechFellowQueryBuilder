package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing UserClient entities in the database.
 */
public interface UserRepository extends JpaRepository<UserClient, UUID>{

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for.
     * @return An Optional containing the found UserClient, or an empty Optional if not found.
     */
    Optional<UserClient> findByUsername(String username);

}
