package com.napier.sem;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Report
{
    //Creates country report from given list of countries
    public static ArrayList<String[]> GenerateCountryReports(ArrayList<Country> countries, ArrayList<City> cities)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Country Code", "Name", "Continent", "Region", "Population", "Capital"});

        countries.forEach(country -> report.add(GenerateCountryReport(country)));

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
    static String[] GenerateCountryReport(Country country)
    {
        return new String[]
                {
                        country.Code,
                        country.Name,
                        country.Continent,
                        country.Region,
                        Integer.toString(country.Population),
                        country.Capital.Name};
    }

    //Creates report line for single city
    static String[] GenerateCityReport(City city, ArrayList<Country> countries)
    {
              return new String[]
                {city.Name,
                        city.Country.Name,
                        Integer.toString(city.Population)
                };
    }


    //Creates report line for single population (In progress)
    static String[] GeneratePopulationsReport(Populations populations)
    {
        return new String[]
                {
                        populations.Name,
                        populations.continent,
                        populations.Region,
                        populations.Capital,
                        populations.Rural,
                        populations.Country,
                        Integer.toString(populations.CityPopulation),
                        Float.toString(populations.PopulationPercentage)
                };


    }

    //Creating population report from giving list (In progress)
    public static ArrayList<String[]> GeneratePopulationReports(ArrayList<Populations> population)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Name", "Country", "Population%", "Continent", "Region", "Rural", "City Population"});

        population.forEach(populations -> report.add(GeneratePopulationsReport(populations)));

        return report;
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


