package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.napier.sem.App.*;
import static com.napier.sem.ReportGenerator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ReportGeneratorTest {


    @BeforeAll
    static void init()
    {

    }
    @Test
    void GenerateCountryReportsTestNull()
    {

        assertNotNull(ReportGenerator.GenerateCountryReports(null));
    }

    @Test
    void GenerateCountryReportsTestEmpty()
    {
        ArrayList<Country> report = new ArrayList<>();
        GenerateCountryReports(report);
        assertFalse(ReportGenerator.GenerateCountryReports(report).isEmpty());
    }

    @Test
    void GenerateCityReportsTestNull()
    {
        assertNotNull(ReportGenerator.GenerateCityReports(null));
    }

    @Test
    void GenerateCityReportsTestEmpty()
    {
        ArrayList<City> report = new ArrayList<>();
        GenerateCityReports(report);
        assertFalse(ReportGenerator.GenerateCityReports(report).isEmpty());
    }

    @Test
    void GenerateCapitalReportsTestNull()
    {
        assertNotNull(ReportGenerator.GenerateCapitalReports(null));
    }

    @Test
    void GenerateCapitalReportsTestEmpty()
    {
        ArrayList<City> report = new ArrayList<>();
        GenerateCapitalReports(report);
        assertFalse(ReportGenerator.GenerateCapitalReports(report).isEmpty());
    }

    @Test
    void GenerateLanguageReportsTestNull()
    {
        assertNotNull(ReportGenerator.GenerateLanguageReports(null));
    }

    @Test
    void GenerateLanguageReportsTestEmpty()
    {
        ArrayList<LanguageSpeakers> report = new ArrayList<>();
        GenerateLanguageReports(report);
        assertFalse(ReportGenerator.GenerateLanguageReports(report).isEmpty());
    }


    @Test
    void GenerateCountryReportTestNull()
    {

        assertNotNull(ReportGenerator.GenerateCountryReport(null));
    }

   @Test
   void GenerateCountryReportTestEmpty()
   {
        ArrayList<Country> report = new ArrayList<>();
        assertFalse(ReportGenerator.GenerateCountryReports(report).isEmpty());
    }

    @Test
    void GenerateCityReportTestNull()
    {

        assertNotNull(ReportGenerator.GenerateCityReport(null));
    }

    @Test
    void GenerateCapitalReportTestNull()
    {

        assertNotNull(ReportGenerator.GenerateCapitalReport(null));
    }

    @Test
    void GenerateLanguageReportTestNull()
    {

        assertNotNull(ReportGenerator.GenerateLanguageReport(null));
    }












}