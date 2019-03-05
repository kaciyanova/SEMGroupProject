package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A city
 */
public class City
{
    /**
     * City number
     */
    public int ID;

    /**
     * City name
     */
    public String Name;

    /**
     * City's Country Code
     */
    public String CountryCode;

    //City's country
    public Country Country;

    /**
     * City's District
     */
    public String District;

    /**
     * City's Population
     */
    public int Population;



    public boolean Capital;
}

