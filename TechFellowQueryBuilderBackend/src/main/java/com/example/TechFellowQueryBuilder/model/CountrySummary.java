package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountrySummary {
    private String countryCode;
    private String shortName;
    private String tableName;
    private String longName;
    private String twoAlphaCode;
    private String currencyUnit;
    private String specialNotes;
    private String region;
    private String incomeGroup;
    private String wbTwoCode;
    private String nationalAccountsBaseYear;
    private String nationalAccountsReferenceYear;
    private String snaPriceValuation;
    private String lendingCategory;
    private String otherGroups;
    private String systemOfNationalAccounts;
    private String alternativeConversionFactor;
    private String pppSurveyYear;
    private String balanceOfPaymentsManualInUse;
    private String externalDebtReportingStatus;
    private String systemOfTrade;
    private String governmentAccountingConcept;
    private String imfDataDisseminationStandard;
    private String latestPopulationCensus;
    private String latestHouseholdSurvey;
    private String sourceOfMostRecentIncomeAndExpenditureData;
    private String vitalRegistrationComplete;
    private String latestAgriculturalCensus;
    private int latestIndustrialData;
    private int latestTradeData;
    private String latestWaterWithdrawalData;
}
