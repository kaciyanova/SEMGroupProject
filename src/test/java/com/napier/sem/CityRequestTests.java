package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.CityRequests.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CityRequestTests {
    //enum for area scopes
    public enum Scope
    {
        World,
        Continent,
        Region,
        District
    }

    @BeforeAll
    static void init()
    {

    }

    @Test
    void GetCitiesInAreaByPopulationTestEmpty() {
        ArrayList<City> citiesToReturn = new ArrayList<>();
//        citiesToReturn(null);
//        assertFalse(CityRequests.GetCitiesInAreaByPopulation().isEmpty());
    }

    @Test
    void SendToWriterTestNull() {ArrayList<String[]> reports = new ArrayList<>();
        ArrayList<String[]> filename = new ArrayList<>();
//        SendToWriter(filename, reports);

    }


//    @Test
//    void SendToWriterTestNormal();
//    {
//       ReportGenerator.writeToCSV(filename, reports)
//    }


//    @Test
//    void getWorldPopulationTestNull() {
//        //      assertFalse(getWorldPopulation(null));
//    }


    @Test
    void getWorldPopulationTestEmpty() {
        ArrayList<City> requestedCities = new ArrayList<>();
//        getWorldPopulation(requestedCities);

    }


//    @test
//    void getWorldPopulationTestNormal();
//    {
//      SendToWriter(requestedCities, "World", capitals);
//    }

//    @test
//    void getContinentPopulationTestNull();
//    {
//        getContinentPopulation(null);
//    }

    @Test
    void getContinentPopulationTestEmpty() {
        ArrayList<City> requestedCities = new ArrayList<>();
//        getContinentPopulation(requestedCities);

    }


//    @test
//    void getContinentPopulationTestNormal();
//    {
//        SendToWriter(requestedCities, continent, capitals);
//    }

//    @test
//    void getRegionPopulationTestNull();
//    {
//        getRegionPopulation(null);
//    }

    @Test
    void getRegionPopulationTestEmpty() {
        ArrayList<City> requestedCities = new ArrayList<>();
//        getRegionPopulation(requestedCities);

    }

//    @test
//    void getRegionPopulationTestNormal();
//    {
//         SendToWriter(requestedCities, region, capitals);
//    }

//    @test
//    void getDistrictPopulationTestNull();
//    {
//         getDistrictPopulation(null);
//    }

    @Test
    void getDistrictPopulationTestEmpty() {
        ArrayList<City> requestedCities = new ArrayList<>();
//        getDistrictPopulation(requestedCities)

    }

//    @test
//    void getDistrictPopulationTestNormal();
//    {
//         SendToWriter(requestedCities, district, capitals);
//    }


}
