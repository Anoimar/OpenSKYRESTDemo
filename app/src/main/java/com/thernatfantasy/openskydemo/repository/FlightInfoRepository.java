package com.thernatfantasy.openskydemo.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.thernatfantasy.openskydemo.api.OpenSkyService;
import com.thernatfantasy.openskydemo.database.FlightInfo;
import com.thernatfantasy.openskydemo.database.FlightInfoDao;
import com.thernatfantasy.openskydemo.json.FlightData;
import com.thernatfantasy.openskydemo.utils.TimeProvider;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */
@Singleton
public class FlightInfoRepository {
    private final OpenSkyService openSkyService;
    private final FlightInfoDao flightInfoDao;


    @Inject
    public FlightInfoRepository(OpenSkyService openSkyService, FlightInfoDao flightInfoDao) {
        this.openSkyService = openSkyService;
        this.flightInfoDao = flightInfoDao;
    }


    public LiveData<List<FlightInfo>> getFlightInfo() {
        getFlightDataFromOpenSkyService();
        return flightInfoDao.getFiveHundredMostRecentFlightInfosNotOlderThan(String.valueOf(new TimeProvider().getTimeOneHourAgo()));

    }

    private void getFlightDataFromOpenSkyService(){
        Log.i("FlightInfoRepository","Trying to get Flight Data from OpenSkyService");
        Call<FlightData> call = openSkyService.flightData();
        call.enqueue(new Callback<FlightData>() {
            @Override
            public void onResponse(Call<FlightData> call, Response<FlightData> response) {
                for(FlightInfo flightInfo : response.body().getFlightInfoList()){
                    new AddFlightInfoAsyncTask().execute(flightInfo);
                }
            }

            @Override
            public void onFailure(Call<FlightData> call, Throwable t) {
                Log.e("FlightInfoRepository","Couldn't get flight data."+t.getMessage());
            }
        });
    }



    private class AddFlightInfoAsyncTask extends AsyncTask<FlightInfo, Void, Void> {

        @Override
        protected Void doInBackground(FlightInfo... flightInfos) {
            flightInfoDao.addFlightInfo(flightInfos[0]);
            return null;
        }
    }
}
