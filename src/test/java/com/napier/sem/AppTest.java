package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void getCapitalCityTestNull()
    {
        app.getCapitalCity(null, null);
    }

    @Test
    void getCapitalCityTestEmpty()
    {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        app.getCapitalCity(country, cities);
    }

    @Test
    void assignCapitalsAndCountriesTestNull()
    {
        app.assignCapitalsAndCountries(null, null);
    }

    @Test
    void assignCapitalsAndCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<City> cities = new ArrayList<City>();

        app.assignCapitalsAndCountries(countries, cities);
    }
}