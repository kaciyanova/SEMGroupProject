package com.napier.sem;

import java.util.ArrayList;
import java.util.Scanner;

import static com.napier.sem.CountryRequests.getWorldPopulation;

public class InputController
{
    public static void RequestReport(ArrayList<Country> countries, ArrayList<City> cities){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter how many countries to report");
        String topN = in.nextLine();

        getWorldPopulation(topN);
    }
}
