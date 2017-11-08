package com.thernatfantasy.openskydemo.di;

import android.content.Context;

import com.thernatfantasy.openskydemo.OpenSkyDemoApplication;
import dagger.Module;
import dagger.Provides;


/**
 * Created by mariuszrafalski on 07.11.2017.
 */
@Module(includes = ViewModelModule.class)
class AppModule {


    @Provides
    Context provideContext(OpenSkyDemoApplication application){
        return application.getApplicationContext();
    }


}
