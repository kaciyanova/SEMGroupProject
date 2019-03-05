package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // connect to database
        a.connect();

        ArrayList<Country> countries = a.getCountries();
        ArrayList<City> cities = a.getCities();
        ArrayList<Language> languages = a.getLanguages();

        // disconnect from database
        a.disconnect();

        a.setCityCountry(countries, cities);
        a.setCapitalCities(countries, cities);

        //TODO write some user input thing

a.testReports(countries,cities);
    }


    public void testReports(ArrayList<Country> countries, ArrayList<City> cities){
Report.writeToCSV("~/countryreport.csv",Report.GenerateCountryReports(countries));
        Report.writeToCSV("~/cityreport.csv",Report.GenerateCityReports(cities));
    }


    //     Connection to MySQL database.
    private Connection con = null;

    // connect to the MySQL database.
    public void connect()
    {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
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
                // connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    //disconnect from the MySQL database.
    public void disconnect()
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
    public ArrayList<Country> getCountries()
    {
        System.out.println("Pulling countries from DB");
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT Code, Name, Continent, Region,Population,Capital "
                            + "FROM country "
                            + "ORDER BY country.Code ASC";
            ;

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
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Countries");
            return null;
        }
    }

    public ArrayList<City> getCities()
    {
        System.out.println("Pulling cities from DB");
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT ID, CountryCode, Name, Continent, District,Population "
                            + "FROM country "
                            + "ORDER BY country.Code ASC";
            ;

            // Execute SQL statement
            ResultSet results = query.executeQuery(selectString);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();

            while (results.next()) {
                City city = new City();
                city.ID = results.getInt("Code");
                city.Name = results.getString("Name");
                city.CountryCode = results.getString("CountryCode");
                city.District = results.getString("District");
                city.Population = results.getInt("Population");
                city.Capital = false;

                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
            return null;
        }
    }

    public ArrayList<Language> getLanguages()
    {
        System.out.println("Pulling languages from DB");
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT CountryCode, Name, Percentage "
                            + "FROM country "
                            + "ORDER BY country.Code ASC";
            ;

            // Execute SQL statement
            ResultSet results = query.executeQuery(selectString);
            // Return new city if valid.
            // Check one is returned
            ArrayList<Language> languages = new ArrayList<Language>();

            while (results.next()) {
                Language language = new Language();
                language.Name = results.getString("Name");
                language.CountryCode = results.getString("CountryCode");
                language.Percentage = results.getFloat("Population");

                languages.add(language);
            }
            return languages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get languages");
            return null;
        }
    }

    //Sets country on cities
    public void setCityCountry(ArrayList<Country> countries, ArrayList<City> cities)
    {
        cities.forEach(city -> city.Country = getCountry(city.CountryCode, countries));

    }

        public Country getCountry(String countryCode, ArrayList<Country> countries)
    {
        try {
            return countries.stream()
                    .filter((country) -> country.Code == countryCode)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Country not found"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Sets whether city is a capital or not
    public void setCapitalCities(ArrayList<Country> countries, ArrayList<City> cities)
    {
        //Checks and sets whether city is a capital
        cities.forEach(city -> city.Capital = countries.stream().anyMatch((country) -> country.CapitalID == city.ID));
    }
}