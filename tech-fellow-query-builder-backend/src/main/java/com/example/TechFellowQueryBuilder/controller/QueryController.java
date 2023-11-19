package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryAPI;
import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QueryController implements QueryAPI {

    private final QueryService queryService;

    @Override
    public QueryResponseDTO createQuery(QueryRequestDTO queryRequestDTO) {
        return queryService.createQuery(queryRequestDTO);
    }
}
