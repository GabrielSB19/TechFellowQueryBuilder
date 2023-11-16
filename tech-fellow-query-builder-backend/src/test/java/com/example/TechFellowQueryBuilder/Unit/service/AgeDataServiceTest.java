package com.example.TechFellowQueryBuilder.Unit.service;

import com.example.TechFellowQueryBuilder.dto.response.GroupAgeSpecialDTO;
import com.example.TechFellowQueryBuilder.mapper.GroupAgeSpecialMapper;
import com.example.TechFellowQueryBuilder.model.GroupAgeSpecial;
import com.example.TechFellowQueryBuilder.service.AgeDataService;
import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryAgeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AgeDataServiceTest {

    @Mock
    private BigQueryAgeDataService bigQueryAgeDataService;

    @Mock
    private GroupAgeSpecialMapper groupAgeSpecialMapper;

    @InjectMocks
    private AgeDataService ageDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getGroupAgeSpecialsTest() throws InterruptedException, IOException {

        GroupAgeSpecial mockGroupAgeSpecial = new GroupAgeSpecial("SP.PRE.TOTL.FE.IN", "Population of the official age for pre-primary education, female (number)");

        when(bigQueryAgeDataService.getGroupAgeSpecial()).thenReturn(List.of(mockGroupAgeSpecial));

        when(groupAgeSpecialMapper.toDTO(any())).thenAnswer(invocation -> {
            GroupAgeSpecial source = invocation.getArgument(0);
            assertNotNull(source.getGroupAgeSpecialCode());
            assertNotNull(source.getGroupAgeSpecialName());

            // Simula la conversión a DTO
            return new GroupAgeSpecialDTO(source.getGroupAgeSpecialCode(), source.getGroupAgeSpecialName());
        });

        List<GroupAgeSpecialDTO> result = ageDataService.getGroupAgeSpecials();

        assertEquals(1, result.size());

        verify(bigQueryAgeDataService, times(1)).getGroupAgeSpecial();
        verify(groupAgeSpecialMapper, times(1)).toDTO(any());
    }

    @Test
    void getGroupAgeSpecialsErrorTest() throws InterruptedException, IOException {
        when(bigQueryAgeDataService.getGroupAgeSpecial()).thenThrow(InterruptedException.class);
        assertThrows(InterruptedException.class, () -> ageDataService.getGroupAgeSpecials());
        verify(bigQueryAgeDataService, times(1)).getGroupAgeSpecial();
    }
}
