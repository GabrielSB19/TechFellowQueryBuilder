package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.request.GetDataSelectRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Interface defining operations related to querying data in the API.
 */
@RequestMapping(GetDataAPI.BASE_URL)
public interface GetDataAPI {

    /**
     * Base URL for data query operations.
     */
    String BASE_URL = "/api/doQuery";

    /**
     * Executes a data query.
     *
     * @param getDataRequestDTO The request DTO containing information for the data query.
     * @return The response DTO containing the result of the data query.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    @PostMapping("/")
    @CrossOrigin("*")
    GetDataResponseDTO doQuery(@RequestBody @Valid GetDataRequestDTO getDataRequestDTO) throws IOException, InterruptedException;

    /**
     * Executes a data query for selection.
     *
     * @param getDataSelectRequestDTO The request DTO containing information for the data selection query.
     * @return The response DTO containing the result of the data selection query.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    @PostMapping("/select")
    @CrossOrigin("*")
    GetDataResponseDTO doQueryToSelect(@RequestBody @Valid GetDataSelectRequestDTO getDataSelectRequestDTO) throws IOException, InterruptedException;
}
