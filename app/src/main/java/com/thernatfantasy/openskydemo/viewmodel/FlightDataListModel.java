package com.thernatfantasy.openskydemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.thernatfantasy.openskydemo.database.FlightInfo;
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
    }


    public LiveData<List<FlightInfo>> getFlightInfoList(String query) {
        flightInfoList = flightInfoRepository.getFlightInfo(query);
        return flightInfoList;
    }

}