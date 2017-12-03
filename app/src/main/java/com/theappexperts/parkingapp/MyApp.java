package com.theappexperts.parkingapp;

import android.app.Application;

import com.theappexperts.parkingapp.injection.components.ApplicationComponent;
import com.theappexperts.parkingapp.injection.components.DaggerApplicationComponent;
import com.theappexperts.parkingapp.injection.modules.ApplicationModule;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public class MyApp extends Application {
    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {return applicationComponent; }

    public void setApplicationComponent(ApplicationComponent applicationComponent){
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
