package com.example.TechFellowQueryBuilder.model.ownModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
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

    private String query;

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", queryName='" + queryName + '\'' +
                '}';
    }
}
