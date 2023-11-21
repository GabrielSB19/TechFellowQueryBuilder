package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Query entities in the database.
 */
@Repository
public interface QueryRepository extends JpaRepository<Query, UUID> {

    /**
     * Retrieves a list of queries based on the unique identifier of the associated user client.
     *
     * @param userClient The unique identifier of the user client.
     * @return A list of Query entities associated with the specified user client.
     */
    List<Query> findByUserClient_Id(UUID userClient);
}
