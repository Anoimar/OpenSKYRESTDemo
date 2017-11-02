package com.thernatfantasy.openskydemo.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.thernatfantasy.openskydemo.FlightInfo;

import java.util.List;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */

public class FlightDataListModel extends AndroidViewModel {

    private final LiveData<List<FlightInfo>> flightInfoList;
    private FlightDatabase flightDatabase;


    public FlightDataListModel(Application application) {
        super(application);

        flightDatabase = FlightDatabase.getDatabase(this.getApplication());
        flightInfoList = flightDatabase.flightInfoDao().getAllFlightInfo();

    }

    public LiveData<List<FlightInfo>> getFlightInfoList() {
        return flightInfoList;
    }

    public void addFlightInfo(FlightInfo flightInfo) {
        new AddFlightInfoAsyncTask(flightDatabase).execute(flightInfo);
    }

    public void removeFlightInfo(FlightInfo flightInfo) {
        new RemoveFlightInfoAsyncTask(flightDatabase).execute(flightInfo);
    }


    abstract class FlightInfoCRUDAsyncTask extends AsyncTask<FlightInfo, Void, Void> {
        protected FlightDatabase database;

        public FlightInfoCRUDAsyncTask(FlightDatabase database) {
            this.database = database;
        }


    }

    private class RemoveFlightInfoAsyncTask extends FlightInfoCRUDAsyncTask {

        public RemoveFlightInfoAsyncTask(FlightDatabase database) {
            super(database);
        }

        @Override
        protected Void doInBackground(FlightInfo... flightInfos) {
            database.flightInfoDao().deleteFlightInfo(flightInfos[0]);
            return null;
        }
    }

    private class AddFlightInfoAsyncTask extends FlightInfoCRUDAsyncTask {

        public AddFlightInfoAsyncTask(FlightDatabase database) {
            super(database);
        }

        @Override
        protected Void doInBackground(FlightInfo... flightInfos) {
            database.flightInfoDao().addFlightInfo(flightInfos[0]);
            return null;
        }
    }
}