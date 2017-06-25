package com.thernatfantasy.openskydemo;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */


public class UsefulState implements Parcelable {
    private String icao24;
    private String callSign;
    private String originCountry;

    public UsefulState(String icao24, String callSign, String originCountry) {
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
        return "UsefulState{" +
                "icao24='" + icao24 + '\'' +
                ", callSign='" + callSign + '\'' +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.icao24);
        dest.writeString(this.callSign);
        dest.writeString(this.originCountry);
    }

    protected UsefulState(Parcel in) {
        this.icao24 = in.readString();
        this.callSign = in.readString();
        this.originCountry = in.readString();
    }

    public static final Parcelable.Creator<UsefulState> CREATOR = new Parcelable.Creator<UsefulState>() {
        @Override
        public UsefulState createFromParcel(Parcel source) {
            return new UsefulState(source);
        }

        @Override
        public UsefulState[] newArray(int size) {
            return new UsefulState[size];
        }
    };
}
