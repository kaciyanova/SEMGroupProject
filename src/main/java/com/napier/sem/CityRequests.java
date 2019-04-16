package com.napier.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.napier.sem.App.cities;
import static com.napier.sem.ReportGenerator.GenerateCapitalReports;
import static com.napier.sem.ReportGenerator.GenerateCityReports;

class CityRequests
{
    //enum for area scopes
    public enum Scope
    {
        World,
        Continent,
        Region,
        District
    }

    //returns list of cities in given area sorted by population
    static List<City> GetCitiesInAreaByPopulation(Scope scope, String area, int numberOfCitiesToGet, boolean capitals)
    {
        //ensures cities are in order of population descending
        cities.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        List<City> citiesToReturn;

        switch (scope) {
            //checks if top n cities given is out of range, returns all in area if true or if number of cities to get is 0, same for sub-areas
            case World:
                if (cities.size() >= numberOfCitiesToGet && numberOfCitiesToGet != 0) {
                    citiesToReturn = cities.subList(0, numberOfCitiesToGet);
                } else {
                    citiesToReturn = cities;
                }
                break;

            case Continent:
                citiesToReturn = cities.stream()
                        .filter((city) -> city.Country.Continent.equals(area)).collect(Collectors.toList());

                if (citiesToReturn.isEmpty()) {
                    System.out.println("No cities found on " + area + ". Please enter a different continent.");
                    getContinentPopulation(numberOfCitiesToGet, capitals);
                }

                if (citiesToReturn.size() >= numberOfCitiesToGet && numberOfCitiesToGet != 0) {
                    citiesToReturn = citiesToReturn.subList(0, numberOfCitiesToGet);
                }

                break;

            case Region:
                citiesToReturn = cities.stream()
                        .filter((city) -> city.Country.Region.equals(area)).collect(Collectors.toList());

                if (citiesToReturn.isEmpty()) {
                    System.out.println("No cities found in " + area + ". Please enter a different region.");
                    getRegionPopulation(numberOfCitiesToGet, capitals);
                }

                if (citiesToReturn.size() >= numberOfCitiesToGet && numberOfCitiesToGet != 0) {
                    citiesToReturn = citiesToReturn.subList(0, numberOfCitiesToGet);
                }

                break;

            case District:
                citiesToReturn = cities.stream()
                        .filter((city) -> city.District.equals(area)).collect(Collectors.toList());

                if (citiesToReturn.isEmpty()) {
                    System.out.println("No cities found on " + area + ". Please enter a different district.");
                    getDistrictPopulation(numberOfCitiesToGet, capitals);
                }

                if (citiesToReturn.size() >= numberOfCitiesToGet && numberOfCitiesToGet != 0) {
                    citiesToReturn = citiesToReturn.subList(0, numberOfCitiesToGet);
                }
                break;

            default: {
                System.out.println("Unknown option entered, returning all cities");
                citiesToReturn = cities;
                break;
            }
        }

        //filters list of cities to only capitals
        if (capitals) {
            citiesToReturn = citiesToReturn.stream().filter((city) -> city.IsCapital).collect(Collectors.toList());
        }

        return citiesToReturn;
    }

    //sends city list to report writer and generates filename
    static void SendToWriter(List<City> requestedCities, String area, boolean capitals)
    {
        ArrayList<String[]> reports;
        String filename;
        if (capitals) {
            reports = GenerateCapitalReports(requestedCities);

            filename = "top" + requestedCities.size() + "CapitalCitiesIn" + area + ".csv";
        } else {
            reports = GenerateCityReports(requestedCities);

            filename = "top" + requestedCities.size() + "CitiesIn" + area + ".csv";
        }

        ReportGenerator.writeToCSV(filename, reports);
    }

    //Gets all or top n city reports in world and writes to csv file
    static void getWorldPopulation(Integer numberOfCitiesToGet, boolean capitals)
    {
             List<City> requestedCities = GetCitiesInAreaByPopulation(CityRequests.Scope.World, "", numberOfCitiesToGet, capitals);

        SendToWriter(requestedCities, "World", capitals);
    }

    //Gets all or top n city reports in a given continent and writes to csv file
    static void getContinentPopulation(Integer numberOfCitiesToGet, boolean capitals)
    {
        System.out.println("Please enter which continent you want to get report for: ");

        Scanner in = new Scanner(System.in);
        String continent = in.nextLine();

        List<City> requestedCities = GetCitiesInAreaByPopulation(CityRequests.Scope.Continent, continent, numberOfCitiesToGet, capitals);

        SendToWriter(requestedCities, continent, capitals);
    }

    //Gets all or top n city reports in a given region and writes to csv file
    static void getRegionPopulation(Integer numberOfCitiesToGet, boolean capitals)
    {
        System.out.println("Please enter which region you want to get report for: ");

        Scanner in = new Scanner(System.in);
        String region = in.nextLine();

        List<City> requestedCities = GetCitiesInAreaByPopulation(CityRequests.Scope.Region, region, numberOfCitiesToGet, capitals);

        SendToWriter(requestedCities, region, capitals);
    }

    //Gets all or top n city reports in a given district and writes to csv file
    static void getDistrictPopulation(Integer numberOfCitiesToGet, boolean capitals)
    {
        System.out.println("Please enter which district you want to get report for: ");

        Scanner in = new Scanner(System.in);
        String district = in.nextLine();

        List<City> requestedCities = GetCitiesInAreaByPopulation(Scope.District, district, numberOfCitiesToGet, capitals);

        SendToWriter(requestedCities, district, capitals);
    }
}
