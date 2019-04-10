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
     void getCountriesTestEmpty()
    {
        assertFalse(App.getCountries().isEmpty());
    }

    @Test
     void getCountriesTestNull()
    {
        assertNotNull(App.getCountries());
    }

    @Test
     void getCitiesTestNull()
    {
        assertNotNull(App.getCities());
    }

    @Test
     void getCitiesTestEmpty()
    {
        assertFalse(App.getCities().isEmpty());
    }

    @Test
     void getLanguagesTestEmpty()
    {
        assertFalse(App.getLanguages().isEmpty());
    }

    @Test
     void getLanguagesTestNull()
    {
        assertNotNull(App.getLanguages());
    }


}