package com.napier.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    static List<Country> GetCountriesInAreaByPopulation(Scope scope, String area, int numberOfCountriesToGet)
    {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        List<Country> countriesToReturn;

        switch (scope) {
            //checks if top n countries given is out of range, returns all in area if true or if number of countries to get is 0, same for sub-areas
            case World:
                if (countries.size() >= numberOfCountriesToGet && numberOfCountriesToGet != 0) {
                    return countries.subList(0, numberOfCountriesToGet);
                }
                return countries;

                case Continent:
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Continent.equals(area)).collect(Collectors.toList());

                if (countriesToReturn.isEmpty()) {
                    System.out.println("No countries found in " + area + ". Please enter a different area.\n");
                    getContinentPopulation(numberOfCountriesToGet);
                }

                if (countriesToReturn.size() >= numberOfCountriesToGet && numberOfCountriesToGet != 0) {
                    return countriesToReturn.subList(0, numberOfCountriesToGet);
                }
                return countriesToReturn;

            case Region:
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Region.equals(area)).collect(Collectors.toList());

                if (countriesToReturn.isEmpty()) {
                    System.out.println("No countries found in " + area + ". Please enter a different area.\n");
                    getRegionPopulation(numberOfCountriesToGet);
                }

                if (countriesToReturn.size() >= numberOfCountriesToGet && numberOfCountriesToGet != 0) {
                    return countriesToReturn.subList(0, numberOfCountriesToGet);
                }
                return countriesToReturn;

            default: {
                System.out.println("Unknown option entered, returning all countries");
                return countries;
            }
        }
    }

    //sends country list to report writer and generates filename
    static void SendToWriter(List<Country> requestedCountries, String area)
    {
        ArrayList<String[]> reports = GenerateCountryReports(requestedCountries);

               String filename = "top" + requestedCountries.size() + "CountriesIn" + area + ".csv";

        ReportGenerator.writeToCSV(filename, reports);
    }

    //Gets all or top n country reports in world and writes to csv file
    static void getWorldPopulation(Integer numberOfCountriesToGet)
    {

        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.World, "", numberOfCountriesToGet);

        SendToWriter(requestedCountries, "World");
    }

    //Gets all or top n country reports in a given continent and writes to csv file
    static void getContinentPopulation(Integer numberOfCountriesToGet)
    {
        System.out.println("Please enter which continent you want to get report for: \n");

        Scanner in = new Scanner(System.in);
        String continent = in.nextLine();

        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.Continent, continent, numberOfCountriesToGet);

        SendToWriter(requestedCountries, continent);
    }

    //Gets all or top n country reports in a given region and writes to csv file
    static void getRegionPopulation(Integer numberOfCountriesToGet)
    {
        System.out.println("Please enter which region you want to get report for: \n");

        Scanner in = new Scanner(System.in);
        String region = in.nextLine();

        List<Country> requestedCountries = GetCountriesInAreaByPopulation(Scope.Region, region, numberOfCountriesToGet);

        SendToWriter(requestedCountries, region);
    }
}
