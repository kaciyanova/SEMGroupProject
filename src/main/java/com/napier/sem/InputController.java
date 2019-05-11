package com.napier.sem;

import java.util.Scanner;

class InputController {
    //reads in user input for report requests
    static void RequestReport() {
        Scanner in = new Scanner(System.in);

        int reportType;
        do {
            System.out.println("Enter what kind of report you'd like to generate:\n");
            System.out.println("1: Country");
            System.out.println("2: City");
            System.out.println("3: Language");
            System.out.println("4: Total/Urban/Rural Population Statistics");
            System.out.println("5: Population Numbers\n");
            reportType = getScope(in, 5);
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
                LanguageReport.RequestLanguageReport();
                break;

            case 4:
                RequestPopulationReport();
                break;
            case 5:
                RequestPopulationNumbers();
                break;
        }

        RepeatRequest(in);
    }

    //gets input for country report scope and number of countries desired
    static void RequestCountryReport() {

        Scanner in = new Scanner(System.in);


        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like the report:\n");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region\n");
            while (!in.hasNextInt()) {
                String invalid = in.next();
                System.out.println(invalid + " is not a number\n");
                System.out.println("Please pick a number from the menu (1-3):\n");
            }
            scope = in.nextInt();
        }
        while (!(scope >= 1 && scope <= 3));

        //validates input to be an int and within a valid range
        Integer numberOfCountriesToGet;
        do {
            System.out.println("Please enter the top N countries by population you want in the report (or enter 0 to get all)\n");

            numberOfCountriesToGet = getNumberOfCities(in);
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

                System.out.println("Unknown scope entered, redirecting to menu start...\n");
                RequestReport();
                break;
            }
        }
    }

    //gets input for city report scope and number of cities desired + whether to only return capitals or not
    static void RequestCityReport() {
        Scanner in = new Scanner(System.in);

        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like the report:\n");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region");
            System.out.println("4: Country");
            System.out.println("5: District\n");
            scope = getScope(in, 5);
        }
        while (!(scope >= 1 && scope <= 5));

        //validates input to be an int and within a valid range
        Integer numberOfCitiesToGet;
        do {
            System.out.println("Please enter the top N cities by population you want in the report (or enter 0 to get all)\n");

            numberOfCitiesToGet = getNumberOfCities(in);
        }
        while (numberOfCitiesToGet < 0);


        System.out.println("Would you like to limit your report to capital cities? (y/n)\n");
        in.nextLine();
        String capitalstr = in.nextLine();
        boolean capitals;
        capitals = capitalstr.equals("y");

        switch (scope) {
            case 1:
                CityRequests.getWorldCities(numberOfCitiesToGet, capitals);
                break;

            case 2:
                CityRequests.getContinentCities(numberOfCitiesToGet, capitals);
                break;

            case 3:
                CityRequests.getRegionCities(numberOfCitiesToGet, capitals);
                break;

            case 4:
                CityRequests.getCountryCities(numberOfCitiesToGet, capitals);
                break;
            case 5:
                CityRequests.getDistrictCities(numberOfCitiesToGet, capitals);
                break;

            default: {
                System.out.println("Scope is " + scope + "\n");

                System.out.println("Unknown scope entered, redirecting to menu start...\n");
                RequestReport();
                break;
            }
        }
    }

    //gets input for population number scope
    static void RequestPopulationReport() {
        Scanner in = new Scanner(System.in);

        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like a population statistics report:\n");
            System.out.println("1: Continent");
            System.out.println("2: Region");
            System.out.println("3: Country");
            scope = getScope(in, 3);

        }
        while (!(scope >= 1 && scope <= 3));


        switch (scope) {
            case 1:
                PopulationPercentages.GetContinentPopulationStats();
                break;

            case 2:
                PopulationPercentages.GetRegionPopulationStats();
                break;

            case 3:
                PopulationPercentages.GetCountryPopulationStats();
                break;
            default: {
                System.out.println("Scope is " + scope);

                System.out.println("Unknown scope entered, redirecting to menu start...\n");
                RequestReport();
                break;
            }
        }

    }

    //gets input for population number scope
    static void RequestPopulationNumbers() {
        Scanner in = new Scanner(System.in);

        //validates input to be an int and within the correct menu range
        int scope;
        do {
            System.out.println("Please enter the area scope for which you'd like a population count:\n");
            System.out.println("1: World");
            System.out.println("2: Continent");
            System.out.println("3: Region");
            System.out.println("4: District");
            System.out.println("5: City\n");
            scope = getScope(in, 5);
        }
        while (!(scope >= 1 && scope <= 5));


        switch (scope) {
            case 1:
                PopulationCount.GetWorldPopulation();
                break;

            case 2:
                PopulationCount.GetContinentPopulation();
                break;

            case 3:
                PopulationCount.GetRegionPopulation();
                break;

            case 4:
                PopulationCount.GetDistrictPopulation();
                break;
            case 5:
                PopulationCount.GetCityPopulation();
                break;
            default: {
                System.out.println("Scope is " + scope);

                System.out.println("Unknown scope entered, redirecting to menu start...\n");
                RequestReport();
                break;
            }
        }

    }

    //Asks if another report is requested
    private static void RepeatRequest(Scanner in) {
        System.out.println("Would you like to request another report? (y/n)\n");
        in.nextLine();
        String again = in.nextLine();

        if (!again.equals("y")) {
            System.out.println("Exiting program\n");
            return;
        }
        System.out.println("Requesting another report \n");
        RequestReport();
    }

    //gets user input for number of cities to get
    private static Integer getNumberOfCities(Scanner in) {
        Integer numberOfCitiesToGet;
        while (!in.hasNextInt()) {
            String invalid = in.next();
            System.out.println(invalid + " is not a number\n");
            System.out.println("Please enter a number\n");
        }
        numberOfCitiesToGet = in.nextInt();
        return numberOfCitiesToGet;
    }

    //gets user input for area scope
    private static int getScope(Scanner in, int options) {
        int scope;
        while (!in.hasNextInt()) {
            String invalid = in.next();
            System.out.println(invalid + " is not a number\n");
            System.out.println("Please pick a number from the menu (1-" + options + "):\n");
        }
        scope = in.nextInt();
        return scope;
    }

}
