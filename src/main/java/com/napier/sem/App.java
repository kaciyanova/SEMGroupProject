package com.napier.sem;

import com.opencsv.CSVWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static ArrayList<Country> countries;
    public static ArrayList<City> cities;
    public static ArrayList<Language> languages;

    public static void main(String[] args) {
        String cloudEndpoint = "35.242.172.149:3306";
        // Connect to database
        if (args.length < 1) {
//            connect(cloudEndpoint);
            connect("localhost:33060");
        } else {
            connect(args[0]);
        }

        countries = getCountries();
        cities = getCities();
        languages = getLanguages();
        // disconnect from database
        disconnect();

        assignCapitalsAndCountries(countries, cities);

        Scanner in = new Scanner(System.in);

        System.out.println("Enter how many countries to report");
        String topN = in.nextLine();

        getWorldPopulation(topN);

    }

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * connect to the MySQL database.
     */
    public static void connect(String location) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * disconnect from the MySQL database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    //gets list of all countries in db
    static ArrayList<Country> getCountries() {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT Code, Name, Continent, Region,Population,Capital "
                            + "FROM country "
                            + "ORDER BY country.Population DESC ";

            // Execute SQL statement
            ResultSet results = query.executeQuery(selectString);
            // Return new city if valid.
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();

            while (results.next()) {
                Country country = new Country();
                country.Code = results.getString("Code");
                country.Name = results.getString("Name");
                country.Continent = results.getString("Continent");
                country.Region = results.getString("Region");
                country.Population = results.getInt("Population");
                country.CapitalID = results.getInt("Capital");

                countries.add(country);
            }
            System.out.println("Countries fetch successful");

            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Countries");
            return null;
        }
    }

    //gets list of all cities in db
    static ArrayList<City> getCities() {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT ID, CountryCode, Name, District,Population "
                            + "FROM city "
                            + "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet results = query.executeQuery(selectString);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();

            while (results.next()) {
                City city = new City();
                city.ID = results.getInt("ID");
                city.Name = results.getString("Name");
                city.CountryCode = results.getString("CountryCode");
                city.District = results.getString("District");
                city.Population = results.getInt("Population");

                cities.add(city);

            }
            System.out.println("Cities fetch successful");

            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
            return null;
        }
    }

    //gets list of all cities in db
    static ArrayList<Language> getLanguages() {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT CountryCode, Language, Percentage "
                            + "FROM countrylanguage "
                            + "ORDER BY countrylanguage.CountryCode ASC";

            // Execute SQL statement
            ResultSet results = query.executeQuery(selectString);
            // Return new city if valid.
            // Check one is returned
            ArrayList<Language> languages = new ArrayList<Language>();

            while (results.next()) {
                Language language = new Language();
                language.Name = results.getString("Language");
                language.CountryCode = results.getString("CountryCode");
                language.Percentage = results.getFloat("Percentage");

                languages.add(language);
            }
            System.out.println("Languages fetch successful");

            return languages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get languages");
            return null;
        }
    }

    //Assigns capital cities to each country & vice versa
    public static void assignCapitalsAndCountries(ArrayList<Country> countries, ArrayList<City> cities) {
        if (countries == null || cities == null) {
            System.out.println("Cities and/or countries null");
            return;
        }
        countries.forEach(country -> country.Capital = getCapitalCity(country, cities));
        cities.forEach(city -> city.Country = getCountry(city, countries));
    }

    //gets capital city of country
    public static City getCapitalCity(Country country, ArrayList<City> cities) {
        if (country == null || cities == null) {
            System.out.println("City and/or countries null");
            return null;
        }

        try {
            City capital = cities.stream()
                    .filter((city) -> city.ID == country.CapitalID)
                    .findFirst()
                    .orElse(null);

            if (capital == null) {
                System.out.println("Capital city of " + country.Name + " not found");
            } else {
                System.out.println("Capital city of " + country.Name + " is " + country.Capital.Name);

            }
//TODO a few countries don't seem to have capital cities, like Antarctica which is included here as a country for reasons unknown

            return capital;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //gets capital city of country
    static Country getCountry(City city, ArrayList<Country> countries) {
        try {
            Country cityCountry = countries.stream()
                    .filter((country) -> country.Code == city.CountryCode)
                    .findFirst()
                    .orElse(null);

            if (cityCountry == null) {
                System.out.println("Country of " + city.Name + " not found");
            } else {
                System.out.println("Country of " + city.Name + " is " + city.Country.Name);

            }
//TODO a few countries don't seem to have capital cities, like Antarctica which is included here as a country for reasons unknown

            return cityCountry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //enum for area scopes
    public enum Scope {
        World,
        Continent,
        Region
    }

    //COUNTRY REPORTS

    //Gets all or top n country reports in world and writes to csv file
    public static void getWorldPopulation(String topN) {
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
        ArrayList<Country> requestedCountries = GetCountriesInAreaByPopulation(countries, Scope.World, "", numberOfCountriesToGet);

        ArrayList<String[]> reports = GenerateCountryReports(requestedCountries);

        String filepath = "top" + numberOfCountriesToGet + "CountriesInWorld.csv";

        writeToCSV(filepath, reports);
    }

    //returns list of countries in given area sorted by population
    public static ArrayList<Country> GetCountriesInAreaByPopulation(ArrayList<Country> countries, Scope scope, String Area, int n) {
        //ensures countries are in order of population descending
        countries.stream().sorted((c1, c2) -> c2.Population - (c1.Population));

        switch (scope) {
            case World:
                return countries;
            case Continent:
                return (ArrayList<Country>) countries.stream()
                        .filter((country) -> country.Continent == Area).collect(Collectors.toList());

            case Region:
                return (ArrayList<Country>) countries.stream()
                        .filter((country) -> country.Region == Area).collect(Collectors.toList());
            default: {
                return countries;
            }
        }
    }


    //Creates country report from given list of countries
    public static ArrayList<String[]> GenerateCountryReports(ArrayList<Country> requestedCountries) {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Country Code", "Name", "Continent", "Region", "Population", "Capital"});

        requestedCountries.forEach(country -> report.add(GenerateCountryReport(country)));

        return report;
    }

    //Creates country report from given list of cities
    public ArrayList<String[]> GenerateCityReports(ArrayList<City> cities) {
        ArrayList<String[]> report = new ArrayList<String[]>();
        //Report header
        report.add(new String[]{"Name", "Country", "Population"});

        cities.forEach(city -> report.add(GenerateCityReport(city)));

        return report;
    }

    //Creates report line for single country
    static String[] GenerateCountryReport(Country country) {
        try {
            return new String[]
                    {
                            country.Code,
                            country.Name,
                            country.Continent,
                            country.Region,
                            Integer.toString(country.Population),
                            country.Capital.Name};
        }
        catch (Exception ex){
            System.out.println(ex);
            return new String[]{};
        }

    }

    //Creates report line for single city
    static String[] GenerateCityReport(City city) {
        return new String[]
                {city.Name,
                        city.Country.Name,
                        Integer.toString(city.Population)
                };
    }


    //writes string array to CSV file
    public static void writeToCSV(String filePath, ArrayList<String[]> report) {

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