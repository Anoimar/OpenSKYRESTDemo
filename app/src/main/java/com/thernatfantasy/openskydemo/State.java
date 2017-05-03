package com.thernatfantasy.openskydemo;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */

public class State {
    private String icao24;
    private String callSign;
    private String originCountry;

    public State(String icao24, String callSign, String originCountry) {
        this.icao24 = icao24;
        this.callSign = callSign;
        this.originCountry = originCountry;
    }

    public String getIcao24() {
        return icao24;
    }

    public String getCallSign() {
        return callSign;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    @Override
    public String toString() {
        return "State{" +
                "icao24='" + icao24 + '\'' +
                ", callSign='" + callSign + '\'' +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
