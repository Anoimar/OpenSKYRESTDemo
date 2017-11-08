package com.thernatfantasy.openskydemo.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.thernatfantasy.openskydemo.database.FlightDataListModel;
import com.thernatfantasy.openskydemo.viewmodel.FlightInfoDataListModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FlightDataListModel.class)
    abstract ViewModel bindFlightDataListModel(FlightDataListModel flightDataListModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FlightInfoDataListModelFactory factory);
}
