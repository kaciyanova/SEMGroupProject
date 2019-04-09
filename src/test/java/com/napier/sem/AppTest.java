package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    @BeforeAll
    static void init() {

    }

    @Test
    static void getCapitalCityTestNull() {
        App.getCapitalCity(null, null);
    }

    @Test
    static void getCapitalCityTestEmpty() {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        App.getCapitalCity(country, cities);
    }

    @Test
    static void assignCapitalsAndCountriesTestNull() {
        App.assignCapitalsAndCountries(null, null);
    }

    @Test
    static void assignCapitalsAndCountriesTestEmpty() {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<City> cities = new ArrayList<City>();

        App.assignCapitalsAndCountries(countries, cities);
    }

    @Test
    static void WorldPopulationTestEmpty() {
        CountryReports.WorldPopulation("");
    }

    @Test
    static void WorldPopulationTestNull(){
        CountryReports.WorldPopulation(null);
    }

    @Test
    static void WorldPopulationTestInvalidString(){
        String notInt="dkfjgh";
        assertThrows(RuntimeException.class, () -> {
            CountryReports.WorldPopulation(notInt);
        });
    }

    @Test
    static void WorldPopulationTestNormal(){
        CountryReports.WorldPopulation("5");
    }
}