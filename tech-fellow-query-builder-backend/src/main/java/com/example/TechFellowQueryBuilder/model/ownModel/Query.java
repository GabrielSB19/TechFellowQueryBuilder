package com.example.TechFellowQueryBuilder.model.ownModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Represents a Query entity with country code and name.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String queryName;

    private String description;

    @ManyToOne
    private UserClient userClient;

    @OneToMany(mappedBy = "query")
    private List<Comment> comments;

    @Column(length = 10000)
    private String query;

    private String worldType;

    String codeCountry;

    String codeRegion;

    String gender;

    String ageMin;

    String ageMax;

    String yearMin;

    String yearMax;

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", queryName='" + queryName + '\'' +
                '}';
    }
}
