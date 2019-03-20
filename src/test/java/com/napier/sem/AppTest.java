package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
//        ArrayList<Country> countries = app.getCountries();
//        ArrayList<City> cities = app.getCities();
//        ArrayList<Language> languages = app.getLanguages();
    }

    @Test
    void GenerateCityReportTestNull()
    {
        app.assignCapitalsAndCountries(null,null);
    }

    @Test
    void GenerateCityReportTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        ArrayList<City> cities = new ArrayList<City>();

        app.assignCapitalsAndCountries(countries,cities);
    }
}