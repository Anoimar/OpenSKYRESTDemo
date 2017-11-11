package com.thernatfantasy.openskydemo.di;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;

import com.thernatfantasy.openskydemo.OpenSkyDemoApplication;
import com.thernatfantasy.openskydemo.database.FlightDatabase;
import com.thernatfantasy.openskydemo.database.FlightInfoDao;
import com.thernatfantasy.openskydemo.database.migrations.Database1To2Migration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mariuszrafalski on 08.11.2017.
 */

@Module
public class RoomModule {

    @Singleton
    @Provides
    FlightDatabase providesRoomDatabase(OpenSkyDemoApplication application){
        return Room.databaseBuilder(application,FlightDatabase.class,"flight_data_db")
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    @Singleton
    @Provides
    FlightInfoDao providesProductDao(FlightDatabase flightDatabase) {
        return flightDatabase.getFlightInfoDao();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            new Database1To2Migration().migrate(database);
        }
    };

}
