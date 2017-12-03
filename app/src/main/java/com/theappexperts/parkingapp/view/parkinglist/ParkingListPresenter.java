package com.theappexperts.parkingapp.view.parkinglist;

import com.theappexperts.parkingapp.data.IDataManager;
import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.view.ui.base.BasePresenter;
import com.theappexperts.parkingapp.view.ui.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public class ParkingListPresenter<V extends IParkingListMvpView>
    extends BasePresenter<V>
    implements IParkingListMvpPresenter<V> {

    @Inject
    public ParkingListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable)
    {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCallParkingList(){
        getCompositeDisposable().add(
                getDataManager().getFromApi_ParkingList()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<List<ParkingModel>>() {
                    @Override
                    public void accept(List<ParkingModel> parkingModels) throws Exception {
                        getMvpView().onFetchDataSuccess(parkingModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                })
        );
    }

}
