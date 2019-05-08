package com.napier.sem;

import java.util.Scanner;

class InputController
{
    //reads in user input for report requests
    static void RequestReport()
    {
        Scanner in = new Scanner(System.in);

        int reportType;
        do {
            System.out.println("Enter what kind of report you'd like to generate:");
            System.out.println("1: Country");
            System.out.println("2: City");
            System.out.println("3: Language");
            System.out.println("4: Population");
            System.out.println("5: Population Numbers");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please pick a number from the menu (1-4):");
            }
            reportType = in.nextInt();
        }
        while (!(reportType >= 1 && reportType <= 5));

        switch (reportType) {
            case 1:
                RequestCountryReport();
                break;
            case 2:
                RequestCityReport();
                break;

            case 3:
//                RequestLanguageReport();
                break;

            case 4:
//                RequestPopulationReport();
                break;
            case 5:
                RequestPopulationNumbers();
                break;
        }

        RepeatRequest(in);
    }

    //gets input for country report scope and number of countries desired
    static void RequestCountryReport()
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
                break;

            case 2:
                CountryRequests.getContinentPopulation(numberOfCountriesToGet);
                break;

            case 3:
                CountryRequests.getRegionPopulation(numberOfCountriesToGet);
                break;

            default: {
                System.out.println("Scope is " + scope);

                System.out.println("Unknown scope entered, redirecting to menu start...");
                RequestReport();
                break;
            }
        }
    }

    //gets input for city report scope and number of cities desired + whether to only return capitals or not
    static void RequestCityReport()
    {
        Scanner in = new Scanner(System.in);

        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like the report:");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region");
            System.out.println("4: District");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please pick a number from the menu (1-4):");
            }
            scope = in.nextInt();
        }
        while (!(scope >= 1 && scope <= 4));

        //validates input to be an int and within a valid range
        Integer numberOfCitiesToGet;
        do {
            System.out.println("Please enter the top N cities by population you want in the report (or enter 0 to get all)");

            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please enter a number");
            }
            numberOfCitiesToGet = in.nextInt();
        }
        while (numberOfCitiesToGet < 0);


        System.out.println("Would you like to limit your report to capital cities? (y/n)");
        in.nextLine();
        String capitalstr = in.nextLine();
        boolean capitals;
        capitals = capitalstr.equals("y");

        switch (scope) {
            case 1:
                CityRequests.getWorldPopulation(numberOfCitiesToGet, capitals);
                break;

            case 2:
                CityRequests.getContinentPopulation(numberOfCitiesToGet, capitals);
                break;

            case 3:
                CityRequests.getRegionPopulation(numberOfCitiesToGet, capitals);
                break;

            case 4:
                CityRequests.getDistrictPopulation(numberOfCitiesToGet, capitals);
                break;

            default: {
                System.out.println("Scope is " + scope);

                System.out.println("Unknown scope entered, redirecting to menu start...");
                RequestReport();
                break;
            }
        }
    }

    //gets input for population number scope
    static void RequestPopulationNumbers()
    {
        Scanner in = new Scanner(System.in);

        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like a population count:");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region");
            System.out.println("4: District");
            System.out.println("5: City");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number");
                System.out.println("Please pick a number from the menu (1-5):");
            }
            scope = in.nextInt();
        }
        while (!(scope >= 1 && scope <= 5));


        switch (scope) {
            case 1:
                PopulationNumbers.GetWorldPopulation();
                break;

            case 2:
                PopulationNumbers.GetContinentPopulation();
                break;

            case 3:
                PopulationNumbers.GetRegionPopulation();
                break;

            case 4:
                PopulationNumbers.GetDistrictPopulation();
                break;
            case 5:
                PopulationNumbers.GetCityPopulation();
                break;
            default: {
                System.out.println("Scope is " + scope);

                System.out.println("Unknown scope entered, redirecting to menu start...");
                RequestReport();
                break;
            }
        }

    }

    //Asks if another report is requested
    private static void RepeatRequest(Scanner in)
    {
        System.out.println("Would you like to request another report? (y/n)");
        in.nextLine();
        String again = in.nextLine();

        if (!again.equals("y")) {
            System.out.println("Exiting program");
            return;
        }
        System.out.println("Requesting another report \n");
        RequestReport();
    }
}
