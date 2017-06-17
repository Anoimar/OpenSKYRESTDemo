package com.thernatfantasy.openskydemo.countries;

import android.util.Log;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by mariuszrafalski on 17.06.2017.
 */

public class CountryFlagUrlProvider {
    private static final String FLAG_PROVIDER_URL = "http://www.translatorscafe.com/cafe/images/flags/";
    private static final String FLAG_IMAGE_EXTENSION = ".gif";
    private static final String URL_TO_UNKNOWN_COUNTRY_FLAG = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Missing_flag.png";

    private Map<String, String> countries = new HashMap<>();
    private static final Map<String, String> COUNTRIES_NAMED_DIFFERENTLY_IN_ICAO24_THAN_IN_LOCALE = ImmutableMap.<String, String>builder()
            .put("ISLAMIC REPUBLIC OF IRAN","IR")
            .put("SYRIAN ARAB REPUBLIC","SY")
            .put("KINGDOM OF THE NETHERLANDS","NL")
            .put("RUSSIAN FEDERATION","RU")
            .put("REPUBLIC OF KOREA","KR")
            .put("REPUBLIC OF MOLDOVA","MD")
            .put("Viet Nam","VN")
            .build();



    public CountryFlagUrlProvider() {
        createCountriesMap();
    }

    private void createCountriesMap() {
        String[] countriesISO = Locale.getISOCountries();
        Locale outLocale = new Locale("EN", "us");
        for (String iso : countriesISO){
            Locale locale = new Locale("",iso);
            countries.put(locale.getDisplayCountry(outLocale).toUpperCase(),iso);
        }
        addCountriesWithDifferentNamesInISOFormatThanInIcao24();
    }

    private void addCountriesWithDifferentNamesInISOFormatThanInIcao24() {
        countries.putAll(COUNTRIES_NAMED_DIFFERENTLY_IN_ICAO24_THAN_IN_LOCALE);
    }

    public String getCountryFlagUrl(String originCountry) {
        try {
            return FLAG_PROVIDER_URL + getISOForCountryName(originCountry) + FLAG_IMAGE_EXTENSION;
        } catch (IllegalArgumentException e) {
            Log.e(getClass().getName(),"Failed to create url for "+originCountry);
        }
        return URL_TO_UNKNOWN_COUNTRY_FLAG;
    }

    private String getISOForCountryName(String countryName) throws IllegalArgumentException{
        return Functions.forMap(countries).apply(countryName.toUpperCase());
    }


}
