package com.napier.sem;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Report
{
    //Creates country report from given list of countries
    public ArrayList<String[]> GenerateCountryReports(ArrayList<Country> countries, ArrayList<City> cities)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Country Code", "Name", "Continent", "Region", "Population", "Capital"});

        countries.forEach(country -> report.add(GenerateCountryReport(country, cities)));

        return report;
    }

    //Creates country report from given list of cities
    public ArrayList<String[]> GenerateCityReports(ArrayList<City> cities, ArrayList<Country> countries)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Name", "Country", "Population"});

        cities.forEach(city -> report.add(GenerateCityReport(city, countries)));

        return report;
    }

    //Creates report line for single country
    String[] GenerateCountryReport(Country country, ArrayList<City> cities)
    {
        String capitalName = "";
        try {
            City capital = cities.stream()
                    .filter((city) -> city.ID == country.CapitalID)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Capital city  not found"));

            capitalName = capital.Name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]
                {
                        country.Code,
                        country.Name,
                        country.Continent,
                        country.Region,
                        Integer.toString(country.Population),
                        capitalName};
    }

    //Creates report line for single city
    String[] GenerateCityReport(City city, ArrayList<Country> countries)
    {
        String countryName = "";
        try {
            Country cityCountry = countries.stream()
                    .filter((country) -> country.Code == city.CountryCode)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Country not found"));

            countryName = cityCountry.Name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]
                {city.Name,
                        countryName,
                        Integer.toString(city.Population)
                };
    }


    //writes string array to CSV file
    public static void writeToCSV(String filePath, ArrayList<String[]> report)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array

            writer.writeAll(report);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


