package com.thernatfantasy.openskydemo.database.migrations;

import android.arch.persistence.db.SupportSQLiteDatabase;

import com.thernatfantasy.openskydemo.database.FlightDatabase;

/**
 * Created by mariuszrafalski on 05.11.2017.
 */

public class Database1To2Migration {
    private static final String TEMPORARY_TABLE_NAME = "Temp_TABLE";

    public void migrate(SupportSQLiteDatabase database){
        database.execSQL("CREATE TABLE IF NOT EXISTS "+ TEMPORARY_TABLE_NAME+" (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `icao24` TEXT, `callSign` TEXT, `originCountry` TEXT, `time` INTEGER)");
        database.execSQL("INSERT INTO "+ TEMPORARY_TABLE_NAME
                + " SELECT * FROM " + FlightDatabase.FLIGHT_INFO_TABLE_NAME);
        database.execSQL("DROP TABLE " + FlightDatabase.FLIGHT_INFO_TABLE_NAME);
        database.execSQL("ALTER TABLE "+ TEMPORARY_TABLE_NAME+" RENAME TO "+FlightDatabase.FLIGHT_INFO_TABLE_NAME);
    }
}
