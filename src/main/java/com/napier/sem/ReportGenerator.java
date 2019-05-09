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
    static ArrayList<String[]> GenerateCountryReports(List<Country> requestedCountries)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Country Code", "Name", "Continent", "Region", "Population", "Capital"});

        requestedCountries.forEach(country -> report.add(GenerateCountryReport(country)));

        return report;
    }

    //Creates country report from given list of cities
    static ArrayList<String[]> GenerateCityReports(List<City> requestedCities)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Name", "Country", "District", "Population"});

        requestedCities.forEach(city -> report.add(GenerateCityReport(city)));

        return report;
    }

    //Creates country report from given list of cities
    static ArrayList<String[]> GenerateCapitalReports(List<City> requestedCities)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Name", "Country", "Population"});

        requestedCities.forEach(city -> report.add(GenerateCityReport(city)));

        return report;
    }

    //Creates language report from given list of languages
    static ArrayList<String[]> GenerateLanguageReports(List<LanguageSpeakers> requestedLanguages)
    {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //ReportGenerator header
        report.add(new String[]{"Language", "Number of speakers", "Percentage of World Population"});

        requestedLanguages.forEach(language -> report.add(GenerateLanguageReport(language)));

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
                        city.District,
                        Integer.toString(city.Population)
                };
    }

    //TODO UHH LOL FORGOT THIS
    //Creates report line for single capital city
    static String[] GenerateCapitalReport(City city)
    {
        return new String[]
                {city.Name,
                        city.Country.Name,
                        Integer.toString(city.Population)
                };
    }

    //Creates report line for single language city
    static String[] GenerateLanguageReport(LanguageSpeakers languageSpeakers)
    {
        return new String[]
                {languageSpeakers.LanguageName,
                        Long.toString(languageSpeakers.Speakers),
                        Float.toString(languageSpeakers.WorldPercentage)
                };
    }


    //writes string array to CSV file
    static void writeToCSV(String filePath, ArrayList<String[]> report)
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

            System.out.println("Printed to report: " + filePath);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
