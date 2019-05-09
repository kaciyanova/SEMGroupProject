package com.napier.sem;
import static com.napier.sem.App.countries;

//Handles language reports
public class LanguageReport
{
    //caches stats so we don't have to calculate multiple times
    static boolean alreadyCountedLanguages;

    static long chineseSpeakers;
    static long englishSpeakers;
    static long hindiSpeakers;
    static long spanishSpeakers;
    static long arabicSpeakers;

    static void PrintLanguageReport()
    {

    }

    //counts number of speakers for each language
    static void CountLanguageSpeakers()
    {
        //only counts language speakers if we haven't already
        if (!alreadyCountedLanguages) {
            for (Country country : countries) {

                for (Language language : country.getLanguages()) {

                    switch (language.Name) {
                        case "Chinese":
                            chineseSpeakers = chineseSpeakers + (long) (country.Population * language.Percentage);
                            break;
                        case "English":
                            englishSpeakers = englishSpeakers + (long) (country.Population * language.Percentage);
                            break;
                        case "Hindi":
                            hindiSpeakers = hindiSpeakers + (long) (country.Population * language.Percentage);
                            break;
                        case "Spanish":
                            spanishSpeakers = spanishSpeakers + (long) (country.Population * language.Percentage);
                            break;
                        case "Arabic":
                            arabicSpeakers = arabicSpeakers + (long) (country.Population * language.Percentage);
                            break;
                    }
                }
            }
            alreadyCountedLanguages=true;
        }
    }
}
