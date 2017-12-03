package com.theappexperts.parkingapp.view.parkinglist;

import com.theappexperts.parkingapp.view.ui.base.MvpPresenter;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public interface IParkingListMvpPresenter<V extends IParkingListMvpView> extends MvpPresenter<V> {
    void onCallParkingList();
}
