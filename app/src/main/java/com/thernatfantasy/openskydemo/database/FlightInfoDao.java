package com.thernatfantasy.openskydemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface FlightInfoDao {

    @Query("select * from FlightInfo WHERE time >= :timeThreshold ORDER BY time DESC LIMIT 500")
    LiveData<List<FlightInfo>> getFiveHundredMostRecentFlightInfosNotOlderThan(String timeThreshold);

    @Insert(onConflict = REPLACE)
    void addFlightInfo(FlightInfo flightInfo);

    @Delete
    void deleteFlightInfo(FlightInfo flightInfo);
}
