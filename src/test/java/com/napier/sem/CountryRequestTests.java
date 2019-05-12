package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.CountryRequests.*;


public class CountryRequestTests {

    @BeforeAll
    static void init()
    {

    }
    //enum for area scopes
    public enum Scope
    {
        World,
        Continent,
        Region
    }
//    @Test
//    void GetCountriesInAreaByPopulationTestNull()
//    {
//        GetCountriesInAreaByPopulation(null);
//
//    }

//    @Test
//    void GetCountriesInAreaByPopulationTestEmpty()
//    {
//        ArrayList<Country> countries = new ArrayList<>();
//        GetCountriesInAreaByPopulation(countries);
//    }

//    @Test
//    void GetCountriesInAreaByPopulationTestNormal()
//    {
//        GetCountriesInAreaByPopulation(countries);
//    }

//    @Test
//    void SendToWriterTestNull()
//    {
//       ArrayList<String[]> reports = new ArrayList<>();
//       ArrayList<String[]> filename = new ArrayList<>();
//       SendToWriter(filename, reports)
//    }

//    @Test
//    void SendToWriterTestNormal()
//    {
//           ReportGenerator.writeToCSV(filename, reports)
//    }


//    @Test
//    void getWorldPopulationTestNull()
//    {
//        getWorldPopulation(null);
//    }

//    @Test
//    void getWorldPopulationTestEmpty()
//    {
//        ArrayList<Country> requestedCountries = new ArrayList<>();
//       getWorldPopulation(requestedCountries, World);
//    }

//    @Test
//    void getWorldPopulationTestNormal()
//    {
//        SendToWriter(requestedCountries, "World");
//    }

//    @Test
//    void getContinentPopulationTestNull()
//    {
//        getContinentPopulation(null);
//    }

//    @Test
//    void getContinentPopulationTestEmpty()
//    {
//        ArrayList<Country> requestedCountries = new ArrayList<>();
//       getContinentPopulation(requestedCountries, continent);
//    }

//    @Test
//    void getContinentPopulationTestNormal()
//    {
//       SendToWriter(requestedCountries, continent);
//    }

//    @Test
//    void getRegionPopulationTestNull()
//    {
//        getRegionPopulation(null);
//    }

//    @Test
//    void getRegionPopulationTestEmpty()
//    {
//        ArrayList<Country> requestedCountries = new ArrayList<>();
//       getContinentPopulation(requestedCountries, region);
//    }

//    @Test
//    void getRegionPopulationTestNormal()
//    {
//       SendToWriter(requestedCountries, region);
//    }
}
