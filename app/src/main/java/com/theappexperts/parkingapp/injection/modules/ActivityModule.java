package com.theappexperts.parkingapp.injection.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.theappexperts.parkingapp.injection.scope.ActivityContext;
import com.theappexperts.parkingapp.view.parkinglist.IParkingListMvpPresenter;
import com.theappexperts.parkingapp.view.parkinglist.IParkingListMvpView;
import com.theappexperts.parkingapp.view.parkinglist.ParkingListPresenter;
import com.theappexperts.parkingapp.view.ui.utils.rx.AppSchedulerProvider;
import com.theappexperts.parkingapp.view.ui.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

@Module
public class ActivityModule {

    AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context getActivityContext(){ return appCompatActivity; }

    @Provides
    AppCompatActivity getAppCompatActivity() { return appCompatActivity; }

    @Provides
    SchedulerProvider appSchedulerProvider() { return new AppSchedulerProvider(); }

    @Provides
    CompositeDisposable getCompositeDisposable()
    {
        return new CompositeDisposable();
    }

    @Provides
    IParkingListMvpPresenter<IParkingListMvpView> getParkingListPresenter (ParkingListPresenter<IParkingListMvpView> parkingListMvpViewParkingListPresenter)
    {
        return parkingListMvpViewParkingListPresenter;
    }

}
