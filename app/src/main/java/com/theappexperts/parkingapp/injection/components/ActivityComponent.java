package com.theappexperts.parkingapp.injection.components;

import com.theappexperts.parkingapp.MainActivity;
import com.theappexperts.parkingapp.injection.modules.ActivityModule;
import com.theappexperts.parkingapp.injection.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
