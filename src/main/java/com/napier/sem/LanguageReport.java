package com.napier.sem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.napier.sem.App.countries;
import static java.util.Collections.sort;

//Handles language reports
public class LanguageReport
{
    //caches stats so we don't have to calculate multiple times
    static boolean alreadyCountedLanguages;
    static List<LanguageSpeakers> languageStatistics;

    static void RequestLanguageReport()
    {
        //only counts language speakers if we haven't already
        if (!alreadyCountedLanguages) {
            CountLanguageSpeakers();
            CalculateWorldPercentages();

            languageStatistics = languageStatistics;

            //creates comparator for language sorting
            Comparator<LanguageSpeakers> comparator = Comparator.comparingLong(LanguageSpeakers::getSpeakers);
            //sorts languages by number of speakers descending
            languageStatistics.sort(comparator.reversed());

            languageStatistics = languageStatistics;

            alreadyCountedLanguages = true;
        }
    }

    //counts number of speakers for each language
    static void CountLanguageSpeakers()
    {
        LanguageSpeakers chineseSpeakers = new LanguageSpeakers();
        LanguageSpeakers englishSpeakers = new LanguageSpeakers();
        LanguageSpeakers hindiSpeakers = new LanguageSpeakers();
        LanguageSpeakers spanishSpeakers = new LanguageSpeakers();
        LanguageSpeakers arabicSpeakers = new LanguageSpeakers();

        chineseSpeakers.LanguageName = "Chinese";
        englishSpeakers.LanguageName = "English";
        hindiSpeakers.LanguageName = "Hindi";
        spanishSpeakers.LanguageName = "Spanish";
        arabicSpeakers.LanguageName = "Arabic";

        for (Country country : countries) {

            for (Language language : country.getLanguages()) {

                switch (language.Name) {
                    case "Chinese":
                        chineseSpeakers.Speakers = chineseSpeakers.Speakers + (long) (country.Population * language.Percentage);
                        break;
                    case "English":
                        englishSpeakers.Speakers = englishSpeakers.Speakers + (long) (country.Population * language.Percentage);
                        break;
                    case "Hindi":
                        hindiSpeakers.Speakers = hindiSpeakers.Speakers + (long) (country.Population * language.Percentage);
                        break;
                    case "Spanish":
                        spanishSpeakers.Speakers = spanishSpeakers.Speakers + (long) (country.Population * language.Percentage);
                        break;
                    case "Arabic":
                        arabicSpeakers.Speakers = arabicSpeakers.Speakers + (long) (country.Population * language.Percentage);
                        break;
                }
            }
        }

        languageStatistics= new ArrayList<>();

        languageStatistics.add(chineseSpeakers);
        languageStatistics.add(englishSpeakers);
        languageStatistics.add(hindiSpeakers);
        languageStatistics.add(spanishSpeakers);
        languageStatistics.add(arabicSpeakers);
    }


    //calculates percentages of world population for languages
    static void CalculateWorldPercentages()
    {
        //ensures we've calculated language counts first
        if (languageStatistics.isEmpty()) {
            System.out.println("Language counts not calculated yet, calculating...\n");
            CountLanguageSpeakers();
        }

        long worldPopulation = PopulationNumbers.CalculatePopulationInCountries(countries);

        for (LanguageSpeakers language : languageStatistics) {
            language.WorldPercentage = (float) language.Speakers / worldPopulation;
        }
    }
}