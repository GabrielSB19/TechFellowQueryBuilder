package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QueryRepository extends JpaRepository<Query, UUID> {


}
