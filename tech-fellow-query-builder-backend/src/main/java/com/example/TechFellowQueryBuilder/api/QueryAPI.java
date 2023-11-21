package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

/**
 * Interface defining operations related to queries in the API.
 */
@RequestMapping(QueryAPI.BASE_URL)
public interface QueryAPI {

    /**
     * Base URL for query operations.
     */
    String BASE_URL = "/api/query";

    /**
     * Creates a new query.
     *
     * @param queryRequestDTO The request DTO containing information for the query creation.
     * @return The response DTO containing information about the created query.
     */
    @PostMapping("/create")
    @CrossOrigin("*")
    QueryResponseDTO createQuery (@RequestBody @Valid QueryRequestDTO queryRequestDTO);

    /**
     * Gets all queries.
     *
     * @return A list of response DTOs containing information about all queries.
     */
    @GetMapping("/get")
    @CrossOrigin("*")
    List<QueryResponseDTO> getAllQuery();

    /**
     * Gets a query by ID.
     *
     * @param id The ID of the query to retrieve.
     * @return The response DTO containing information about the specified query.
     */
    @GetMapping("/get/{id}")
    @CrossOrigin("*")
    QueryResponseDTO getQueryById(@PathVariable String id);

    /**
     * Gets queries by user/client name.
     *
     * @param name The name of the user/client for whom queries are to be retrieved.
     * @return A list of response DTOs containing information about the queries for the specified user/client.
     */
    @GetMapping("/getByUser/{name}")
    @CrossOrigin("*")
    List<QueryResponseDTO> getQueryByUserClientName(@PathVariable String name);
}
