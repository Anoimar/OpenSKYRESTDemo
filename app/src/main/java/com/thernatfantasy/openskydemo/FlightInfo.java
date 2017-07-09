package com.thernatfantasy.openskydemo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */

@Entity
public class FlightInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String icao24;
    private String callSign;
    private String originCountry;

    @Ignore
    public FlightInfo(String icao24, String callSign, String originCountry) {
        this.icao24 = icao24;
        this.callSign = callSign;
        this.originCountry = originCountry;
    }

    public FlightInfo(int id, String icao24, String callSign, String originCountry) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "icao24='" + icao24 + '\'' +
                ", callSign='" + callSign + '\'' +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
