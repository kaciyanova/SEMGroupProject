package com.napier.sem;


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

    /**
     * City's District
     */
    public String District;

    /**
     * City's Population
     */
    public int Population;

    public Country Country;
    public Country getCountry() { return this.Country; }
    public void setCountry(Country Country) { this.Country = Country; }

    public boolean IsCapital;
    public boolean getCapital() { return this.IsCapital; }
    public void setCapital(boolean IsCapital) { this.IsCapital = IsCapital; }

}

