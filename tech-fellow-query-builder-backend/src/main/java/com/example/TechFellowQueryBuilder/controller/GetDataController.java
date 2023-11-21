package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.GetDataAPI;
import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.request.GetDataSelectRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import com.example.TechFellowQueryBuilder.service.GetDataBigQuery.GetDataToQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * RestController handling requests related to getting data from BigQuery.
 */
@RestController
@AllArgsConstructor
public class GetDataController implements GetDataAPI {

    private final GetDataToQueryService getDataToQueryService;

    /**
     * Handles the request to perform a general query to obtain data from BigQuery.
     *
     * @param getDataRequestDTO The data transfer object (DTO) containing the request parameters.
     * @return A GetDataResponseDTO containing the response data from the BigQuery query.
     * @throws IOException          If an input or output exception occurs during the query execution.
     * @throws InterruptedException If the query execution is interrupted.
     */
    @Override
    public GetDataResponseDTO doQuery(GetDataRequestDTO getDataRequestDTO) throws IOException, InterruptedException {
        return getDataToQueryService.doQuery(getDataRequestDTO);
    }

    /**
     * Handles the request to perform a SELECT query to obtain specific data from BigQuery.
     *
     * @param getDataSelectRequestDTO The data transfer object (DTO) containing the SELECT query parameters.
     * @return A GetDataResponseDTO containing the response data from the BigQuery SELECT query.
     * @throws IOException          If an input or output exception occurs during the query execution.
     * @throws InterruptedException If the query execution is interrupted.
     */
    @Override
    public GetDataResponseDTO doQueryToSelect(GetDataSelectRequestDTO getDataSelectRequestDTO) throws IOException, InterruptedException {
        return getDataToQueryService.doQueryToSelect(getDataSelectRequestDTO);
    }
}
