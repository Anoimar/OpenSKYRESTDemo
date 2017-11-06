package com.thernatfantasy.openskydemo.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.thernatfantasy.openskydemo.FlightInfo;
import com.thernatfantasy.openskydemo.database.migrations.Database1To2Migration;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */
@Database(entities = {FlightInfo.class}, version = 2)
public abstract class FlightDatabase extends RoomDatabase {

    public static final String FLIGHT_INFO_TABLE_NAME = "FlightInfo";
    private static FlightDatabase INSTANCE;

    public static FlightDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),FlightDatabase.class,"flight_data_db")
                            .addMigrations(MIGRATION_1_2)
                            .build();
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
           new Database1To2Migration().migrate(database);
        }
    };

    public abstract FlightInfoDao flightInfoDao();
}
