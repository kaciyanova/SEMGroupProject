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
    }

    public ArrayList<Country> GetCountriesInAreaByPopulation(App a, ArrayList<Country> countries, Scope scope, String Area)
    {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        switch (scope) {
            case World:
                return countries;
            case Continent:
                return countries.stream()
                        .filter((country) -> country.Continent == Area).collect(Collectors.toCollection(Collectors.toList()));

                break;
            case Region:
                break;
            default: {
                return countries;
            }
        }


//        public void whenSortingEntitiesByName_thenCorrectlySorted ()
//        {
//            List<Human> humans = Lists.newArrayList(
//                    new Human("Sarah", 10),
//                    new Human("Jack", 12)
//            );
//
//            humans.sort(
//                    (Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
//
//            assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
//        }


    }