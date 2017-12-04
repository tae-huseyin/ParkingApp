package com.theappexperts.parkingapp.data;

import android.content.Context;

import com.theappexperts.parkingapp.data.network.ApiHelper;
import com.theappexperts.parkingapp.data.network.AppApiHelper;
import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.data.network.model.ReserveModel;
import com.theappexperts.parkingapp.injection.scope.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements IDataManager{

    private ApiHelper apiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context application, AppApiHelper appApiHelper) {
        this.apiHelper = appApiHelper;
    }

    public Observable<List<ParkingModel>> getFromApi_ParkingList(){
        return apiHelper.getFromApi_ParkingList();
    }

//    public Observable<ReserveModel> postFromApi_ParkingReserve(){
//        return apiHelper.postFromApi_ParkingReserve();
//    }
}
