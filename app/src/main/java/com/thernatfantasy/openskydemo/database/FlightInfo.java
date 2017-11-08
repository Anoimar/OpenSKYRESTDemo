package com.thernatfantasy.openskydemo.database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.thernatfantasy.openskydemo.database.DateConverter;

import java.util.Date;

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
    @TypeConverters(DateConverter.class)
    private Date time;

    public FlightInfo(String icao24, String callSign, String originCountry, Date time) {
        this.icao24 = icao24;
        this.callSign = callSign;
        this.originCountry = originCountry;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "id=" + id +
                ", icao24='" + icao24 + '\'' +
                ", callSign='" + callSign + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", time=" + time +
                '}';
    }


}
