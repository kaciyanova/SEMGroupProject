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
    void testGetCountries()
    {
        ArrayList<Country> countries = app.getCountries();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    @Test
    void testGetCities()
    {
        ArrayList<City> cities = app.getCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

    }

    @Test
    void testGetLanguages()
    {
        ArrayList<Language> languages = app.getLanguages();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }
}