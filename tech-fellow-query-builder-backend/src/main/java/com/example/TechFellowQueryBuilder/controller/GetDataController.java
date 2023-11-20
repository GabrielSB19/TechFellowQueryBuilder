package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.GetDataAPI;
import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import com.example.TechFellowQueryBuilder.service.GetDataBigQuery.GetDataToQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class GetDataController implements GetDataAPI {

    private final GetDataToQueryService getDataToQueryService;


    @Override
    public GetDataResponseDTO doQuery(GetDataRequestDTO getDataRequestDTO) throws IOException, InterruptedException {
        return getDataToQueryService.doQuery(getDataRequestDTO);
    }
}
