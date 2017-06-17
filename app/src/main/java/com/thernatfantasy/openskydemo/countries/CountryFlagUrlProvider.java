package com.thernatfantasy.openskydemo.countries;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Created by mariuszrafalski on 17.06.2017.
 */

public class CountryFlagUrlProvider {
    private static final String FLAG_PROVIDER_URL = "http://www.sciencekids.co.nz/images/pictures/flags680/";
    private static final String FLAG_IMAGE_EXTENSION = ".jpg";
    private static final Map<String, String> COUNTRIES_NAMED_DIFFERENTLY_IN_ICAO24 = ImmutableMap.<String, String>builder()
            .put("ISLAMIC REPUBLIC OF IRAN","Iran")
            .put("SYRIAN ARAB REPUBLIC","Syria")
            .put("KINGDOM OF THE NETHERLANDS","Netherlands")
            .put("RUSSIAN FEDERATION","Russia")
            .put("REPUBLIC OF KOREA","South_Korea")
            .put("REPUBLIC OF MOLDOVA","Moldova")
            .put("VIET NAM","Vietnam")
            .build();


    public String getCountryFlagUrl(String originCountry) {
        return FLAG_PROVIDER_URL + getFormattedCountryName(originCountry) + FLAG_IMAGE_EXTENSION;
    }

    private String getFormattedCountryName(String countryName) throws IllegalArgumentException{
        return Functions.forMap(COUNTRIES_NAMED_DIFFERENTLY_IN_ICAO24, getStandardCountryName(countryName)).apply(countryName.toUpperCase());
    }

    private String getStandardCountryName(String countryName) {
        return countryName.replace(" ","_");

    }
}
