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


}