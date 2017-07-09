package com.thernatfantasy.openskydemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.thernatfantasy.openskydemo.FlightInfo;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */
@Database(entities = {FlightInfo.class}, version = 1)
public abstract class FlightDatabase extends RoomDatabase {

    private static FlightDatabase INSTANCE;

    public static FlightDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),FlightDatabase.class,"flight_data_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract FlightInfoDao flightInfoDao();
}
