package com.thernatfantasy.openskydemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.thernatfantasy.openskydemo.repository.FlightInfoRepository;


import java.util.List;

import javax.inject.Inject;

/**
 * Created by mariuszrafalski on 25.06.2017.
 */

public class FlightDataListModel extends ViewModel {

    private LiveData<List<FlightInfo>> flightInfoList;
    private FlightInfoRepository flightInfoRepository;

    @Inject
    public FlightDataListModel(FlightInfoRepository repository) {
        this.flightInfoRepository = repository;
        flightInfoList = flightInfoRepository.getFlightInfo();
    }


    public LiveData<List<FlightInfo>> getFlightInfoList() {
        return flightInfoList;
    }


}