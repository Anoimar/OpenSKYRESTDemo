package com.thernatfantasy.openskydemo.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by mariuszrafalski on 20.08.2017.
 */

public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(long time){
        return new Date(time);
    }

    @TypeConverter
    public static long fromDate(Date date){
        return date.getTime();
    }
}
