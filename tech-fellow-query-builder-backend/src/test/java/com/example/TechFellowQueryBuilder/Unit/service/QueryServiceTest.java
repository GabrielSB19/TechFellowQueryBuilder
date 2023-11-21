package com.example.TechFellowQueryBuilder.Unit.service;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.QueryMapper;
import com.example.TechFellowQueryBuilder.mapper.QueryMapperImpl;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import com.example.TechFellowQueryBuilder.service.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class QueryServiceTest {


    private UserRepository userRepository;

    private QueryRepository queryRepository;

    private QueryMapper queryMapper;

    private QueryService queryService;

    @BeforeEach
    public void setUp() {
        queryMapper = spy(QueryMapperImpl.class);
        userRepository = mock(UserRepository.class);
        queryRepository = mock(QueryRepository.class);

        queryService = new QueryService(userRepository, queryRepository, queryMapper);
    }

    @Test
    void testCreateQuery() {
        UserClient userClient = new UserClient();
        userClient.setId(UUID.randomUUID());
        userClient.setUsername("test");

        Query mockedQuery = new Query();
        mockedQuery.setId(UUID.randomUUID());
        mockedQuery.setUserClient(userClient);
        mockedQuery.setWorldType("test");
        mockedQuery.setComments(Collections.emptyList());
        QueryRequestDTO queryRequestDTO = new QueryRequestDTO();
        queryRequestDTO.setUserClient(userClient.getUsername());
        queryRequestDTO.setWorldType("test");
        QueryResponseDTO mockedResponseDTO = new QueryResponseDTO();

        when(userRepository.findByUsername(userClient.getUsername())).thenReturn(Optional.of(new UserClient()));
        when(queryMapper.toQuery(any(QueryRequestDTO.class))).thenReturn(mockedQuery);
        when(queryRepository.save(mockedQuery)).thenReturn(mockedQuery);
        when(queryMapper.toQueryResponseDTO(mockedQuery)).thenReturn(mockedResponseDTO);

        QueryResponseDTO result = queryService.createQuery(queryRequestDTO);

        verify(userRepository, times(1)).findByUsername(queryRequestDTO.getUserClient());
        verify(queryMapper, times(1)).toQuery(queryRequestDTO);
        verify(queryRepository, times(1)).save(mockedQuery);
        verify(queryMapper, times(1)).toQueryResponseDTO(mockedQuery);

        assertEquals(mockedResponseDTO, result);
    }

    @Test
    void testCreateQueryWithoutUser(){

        Query mockedQuery = new Query();
        mockedQuery.setId(UUID.randomUUID());
        mockedQuery.setWorldType("test");
        mockedQuery.setComments(Collections.emptyList());

        QueryRequestDTO queryRequestDTO = new QueryRequestDTO();
        queryRequestDTO.setWorldType("test");

        when(userRepository.findByUsername("NoTExist")).thenReturn(Optional.empty());
        when(queryMapper.toQuery(any(QueryRequestDTO.class))).thenReturn(mockedQuery);
        when(queryRepository.save(mockedQuery)).thenReturn(mockedQuery);

        assertThrows(RuntimeException.class, () -> {
            queryService.createQuery(queryRequestDTO);
        });
    }

    @Test
    void testGetAllQueries() {
        Query mockedQuery = new Query();
        mockedQuery.setId(UUID.randomUUID());
        mockedQuery.setWorldType("test");
        mockedQuery.setUserClient(new UserClient());
        mockedQuery.setComments(Collections.emptyList());
        QueryResponseDTO mockedResponseDTO = new QueryResponseDTO();

        when(queryRepository.findAll()).thenReturn(List.of(mockedQuery));
        when(queryMapper.toQueryResponseDTO(mockedQuery)).thenReturn(mockedResponseDTO);

        List<QueryResponseDTO> result = queryService.getAllQueries();

        verify(queryRepository, times(1)).findAll();
        verify(queryMapper, times(1)).toQueryResponseDTO(mockedQuery);

        assertEquals(List.of(mockedResponseDTO), result);
    }

    @Test
    void testGetQueryById(){
        UUID id = UUID.randomUUID();
        Query queryEntity = new Query();
        queryEntity.setUserClient(new UserClient());
        queryEntity.setComments(Collections.emptyList());
        QueryResponseDTO expectedResponse = new QueryResponseDTO();

        when(queryRepository.findById(id)).thenReturn(Optional.of(queryEntity));
        when(queryMapper.toQueryResponseDTO(queryEntity)).thenReturn(expectedResponse);

        QueryResponseDTO actualResponse = queryService.getQueryById(id);

        verify(queryRepository, times(1)).findById(id);
        verify(queryMapper, times(1)).toQueryResponseDTO(queryEntity);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void testGetQueryByIdWhenQueryNotExists() {
        UUID nonExistingQueryId = UUID.randomUUID();
        when(queryRepository.findById(nonExistingQueryId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            queryService.getQueryById(nonExistingQueryId);
        });
    }

    @Test
    void testGetQueryByUserClient(){
           UserClient userClient = new UserClient();
            userClient.setId(UUID.randomUUID());
            userClient.setUsername("test");

            Query queryEntity = new Query();
            queryEntity.setUserClient(userClient);
            queryEntity.setComments(Collections.emptyList());
            QueryResponseDTO expectedResponse = new QueryResponseDTO();

            when(userRepository.findByUsername(userClient.getUsername())).thenReturn(Optional.of(userClient));
            when(queryRepository.findByUserClient_Id(userClient.getId())).thenReturn(List.of(queryEntity));
            when(queryMapper.toQueryResponseDTO(queryEntity)).thenReturn(expectedResponse);

            List<QueryResponseDTO> actualResponse = queryService.getQueryByUserClient(userClient.getUsername());

            verify(userRepository, times(1)).findByUsername(userClient.getUsername());
            verify(queryRepository, times(1)).findByUserClient_Id(userClient.getId());
            verify(queryMapper, times(1)).toQueryResponseDTO(queryEntity);

            assertEquals(List.of(expectedResponse), actualResponse);
    }

    @Test
    void testGetQueryByUserClientWhenUserNotExists() {
        String nonExistingUser = "nonExistingUser";
        when(userRepository.findByUsername(nonExistingUser)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            queryService.getQueryByUserClient(nonExistingUser);
        });
    }


    /*


    public QueryResponseDTO getQueryById(UUID id){
        return queryMapper.toQueryResponseDTO(queryRepository.findById(id).orElseThrow(() -> new RuntimeException("Query not found")));
    }


    public List<QueryResponseDTO> getQueryByUserClient(String userClient){
        UserClient user = userRepository.findByUsername(userClient).orElseThrow(() -> new RuntimeException("User not found"));
        return queryRepository.findByUserClient_Id(user.getId()).stream().map(queryMapper::toQueryResponseDTO).toList();
    }
     */
}
