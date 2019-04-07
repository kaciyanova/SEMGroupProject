package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest
{

    @BeforeAll
    static void init()
    {

    }

    @Test
    void getCapitalCityTestNull()
    {
        App.getCapitalCity(null, null);
    }

    @Test
    void getCapitalCityTestEmpty()
    {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        App.getCapitalCity(country, cities);
    }

    @Test
    void assignCapitalsAndCountriesTestNull()
    {
        App.assignCapitalsAndCountries(null, null);
    }

    @Test
    void assignCapitalsAndCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<City> cities = new ArrayList<City>();

        App.assignCapitalsAndCountries(countries, cities);
    }
}