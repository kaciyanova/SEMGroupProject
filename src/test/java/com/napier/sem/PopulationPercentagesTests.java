package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.PopulationPercentages.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//(WIP)

public class PopulationPercentagesTests {

    @BeforeAll
    static void init() {

    }

    @Test
    void GetContinentPopulationStatsTestNull() {
        GetContinentPopulationStats();
    }

    @Test
    void GetContinentPopulationStatsTestEmpty() {
        ArrayList<PopulationStatistics> countriesInArea = new ArrayList<>();
        GetContinentPopulationStats();
//      assertFalse(PopulationPercentages.GetContinentPopulationStats(countriesInArea).isEmpty());
    }

    @Test
    void GetContinentPopulationStatsTestNormal() {
        PopulationPercentages.GetContinentPopulationStats();
    }


    @Test
    void GetRegionPopulationStatsTestNull() {
        GetRegionPopulationStats();
        PopulationPercentages.GetRegionPopulationStats();
    }

    @Test
    void GetRegionPopulationStatsTestEmpty() {
        ArrayList<Country> countriesInArea = new ArrayList<>();
        GetRegionPopulationStats();
//        assertFalse(PopulationPercentages.GetRegionPopulationStats(countriesInArea).isEmpty());
    }

    @Test
    void GetRegionPopulationStatsTestNormal() {
        PopulationPercentages.GetRegionPopulationStats();
    }


    @Test
    void GetCountryPopulationStatsTestNull(Object GetCountryPopulationStats) {
        GetCountryPopulationStats();
    }

    @Test
    void GetCountryPopulationStatsTestEmpty() {
        ArrayList<Country> GetCountryPopulationStats = new ArrayList<>();
        GetCountryPopulationStats();
//        assertFalse(PopulationPercentages.GetCountryPopulationStats(GetCountryPopulationStats).isEmpty());
    }

//    @Test
//    void GetCountryPopulationStatsTestNormal()
//    {
//        PopulationPercentages.GetCountryPopulationStats(GetCountryPopulationStats(null));
//   }


    @Test
    void CalculateAreaPopulationsTestNull() {
        PopulationStatistics countryPopulationStatistics = CalculateCountryPopulationStats(null);
    }

//    @Test
//    void CalculateAreaPopulationsTestNormal() {
//        ArrayList<Country> country = new ArrayList<>();
//        CalculateAreaPopulations(country);
//        PopulationStatistics countryPopulationStatistics = CalculateCountryPopulationStats(country);
//    }
}


















