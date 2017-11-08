package com.thernatfantasy.openskydemo;

import android.app.Activity;
import android.app.Application;

import com.thernatfantasy.openskydemo.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */

public class OpenSkyDemoApplication extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity>dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
