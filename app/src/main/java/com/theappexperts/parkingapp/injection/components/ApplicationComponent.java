package com.theappexperts.parkingapp.injection.components;

import android.app.Application;
import android.content.Context;

import com.theappexperts.parkingapp.MyApp;
import com.theappexperts.parkingapp.data.IDataManager;
import com.theappexperts.parkingapp.data.network.ApiHelper;
import com.theappexperts.parkingapp.injection.modules.ApplicationModule;
import com.theappexperts.parkingapp.injection.modules.NetworkModule;
import com.theappexperts.parkingapp.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MyApp myApp);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

    ApiHelper getApiHelper();
}
