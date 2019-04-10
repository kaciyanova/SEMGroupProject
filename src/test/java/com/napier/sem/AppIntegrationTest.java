package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{

    @BeforeAll
    static void init()
    {
        App.connect("localhost:33060");
    }

    @Test
    static void getCountriesTestEmpty()
    {
        assertFalse(App.getCountries().isEmpty());
    }

    @Test
    static void getCountriesTestNull()
    {
        assertNotNull(App.getCountries());
    }

    @Test
    static void getCitiesTestNull()
    {
        assertNotNull(App.getCities());
    }

    @Test
    static void getCitiesTestEmpty()
    {
        assertFalse(App.getCities().isEmpty());
    }

    @Test
    static void getLanguagesTestEmpty()
    {
        assertFalse(App.getLanguages().isEmpty());
    }

    @Test
    static void getLanguagesTestNull()
    {
        assertNotNull(App.getLanguages());
    }


}