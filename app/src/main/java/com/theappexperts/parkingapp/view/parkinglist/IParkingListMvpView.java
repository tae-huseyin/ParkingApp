package com.theappexperts.parkingapp.view.parkinglist;

import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.data.network.model.ReserveModel;
import com.theappexperts.parkingapp.view.ui.base.MvpView;

import java.util.List;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public interface IParkingListMvpView extends MvpView {

    void onFetchDataSuccess(List<ParkingModel> parkingModels);
    void onFetchDataError(String message);
}
