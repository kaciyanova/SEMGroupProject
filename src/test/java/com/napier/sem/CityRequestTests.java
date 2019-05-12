package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.CityRequests.*;

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

//    @test
//    void GetCitiesInAreaByPopulationTestNull();
//    {
//        GetCitiesInAreaByPopulation(null);
//    }

//    @test
//    void GetCitiesInAreaByPopulationTestEmpty();
//    {
//        ArrayList<City> citiesToReturn = new ArrayList<>();
//        citiesToReturn(City);
//    }

//    @test
//    void GetCitiesInAreaByPopulationTestNormal();
//    {
//         if (capitals) {
//            citiesToReturn = citiesToReturn.stream().filter((city) -> city.IsCapital).collect(Collectors.toList());
//    }

//    @test
//    void SendToWriterTestNull();
//    {
//        ArrayList<String[]> reports = new ArrayList<>();
//        ArrayList<String[]> filename = new ArrayList<>();
//        SendToWriter(filename, reports)
//    }

//    @test
//    void SendToWriterTestNormal();
//    {
//       ReportGenerator.writeToCSV(filename, reports)
//    }


//    @test
//    void getWorldPopulationTestNull();
//    {
//        getWorldPopulation(null);
//    }

//    @test
//    void getWorldPopulationTestEmpty();
//    {
//        ArrayList<City> requestedCities = new ArrayList<>();
//        requestedCities(City);
//    }

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

//    @test
//    void getContinentPopulationTestEmpty();
//    {
//        ArrayList<City> requestedCities = new ArrayList<>();
//        requestedCities(requestedCities, continent, capitals);
//    }

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

//    @test
//    void getRegionPopulationTestEmpty();
//    {
//        ArrayList<City> requestedCities = new ArrayList<>();
//        requestedCities(requestedCities, region, capitals);
//    }

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

//    @test
//    void getDistrictPopulationTestEmpty();
//    {
//        ArrayList<City> requestedCities = new ArrayList<>();
//        requestedCities(requestedCities, district, capitals);
//    }

//    @test
//    void getDistrictPopulationTestNormal();
//    {
//         SendToWriter(requestedCities, district, capitals);
//    }


}
