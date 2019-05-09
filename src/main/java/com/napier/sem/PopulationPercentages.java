package com.napier.sem;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.napier.sem.App.cities;
import static com.napier.sem.App.countries;

//Handles population percentage reports (all,urban, and rural populations)
public class PopulationPercentages
{
     //Gets+prints continent population statistics & sends to report printer
    static void GetContinentPopulationStats()
    {
        System.out.println("Please enter which continent you want to get population statistics for: \n");

        Scanner in = new Scanner(System.in);
        String continent = in.nextLine();

        List<Country> countriesInArea = PopulationCount.GetCountriesInArea(PopulationCount.Scope.Continent, continent);

        if (countriesInArea.isEmpty()) {
            System.out.println("No countries found in " + continent + ". Please enter a different continent.\n");
            GetContinentPopulationStats();
        }

        PopulationStatistics continentPopulationStats = new PopulationStatistics();
        continentPopulationStats.Area = continent;

        ReportGenerator.GeneratePopulationReport(CalculateAreaPopulations(countriesInArea,continentPopulationStats));

    }

    //Gets region population statistics & sends to report printer
    static void GetRegionPopulationStats()
    {
        System.out.println("Please enter which region you want to get population statistics for: \n");

        Scanner in = new Scanner(System.in);
        String region = in.nextLine();

        List<Country> countriesInArea = PopulationCount.GetCountriesInArea(PopulationCount.Scope.Region, region);

        if (countriesInArea.isEmpty()) {
            System.out.println("No countries found in " + region + ". Please enter a different region.\n");
            GetRegionPopulationStats();
        }

        PopulationStatistics regionPopulationStats = new PopulationStatistics();
        regionPopulationStats.Area = region;

        ReportGenerator.GeneratePopulationReport(CalculateAreaPopulations(countriesInArea,regionPopulationStats));
    }

    //Gets country population statistics & sends to report printer
    static void GetCountryPopulationStats()
    {
        System.out.println("Please enter which country you want to get population statistics for: \n");

        Scanner in = new Scanner(System.in);
        String countryName = in.nextLine();

        Country requestedCountry = countries.stream().filter((country) -> country.Name.equals(countryName)).findFirst().orElse(null);

        if (requestedCountry == null) {
            System.out.println("No country found with name " + countryName + ". Please enter a different country.\n");
            GetCountryPopulationStats();
        }

        PopulationStatistics populationStatistics = CalculateCountryPopulationStats(requestedCountry);

        ReportGenerator.GeneratePopulationReport(populationStatistics);
    }

    //Sums all population stats in area
    static PopulationStatistics CalculateAreaPopulations(List<Country> countriesInArea, PopulationStatistics areaPopulationStats)
    {
        for (Country country : countriesInArea) {
            PopulationStatistics countryPopulationStatistics = CalculateCountryPopulationStats(country);

            areaPopulationStats.TotalPopulation = areaPopulationStats.TotalPopulation + countryPopulationStatistics.TotalPopulation;
            areaPopulationStats.UrbanPopulation = areaPopulationStats.UrbanPopulation + countryPopulationStatistics.UrbanPopulation;
            areaPopulationStats.RuralPopulation = areaPopulationStats.RuralPopulation + countryPopulationStatistics.RuralPopulation;
        }
        return areaPopulationStats;
    }

    //Calculates population stats for given country
    static PopulationStatistics CalculateCountryPopulationStats(Country country)
    {
        PopulationStatistics populationStatistics = new PopulationStatistics();

        populationStatistics.Area = country.Name;

        populationStatistics.UrbanPopulation = GetUrbanPopulation(country);
        populationStatistics.TotalPopulation = country.Population;
        populationStatistics.RuralPopulation = populationStatistics.TotalPopulation - populationStatistics.UrbanPopulation;

        return populationStatistics;
    }

    //Calculates the urban population of a country
    static long GetUrbanPopulation(Country country)
    {
        List<City> citiesInCountry = cities.stream().filter((city) -> city.Country == country).collect(Collectors.toList());

        long urbanPopulation = 0;

        for (City city : citiesInCountry) {
            urbanPopulation = urbanPopulation + city.Population;
        }

        return urbanPopulation;
    }
}