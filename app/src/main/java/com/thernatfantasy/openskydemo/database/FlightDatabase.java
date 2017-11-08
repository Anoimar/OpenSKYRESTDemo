package com.thernatfantasy.openskydemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */
@Database(entities = {FlightInfo.class}, version = 2)
public abstract class FlightDatabase extends RoomDatabase {

    public static final String FLIGHT_INFO_TABLE_NAME = "FlightInfo";

    public abstract FlightInfoDao getFlightInfoDao();
}
