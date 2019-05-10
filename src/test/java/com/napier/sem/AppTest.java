package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.App.*;
import static com.napier.sem.CountryRequests.getWorldPopulation;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @BeforeAll
    static void init() {

    }

    @Test
    void getCapitalCityTestNull() {
        getCapitalCity(null);
    }

    @Test
    void getCapitalCityTestEmpty() {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        getCapitalCity(country);
    }

    @Test
    void assignCapitalsToCountriesTestNull() {
        assignCapitalsToCountries(null);
    }

//     @Test
//     void assignCapitalsToCountriesTestEmpty() {
//         ArrayList<Country> countries = new ArrayList<Country>();
//         ArrayList<City> cities = new ArrayList<City>();
//
//         assignCapitalsToCountries(App.cities);
//     }

    @Test
    void getWorldPopulationTestEmpty() {
        getWorldPopulation(0);
    }

    @Test
    void getWorldPopulationTestNull() {
        getWorldPopulation(null);
    }

//    @Test
//    void getWorldPopulationTestInvalidString()
//    {
//        String notInt = "dkfjgh";
//        assertThrows(RuntimeException.class, () -> {
//            getWorldPopulation(notInt);
//        });
//    }

    @Test
    static void WorldPopulationTestNormal() {
        getWorldPopulation(5);
    }


    @Test
    void assignLanguagesToCountriesTestEmpty() {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<Language> languages = new ArrayList<Language>();

        assignLanguagesToCountries(countries, languages);
    }


    @Test
    void assignLanguagesToCountriesTestNull() {
        assignLanguagesToCountries(null, null);
    }

    //   @Test
//    void getCountryTestEmpty()
//    {
//        getCountry();
//    }

    @Test
    void getCountryTestNull() {
        getCountry(null);
    }


//   @Test
//   void getCountryTestInvalidString()
//  {
//        String notString = "123456";
//        assertThrows(RuntimeException.class, () -> {
//            getCountry(notString);
//        });
//  }


//    @Test
//    void getCountryTestNormal()
//    {
//        getCountry();
//    }

//     @Test
//     void getLanguagesTestEmpty()
//     {
//
//        getLanguages("");
//     }


//    @Test
//    void getLanguagesTestNull()
//     {
//         getLanguages((String) null);
//     }

//     @Test
//     void getLanguagesTestInvalidString()
//    {
//        String notString = "123456";
//        assertThrows(RuntimeException.class, () -> {
//             getLanguages(notString);
//         });
//     }

//     @Test
//     void getLanguagesTestNormal()
//     {
//        getLanguages("English");
//     }
 }