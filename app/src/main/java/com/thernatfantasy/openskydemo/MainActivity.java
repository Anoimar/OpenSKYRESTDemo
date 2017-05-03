package com.thernatfantasy.openskydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thernatfantasy.openskydemo.json.FlightData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGING_TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenSkyService openSkyService = OpenSkyService.retrofit.create(OpenSkyService.class);
        Call<FlightData> call = openSkyService.flightData();
        call.enqueue(new Callback<FlightData>() {
            @Override
            public void onResponse(Call<FlightData> call, Response<FlightData> response) {
                Log.i(LOGGING_TAG,"Successfully acquired flight data.");
            }

            @Override
            public void onFailure(Call<FlightData> call, Throwable t) {
                Log.e(LOGGING_TAG,"Couldn't get flight data."+t.getMessage());
            }
        });

    }
}
