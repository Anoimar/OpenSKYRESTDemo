package com.thernatfantasy.openskydemo.di;

import com.thernatfantasy.openskydemo.api.OpenSkyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */

@Module
public class NetModule {

    String serviceBaseUrl="https://opensky-network.org/";

    @Provides
    @Singleton
    OpenSkyService provideOpenSkyService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(serviceBaseUrl)
                .build()
                .create(OpenSkyService.class);
    }
}
