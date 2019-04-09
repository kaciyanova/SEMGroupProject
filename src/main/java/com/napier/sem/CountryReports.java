package com.napier.sem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class CountryReports {

    static ArrayList<Country> countries;

    public enum Scope {
        World,
        Continent,
        Region
    }

    @RequestMapping("world")
    public static void WorldPopulation(@RequestParam(value = "topN", defaultValue = "250") String topNStr) {
        int topN;
        try {
            topN = Integer.parseInt(topNStr);
        } catch (Exception ex) {
            System.out.println("String cannot be converted to int");
            topN = 250;
        }
        GetCountriesInAreaByPopulation(countries, Scope.World, "", topN);
    }

    public static ArrayList<Country> GetCountriesInAreaByPopulation(ArrayList<Country> countries, Scope scope, String Area, int topN) {
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