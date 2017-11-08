package com.thernatfantasy.openskydemo.di;

import com.thernatfantasy.openskydemo.OpenSkyDemoApplication;
import com.thernatfantasy.openskydemo.database.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by mariuszrafalski on 07.11.2017.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class,
        NetModule.class,
        RoomModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(OpenSkyDemoApplication application);
        AppComponent build();
    }

    void inject(OpenSkyDemoApplication application);
}
