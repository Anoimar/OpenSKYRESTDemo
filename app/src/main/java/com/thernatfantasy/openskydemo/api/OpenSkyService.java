package com.thernatfantasy.openskydemo.api;

import com.thernatfantasy.openskydemo.json.FlightData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */

public interface OpenSkyService {

    String serviceBaseUrl = "https://opensky-network.org/";

    @GET("api/states/all")
    Call<FlightData>flightData();

}
