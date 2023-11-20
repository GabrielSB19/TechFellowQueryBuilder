package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping(GetDataAPI.BASE_URL)
public interface GetDataAPI {

    String BASE_URL = "/api/doQuery";

    @PostMapping("/")
    GetDataResponseDTO doQuery(@RequestBody @Valid GetDataRequestDTO getDataRequestDTO) throws IOException, InterruptedException;

}
