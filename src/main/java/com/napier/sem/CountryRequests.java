package com.napier.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.napier.sem.App.countries;
import static com.napier.sem.ReportGenerator.GenerateCountryReports;

public class CountryRequests
{
    //enum for area scopes
    public enum Scope
    {
        World,
        Continent,
        Region
    }

    //returns list of countries in given area sorted by population
    static List<Country> GetCountriesInAreaByPopulation(Scope scope, String Area, int n)
    {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        List<Country> countriesToReturn;

        switch (scope) {
            case World:
                return countries.subList(0, n);
            case Continent:
                //checks if top n countries given is out of range, returns all in area if true, same for region
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Continent == Area).collect(Collectors.toList());
                if (countriesToReturn.size() >= n) {
                    return countriesToReturn.subList(0, n);
                }
                System.out.println("Number of countries requested more than number of countries on continent; returning all countries on continent");
                return countriesToReturn;

            case Region:
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Region == Area).collect(Collectors.toList());
                if (countriesToReturn.size() >= n) {
                    return countriesToReturn.subList(0, n);
                }
                System.out.println("Number of countries requested more than number of countries in region; returning all countries in region");
                return countriesToReturn;
            default: {
                return countries;
            }
        }
    }

    //sends country list to report writer and generates filename
    static void SendToWriter(List<Country> requestedCountries, String area, int numberOfCountriesToGet)
    {
        ArrayList<String[]> reports = GenerateCountryReports(requestedCountries);

        String filename = "top" + numberOfCountriesToGet + "CountriesIn" + area.toString() + ".csv";

        ReportGenerator.writeToCSV(filename, reports);
    }

    //Gets all or top n country reports in world and writes to csv file
    static void getWorldPopulation(Integer numberOfCountriesToGet)
    {
        if (numberOfCountriesToGet == 0) {
            numberOfCountriesToGet = countries.size();
        }

        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.World, "", numberOfCountriesToGet);

        SendToWriter(requestedCountries, "World", numberOfCountriesToGet);
    }

    //Gets all or top n country reports in a given continent and writes to csv file
    static void getContinentPopulation(Integer numberOfCountriesToGet)
    {
        if (numberOfCountriesToGet == 0) {
            numberOfCountriesToGet = countries.size();
        }
//TODO get area input here
//        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.Continent, continent, numberOfCountriesToGet);

//        SendToWriter(requestedCountries, continent, numberOfCountriesToGet);
    }

    //Gets all or top n country reports in a given region and writes to csv file
    static void getRegionPopulation(Integer numberOfCountriesToGet)
    {
        if (numberOfCountriesToGet == 0) {
            numberOfCountriesToGet = countries.size();
        }

//        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.Region, region, numberOfCountriesToGet);

//        SendToWriter(requestedCountries, region, numberOfCountriesToGet);
    }
}
