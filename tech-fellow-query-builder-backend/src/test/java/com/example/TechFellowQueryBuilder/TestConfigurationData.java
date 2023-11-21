package com.example.TechFellowQueryBuilder;

import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.CommentRepository;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfigurationData {
    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(UserRepository userRepository, CommentRepository commentRepository, QueryRepository queryRepository) {

        UserClient user1 = UserClient.builder()
                .username("user1")
                .build();
        UserClient user2 = UserClient.builder()
                .username("user2")
                .build();

        Query query1 = Query.builder()
                .query("query1")
                .description("description1")
                .userClient(user1)
                .query("SELECT * FROM table1")
                .worldType("COL")
                .build();

        Query query2 = Query.builder()
                .query("query2")
                .description("description2")
                .userClient(user2)
                .query("SELECT * FROM table2")
                .worldType("ARG")
                .build();

        Comment comment1 = Comment.builder()
                .comment("comment1")
                .userClient(user1)
                .query(query1)
                .build();

        Comment comment2 = Comment.builder()
                .comment("comment1")
                .userClient(user1)
                .query(query1)
                .build();


        return args -> {
            userRepository.save(user1);
            userRepository.save(user2);
            queryRepository.save(query1);
            queryRepository.save(query2);
            commentRepository.save(comment1);
            commentRepository.save(comment2);
        };
    }
}

