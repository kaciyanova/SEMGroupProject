package com.napier.sem;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator
{
    //Creates country report from given list of countries
    public static ArrayList<String[]> GenerateCountryReports(List<Country> requestedCountries)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Country Code", "Name", "Continent", "Region", "Population", "Capital"});

        requestedCountries.forEach(country -> report.add(GenerateCountryReport(country)));

        return report;
    }

    //Creates country report from given list of cities
    public ArrayList<String[]> GenerateCityReports(List<City> cities)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Name", "Country", "Population"});

        cities.forEach(city -> report.add(GenerateCityReport(city)));

        return report;
    }

    //Creates report line for single country
    static String[] GenerateCountryReport(Country country)
    {
        try {
            String capitalName;

            if (country.Capital == null) {
                capitalName = "N/A";
            } else {
                capitalName = country.Capital.Name;
            }

            return new String[]
                    {
                            country.Code,
                            country.Name,
                            country.Continent,
                            country.Region,
                            Integer.toString(country.Population),
                            capitalName};
        } catch (Exception ex) {
            System.out.println(ex);
            return new String[]{};
        }

    }

    //Creates report line for single city
    static String[] GenerateCityReport(City city)
    {
        return new String[]
                {city.Name,
                        city.Country.Name,
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

            System.out.println("Printed to report: "+filePath);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
