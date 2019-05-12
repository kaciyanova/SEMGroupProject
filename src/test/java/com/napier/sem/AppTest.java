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
        getCapitalCity(null);
    }

    @Test
    void getCapitalCityTestEmpty()
    {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        getCapitalCity(country);
    }
//
//    @Test
//    void assignCapitalsAndCountriesTestNull()
//    {
//        assignCapitalsAndCountries(null, null);
//    }

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

//    @Test
//    void assignLanguagesToCountriesTestEmpty()
//    {
//        ArrayList<Country> countries = new ArrayList<Country>();
//        ArrayList<Language> languages = new ArrayList<Language>();
//
//        assignLanguagesToCountries(countries);
//    }


//    @Test
//    void assignLanguagesToCountriesTestNull()
//    {
//        assignLanguagesToCountries(null);
//    }

//    @Test
//    void getCountryTestEmpty()
//    {
//       getCountry();
//    }

//    @Test
//    void getCountryTestNull()
//    {
//        getCountry(null);
//    }

//   @Test
//   void getCountryTestInvalidString()
//  {
//        String notString = "123456";
//        assertThrows(RuntimeException.class, () -> {
//            getCountry(notString);
//        });
//    }

//    @Test
//    void getCountryTestNormal()
//    {
//        getCountry(null);
//    }

//    @Test
//    void getLanguagesTestEmpty()
//    {
//        getLanguages();
//   }
//
//    @Test
//    void getLanguagesTestNull()
//    {
//        getLanguages(null);
//   }

//    @Test
//    void getLanguagesTestInvalidString()
//    {
//        String notString = "123456";
//        assertThrows(RuntimeException.class, () -> {
//            getLanguages(notString);
//        });
//    }

//    @Test
//    void getLanguagesTestNormal()
//    {
//        getLanguages();
//    }


}