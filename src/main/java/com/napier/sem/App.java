package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.Connect();
        // Get City
//        City city = a.GetCity(1);
        // Display results
//        a.DisplayCity(city);

        // Disconnect from database
        a.Disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void Connect()
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
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to Connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void Disconnect()
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

    public void GetWorldInfo()
    {
        //pulling all from db since amount of data is small
    }

    //gets list of all countries in db
    public List<Country> GetCountries()
    {
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
                country.Capital=results.getInt("Capital");

                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Countries");
            return null;
        }
    }

    public ArrayList<City> GetCities()
    {
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

                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
            return null;
        }
    }

    public ArrayList<Language> GetLanguages()
    {
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


    public void DisplayCity(City city)
    {
        if (city != null) {
            System.out.println(
                    city.ID + " "
                    //  + city.Name + " "
                    //  + "Country code:" + city.CountryCode + "\n"
                    //  + "District:" + city.District + "\n"
                    // + "Population:" +city.Population + "\n"
            );
        }
    }
}