package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.App.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    public static ArrayList<Country> countries;
    public static ArrayList<City> cities;
    public static ArrayList<Language> languages;

    @BeforeAll
    static void init() {

        countries = getCountries();
        cities = getCities();
        languages = getLanguages();
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

//    @Test
//    static void getWorldPopulationTestEmpty() {
//        App.getWorldPopulation("");
//    }
//
//    @Test
//    static void getWorldPopulationTestNull(){
//        App.getWorldPopulation(null);
//    }
//
//    @Test
//    static void getWorldPopulationTestInvalidString(){
//        String notInt="dkfjgh";
//        assertThrows(RuntimeException.class, () -> {
//            App.getWorldPopulation(notInt);
//        });
//    }
//
//    @Test
//    static void getWorldPopulationTestNormal(){
//        App.getWorldPopulation("5");
//    }
}