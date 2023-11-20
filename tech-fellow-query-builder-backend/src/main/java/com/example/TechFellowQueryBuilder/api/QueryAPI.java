package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RequestMapping(QueryAPI.BASE_URL)
public interface QueryAPI {
    String BASE_URL = "/api/query";

    @PostMapping("/create")
    QueryResponseDTO createQuery (@RequestBody @Valid QueryRequestDTO queryRequestDTO);

    @GetMapping("/get")
    List<QueryResponseDTO> getAllQuery();

    @GetMapping("/get/{id}")
    QueryResponseDTO getQueryById(@PathVariable String id);
}
