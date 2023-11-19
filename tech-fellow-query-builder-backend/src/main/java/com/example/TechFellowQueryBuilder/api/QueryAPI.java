package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@RequestMapping(QueryAPI.BASE_URL)
public interface QueryAPI {
    String BASE_URL = "/api/query";

    @PostMapping("/create")
    QueryResponseDTO createQuery (@RequestBody @Valid QueryRequestDTO queryRequestDTO);

}
