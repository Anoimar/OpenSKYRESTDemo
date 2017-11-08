package com.thernatfantasy.openskydemo.di;

import com.thernatfantasy.openskydemo.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
