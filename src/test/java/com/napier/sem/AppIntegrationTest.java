package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void assignCapitalsAndCountriesTestNull()
    {
        app.assignCapitalsAndCountries(null,null);
    }

    @Test
    void assignCapitalsAndCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<City> cities = new ArrayList<City>();

        app.assignCapitalsAndCountries(countries,cities);
    }

    @Test
    void getCountriesTestEmpty()
    {
        assertFalse(app.getCountries().isEmpty());
    }

    @Test
    void getCountriesTestNull()
    {
        assertNotNull(app.getCountries());
    }
    @Test
    void getCitiesTestNull()
    {
        assertNotNull(app.getCities());
    }

    @Test
    void getCitiesTestEmpty()
    {
                assertFalse(app.getCities().isEmpty());
    }
    @Test
    void getLanguagesTestEmpty()
    {
        assertFalse(app.getLanguages().isEmpty());
    }
    @Test
    void getLanguagesTestNull()
    {
        assertNotNull(app.getLanguages());
    }
//do all methods need to be public Just so they can be tested??? like this one is called by a public method and nowhere else
    //does each class need its own testing file like AppIntegrationTest ReportIntegrationTest etc
    @Test
    void getCapitalCityTestNull()
    {
        app.getCapitalCity(null,null);
    }

//    @Test
//    void getCapitalCityTestEmpty()
//    {
//        Country country = new Country();
//        ArrayList<City> cities = new ArrayList<City>();
//        app.getCapitalCity(country,cities);
//    }
}