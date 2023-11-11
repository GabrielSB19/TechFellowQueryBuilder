package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryAPI;
import com.example.TechFellowQueryBuilder.service.BigQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QueryController implements QueryAPI {

    private final BigQueryService bigQueryService;

    @Override
    public String getQuery() throws InterruptedException {
        return bigQueryService.basicQuery();
    }
}
