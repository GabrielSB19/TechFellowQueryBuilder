package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.QueryMapper;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for handling business logic related to queries.
 */
@Service
@AllArgsConstructor
public class QueryService {


    private final UserRepository userRepository;

    private final QueryRepository queryRepository;

    private final QueryMapper queryMapper;

    /**
     * Creates a new query based on the provided request DTO.
     *
     * @param queryRequestDTO The request DTO containing information for creating the query.
     * @return The response DTO containing information about the created query.
     */
    public QueryResponseDTO createQuery(QueryRequestDTO queryRequestDTO) {
        System.out.println(queryRequestDTO);
        Query query = queryMapper.toQuery(queryRequestDTO);
        query.setUserClient(userRepository.findByUsername(queryRequestDTO.getUserClient()).orElseThrow(() -> new RuntimeException("User not found")));
        query.setComments(Collections.emptyList());
        query.setWorldType(queryRequestDTO.getWorldType());
        return queryMapper.toQueryResponseDTO(queryRepository.save(query));
    }

    /**
     * Retrieves a list of all queries in the system.
     *
     * @return A list of response DTOs containing information about all queries.
     */
    public List<QueryResponseDTO> getAllQueries() {
        return queryRepository.findAll().stream().map(queryMapper::toQueryResponseDTO).toList();
    }

    /**
     * Retrieves a query by its unique identifier.
     *
     * @param id The unique identifier of the query to retrieve.
     * @return The response DTO containing information about the specified query.
     */
    public QueryResponseDTO getQueryById(UUID id){
        return queryMapper.toQueryResponseDTO(queryRepository.findById(id).orElseThrow(() -> new RuntimeException("Query not found")));
    }

    /**
     * Retrieves a list of queries associated with a specific user client.
     *
     * @param userClient The username of the user client.
     * @return A list of response DTOs containing information about queries associated with the specified user client.
     */
    public List<QueryResponseDTO> getQueryByUserClient(String userClient){
        UserClient user = userRepository.findByUsername(userClient).orElseThrow(() -> new RuntimeException("User not found"));
        return queryRepository.findByUserClient_Id(user.getId()).stream().map(queryMapper::toQueryResponseDTO).toList();
    }
}