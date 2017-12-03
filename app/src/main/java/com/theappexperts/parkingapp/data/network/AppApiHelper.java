package com.theappexperts.parkingapp.data.network;

import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.data.network.services.RequestInterface;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper{

    private RequestInterface requestInterface;

    @Inject
    public AppApiHelper(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    @Override
    public Observable<List<ParkingModel>> getFromApi_ParkingList() {
        return requestInterface.getParkingList();
    }
}
