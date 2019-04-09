package com.napier.sem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class CountryReports
{
    ArrayList<Country> countries;

    public enum Scope
    {
        World,
        Continent,
        Region
    }

    @RequestMapping("world")
    public void WorldPopulation(@RequestParam(value="topN") String topN){
//        try{
        if (topN==null|| toString().isEmpty()){
            GetCountriesInAreaByPopulation(countries,Scope.World,"",250);
        }
        else {
            GetCountriesInAreaByPopulation(countries,Scope.World,"",Integer.parseInt(topN));
        }
    }

    public ArrayList<Country> GetCountriesInAreaByPopulation( ArrayList<Country> countries, Scope scope, String Area, int topN)
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