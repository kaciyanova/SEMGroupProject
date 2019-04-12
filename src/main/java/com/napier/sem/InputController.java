package com.napier.sem;

import java.util.Scanner;
import java.util.stream.Collectors;

public class InputController
{
    public enum ReportType
    {
        Country,
        City,
        Language
    }

    //reads in user input for report requests
    public static void RequestReport()
    {
        Scanner in = new Scanner(System.in);

        int reportType;
        do {
            System.out.println("Enter what kind of report you'd like to generate:");
            System.out.println("1: Country");
            System.out.println("2: City");
            System.out.println("3: Language");
            System.out.println("4: Population");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please pick a number from the menu (1-4):");
            }
            reportType = in.nextInt();
        }
        while (!(reportType >= 1 && reportType <= 4));

        switch (reportType) {
            case 1:
                RequestCountryReport();
            case 2:
//                RequestCityReport();

            case 3:
//                RequestLanguageReport();

            case 4:
//                RequestPopulationReport();
        }
    }

    //gets input for country report scope and number of countries desired
    public static void RequestCountryReport()
    {

        Scanner in = new Scanner(System.in);


        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like the report:");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please pick a number from the menu (1-3):");
            }
            scope = in.nextInt();
        }
        while (!(scope >= 1 && scope <= 3));

        //validates input to be an int and within a valid range
        Integer numberOfCountriesToGet;
        do {
            System.out.println("Please enter the top N countries by population you want in the report (or enter 0 to get all)");

            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please enter a number");
            }
            numberOfCountriesToGet = in.nextInt();
        }
        while (numberOfCountriesToGet < 0);


        switch (scope) {
            case 1:
                CountryRequests.getWorldPopulation(numberOfCountriesToGet);
            case 2:
                CountryRequests.getContinentPopulation(numberOfCountriesToGet);
            case 3:
                CountryRequests.getRegionPopulation(numberOfCountriesToGet);
            default: {
                System.out.println("Scope is "+scope);

                System.out.println("How did you manage to sneak past my int check! Villain! Back to the beginning of the method with you.");
                RequestReport();
            }
        }
    }
}
