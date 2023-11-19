package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserClient, UUID>{

    Optional<UserClient> findByUsername(String username);

}
