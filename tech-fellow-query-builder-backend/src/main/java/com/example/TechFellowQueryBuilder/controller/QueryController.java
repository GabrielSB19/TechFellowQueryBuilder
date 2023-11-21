package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryAPI;
import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for handling queries via RESTful API.
 */
@RestController
@AllArgsConstructor
public class QueryController implements QueryAPI {

    private final QueryService queryService;

    /**
     * Handles the creation of a new query.
     *
     * @param queryRequestDTO The request DTO containing query information.
     * @return The response DTO containing information about the created query.
     */
    @Override
    public QueryResponseDTO createQuery(QueryRequestDTO queryRequestDTO) {
        return queryService.createQuery(queryRequestDTO);
    }

    /**
     * Handles the retrieval of all queries.
     *
     * @return A list of response DTOs containing information about all queries.
     */
    @Override
    public List<QueryResponseDTO> getAllQuery() {
        return queryService.getAllQueries();
    }

    /**
     * Handles the retrieval of a query by its ID.
     *
     * @param id The ID of the query to retrieve.
     * @return The response DTO containing information about the specified query.
     */
    @Override
    public QueryResponseDTO getQueryById(String id) {
        return queryService.getQueryById(UUID.fromString(id));
    }

    /**
     * Handles the retrieval of queries by user client name.
     *
     * @param name The user client name used to filter queries.
     * @return A list of response DTOs containing information about queries with the specified user client name.
     */
    @Override
    public List<QueryResponseDTO> getQueryByUserClientName(String name) {
        return queryService.getQueryByUserClient(name);
    }
}
