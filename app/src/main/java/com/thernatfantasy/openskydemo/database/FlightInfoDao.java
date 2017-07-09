package com.thernatfantasy.openskydemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.thernatfantasy.openskydemo.FlightInfo;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */
@Dao
public interface FlightInfoDao {

    @Query("select * from FlightInfo")
    LiveData<List<FlightInfo>> getAllFlightInfo();

    @Insert(onConflict = REPLACE)
    void addFlightInfo(FlightInfo flightInfo);

    @Delete
    void deleteFlightInfo(FlightInfo flightInfo);
}
