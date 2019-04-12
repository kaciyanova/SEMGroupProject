package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.App.*;
import static com.napier.sem.CountryRequests.getWorldPopulation;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{

    @BeforeAll
    static void init()
    {

    }

    @Test
    void getCapitalCityTestNull()
    {
        getCapitalCity(null, null);
    }

    @Test
    void getCapitalCityTestEmpty()
    {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        getCapitalCity(country, cities);
    }

    @Test
    void assignCapitalsAndCountriesTestNull()
    {
        assignCapitalsAndCountries(null, null);
    }

//    @Test
//    void assignCapitalsAndCountriesTestEmpty()
//    {
//        ArrayList<Country> countries = new ArrayList<Country>();
//        ArrayList<City> cities = new ArrayList<City>();
//
//        assignCapitalsAndCountries(countries, cities);
//    }

//    @Test
//    void getWorldPopulationTestEmpty()
//    {
//        getWorldPopulation("");
//    }
//
//    @Test
//    void getWorldPopulationTestNull()
//    {
//        getWorldPopulation(null);
//    }

//    @Test
//    void getWorldPopulationTestInvalidString()
//    {
//        String notInt = "dkfjgh";
//        assertThrows(RuntimeException.class, () -> {
//            getWorldPopulation(notInt);
//        });
//    }

//    @Test
//    void getWorldPopulationTestNormal()
//    {
//        getWorldPopulation("5");
//    }
}