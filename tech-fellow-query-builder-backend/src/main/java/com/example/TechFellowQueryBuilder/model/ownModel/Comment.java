package com.example.TechFellowQueryBuilder.model.ownModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Represents a Comment entity with country code and name.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String comment;

    @ManyToOne
    private UserClient userClient;

    @ManyToOne
    private Query query;


}
