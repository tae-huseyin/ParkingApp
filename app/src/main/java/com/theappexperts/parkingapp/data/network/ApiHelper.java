package com.theappexperts.parkingapp.data.network;

import com.theappexperts.parkingapp.data.network.model.ParkingModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public interface ApiHelper {
    Observable<List<ParkingModel>> getFromApi_ParkingList();
}
