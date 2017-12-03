package com.theappexperts.parkingapp.injection.modules;

import android.app.Application;
import android.content.Context;

import com.theappexperts.parkingapp.data.AppDataManager;
import com.theappexperts.parkingapp.data.IDataManager;
import com.theappexperts.parkingapp.data.network.ApiHelper;
import com.theappexperts.parkingapp.data.network.AppApiHelper;
import com.theappexperts.parkingapp.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) { this.application = application; }

    @Provides
    @ApplicationContext
    Context getContext() { return application; }

    @Provides
    Application getApplication() { return application; }

    @Provides
    @Singleton
    IDataManager getDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper getApiHelper(AppApiHelper appApiHelper) { return appApiHelper; }

}
