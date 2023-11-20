package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.QueryMapper;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class QueryService {


    private final UserRepository userRepository;

    private final QueryRepository queryRepository;

    private final QueryMapper queryMapper;

    public QueryResponseDTO createQuery(QueryRequestDTO queryRequestDTO) {
        Query query = queryMapper.toQuery(queryRequestDTO);
        query.setUserClient(userRepository.findByUsername(queryRequestDTO.getUserClient()).orElseThrow(() -> new RuntimeException("User not found")));
        return queryMapper.toQueryResponseDTO(queryRepository.save(query));
    }

    public List<QueryResponseDTO> getAllQueries() {
        return queryRepository.findAll().stream().map(queryMapper::toQueryResponseDTO).toList();
    }

    public QueryResponseDTO getQueryById(UUID id){
        return queryMapper.toQueryResponseDTO(queryRepository.findById(id).orElseThrow(() -> new RuntimeException("Query not found")));
    }

    public void buildQuery(){

    }
}