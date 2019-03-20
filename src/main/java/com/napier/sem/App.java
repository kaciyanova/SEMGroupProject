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
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }
        ArrayList<Country> countries = a.getCountries();
        ArrayList<City> cities = a.getCities();
        ArrayList<Language> languages = a.getLanguages();

        // disconnect from database
        a.disconnect();

        a.assignCapitalsAndCountries(countries, cities);

        ArrayList<Country> countriess = countries;
        ArrayList<City> citiess = cities;
        for (int i = 0; i < 10; i++) {
            System.out.println("city on og country: " + countries.get(i).Capital.Name);
            System.out.println("city on maybe new country: " + countriess.get(i).Capital.Name);


            System.out.println("Country on og city: "+ cities.get(i).Country.Name);
            Country countryoncity = citiess.get(i).Country;
            if (countryoncity != null) {
                System.out.println("Country on maybe new city: " + citiess.get(i).Country.Name);

            } else {
                System.out.println("no country on city: " + citiess.get(i).Name);
            }

        }
        //TODO write some user input thing
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * connect to the MySQL database.
     */
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * disconnect from the MySQL database.
     */
    void disconnect()
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
    ArrayList<Country> getCountries()
    {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT Code, Name, Continent, Region,Population,Capital "
                            + "FROM country "
                            + "ORDER BY country.Population DESC ";
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
            System.out.println("Countries fetch successful");

            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Countries");
            return null;
        }
    }

    //gets list of all cities in db
    ArrayList<City> getCities()
    {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT ID, CountryCode, Name, District,Population "
                            + "FROM city "
                            + "ORDER BY city.Population DESC";
            ;

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
    ArrayList<Language> getLanguages()
    {
        try {
            // Create an SQL statement
            Statement query = con.createStatement();
            // Create string for SQL statement
            String selectString =
                    "SELECT CountryCode, Language, Percentage "
                            + "FROM countrylanguage "
                            + "ORDER BY countrylanguage.CountryCode ASC";
            ;

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
    public void assignCapitalsAndCountries(ArrayList<Country> countries, ArrayList<City> cities)
    {
        if (countries.isEmpty()||cities.isEmpty()){
            System.out.println("Cities or countries empty");
            return;
        }
        countries.forEach(country -> country.Capital = getCapitalCity(country, cities));
        cities.forEach(city -> city.Country = getCountry(city, countries));
    }

    //gets capital city of country
    City getCapitalCity(Country country, ArrayList<City> cities)
    {
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
    Country getCountry(City city , ArrayList<Country> countries)
    {
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
}