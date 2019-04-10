package com.napier.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.napier.sem.App.countries;
import static com.napier.sem.Report.GenerateCountryReports;

public class CountryRequests
{
    //enum for area scopes
    public enum Scope
    {
        World,
        Continent,
        Region
    }
    //Gets all or top n country reports in world and writes to csv file
    public static void getWorldPopulation(String topN)
    {
        int numberOfCountriesToGet;
        if (topN == null || topN == "all" || topN == "") {
            numberOfCountriesToGet = 250;
        } else {
            try {
                numberOfCountriesToGet = Integer.parseInt(topN);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Invalid number " + topN + " provided, printing all countries in scope");
                numberOfCountriesToGet = 250;
            }
        }
        List<Country> requestedCountries = GetCountriesInAreaByPopulation(countries, Scope.World, "", numberOfCountriesToGet);

        ArrayList<String[]> reports = GenerateCountryReports(requestedCountries);

        String filepath = "top" + numberOfCountriesToGet + "CountriesInWorld.csv";

        Report.writeToCSV(filepath, reports);
    }

    //returns list of countries in given area sorted by population
    public static List<Country> GetCountriesInAreaByPopulation(ArrayList<Country> countries, Scope scope, String Area, int n)
    {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        switch (scope) {
            case World:
                return countries.subList(0, n);
            case Continent:
                return countries.stream()
                        .filter((country) -> country.Continent == Area).collect(Collectors.toList());

            case Region:
                return countries.stream()
                        .filter((country) -> country.Region == Area).collect(Collectors.toList());
            default: {
                return countries;
            }
        }
    }
}
