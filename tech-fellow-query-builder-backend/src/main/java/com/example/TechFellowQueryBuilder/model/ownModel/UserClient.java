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
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    @OneToMany(mappedBy = "userClient")
    private List<Query> queries;

    @OneToMany(mappedBy = "userClient")
    private List<Comment> comments;

}
