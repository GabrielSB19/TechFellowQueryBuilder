package com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Group Age Special DTO with code and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAgeSpecialDTO {
    private String groupAgeSpecialCode;
    private String groupAgeSpecialName;
}
