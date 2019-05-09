package com.napier.sem;

import java.util.List;

public class Country
{
    /**
     * Country code
     */
    public String Code;

    /**
     * Country name
     */
    public String Name;

    /**
     * Country's  Code
     */
    public String Continent;

    /**
     * Country's Region
     */
    public String Region;

    /**
     * Country's Population
     */
    public int Population;

    //ID of Country's capital
    public int CapitalID;

    //Country's capital
    public City Capital;
    //gets capital
    public City getCapital() { return this.Capital; }
    //sets capital
    public void setCapital(City Capital) { this.Capital = Capital; }

    //Country's Languages
    public List<Language> Languages;

    //gets Languages
    public List<Language> getLanguages() { return this.Languages; }
    //sets Languages
    public void setLanguages(List<Language> Languages) { this.Languages = Languages; }
}
