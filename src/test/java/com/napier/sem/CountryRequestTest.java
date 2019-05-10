package com.napier.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.napier.sem.App.countries;
import static com.napier.sem.ReportGenerator.GenerateCountryReports;

public class CountryRequestsTest
{
    @BeforeAll
    static void init()
    {
        World,
        Continent,
        Region
    }
    @Test
    void GetCountriesInAreaByPopulationTestNull()
    {
        assertNotNull(App.GetCountriesInAreaByPopulation());
    }
    App.GetCountriesInAreaByPopulation(null, null);

}

    @Test
    void SendToWriterTestNull()
    {
        assertNotNull(App.gSendToWriter());
    }
     App.SendToWriter(null, null);
}