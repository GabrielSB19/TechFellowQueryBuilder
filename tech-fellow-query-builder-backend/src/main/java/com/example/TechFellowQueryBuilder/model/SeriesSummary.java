package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeriesSummary {
    private String seriesCode;
    private String topic;
    private String indicatorName;
    private String shortDefinition;
    private String longDefinition;
    private String unitOfMeasure;
    private String periodicity;
    private String basePeriod;
    private String otherNotes;
    private String aggregationMethod;
    private String limitationsAndExceptions;
    private String notesFromOriginalSource;
    private String generalComments;
    private String source;
    private String statisticalConceptAndMethodology;
    private String developmentRelevance;
    private String relatedSourceLinks;
    private String otherWebLinks;
    private String relatedIndicators;
    private String licenseType;
}
