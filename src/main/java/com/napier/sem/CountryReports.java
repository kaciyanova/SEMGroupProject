package com.napier.sem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CountryReports
{
    public enum Scope
    {
        World,
        Continent,
        Region
        Population
        capital
    }

    public ArrayList<Country> GetCountriesInAreaByPopulation(App a, ArrayList<Country> countries, Scope scope, String Area)
    {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        switch (scope) {
            case World:
                return countries;
            case Continent:
                return (ArrayList<Country>) countries.stream()
                        .filter((country) -> country.Continent == Area).collect(Collectors.toList());

            case Region:
                return (ArrayList<Country>) countries.stream()
                        .filter((country) -> country.Region == Area).collect(Collectors.toList());
            default: {
                return countries;
            }
        }
    }

}