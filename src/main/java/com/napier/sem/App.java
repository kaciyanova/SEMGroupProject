package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static ArrayList<Country> countries;
    public static ArrayList<City> cities;
    public static ArrayList<Language> languages;

    public static void main(String[] args)
    {
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

        InputController.RequestReport();
    }


    //      Connection to MySQL database.
    private static Connection con = null;

    //     connect to the MySQL database.
    public static void connect(String location)
    {
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

    //      disconnect from the MySQL database.
    public static void disconnect()
    {
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
    static ArrayList<Country> getCountries()
    {
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
    static ArrayList<City> getCities()
    {
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
    static ArrayList<Language> getLanguages()
    {
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
    public static void assignCapitalsAndCountries(ArrayList<Country> countries, ArrayList<City> cities)
    {
        if (countries == null || cities == null) {
            System.out.println("Cities and/or countries null");
            return;
        }
        countries.forEach(country -> country.setCapital(getCapitalCity(country, cities)));
        cities.forEach(city -> city.setCountry(getCountry(city, countries)));
    }

    //gets capital city of country
    public static City getCapitalCity(Country country, ArrayList<City> cities)
    {
        City capital;
        try {
            if (country == null || cities == null) {
                System.out.println("City and/or countries null");
                return null;
            }

            capital = cities.stream()
                    .filter((city) -> city.ID == country.CapitalID)
                    .findFirst()
                    .orElse(null);

            return capital;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //gets capital city of country
    static Country getCountry(City city, ArrayList<Country> countries)
    {
        Country cityCountry;
        try {
            cityCountry = countries.stream()
                    .filter((country) -> country.Code.equals(city.CountryCode))
                    .findFirst()
                    .orElse(null);

            return cityCountry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}