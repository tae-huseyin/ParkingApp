package com.theappexperts.parkingapp;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.injection.components.ActivityComponent;
import com.theappexperts.parkingapp.injection.components.DaggerActivityComponent;
import com.theappexperts.parkingapp.injection.modules.ActivityModule;
import com.theappexperts.parkingapp.view.parkinglist.IParkingListMvpView;
import com.theappexperts.parkingapp.view.parkinglist.ParkingListPresenter;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, IParkingListMvpView {

    //Google maps object
    GoogleMap mMap;
    private static final String MAP_FRAGMENT_TAG = "map";

    //getting data stuff :?:notso sure ask kalpesh
    ActivityComponent activityComponent;

    @Inject
    ParkingListPresenter<MainActivity> parkingListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dagger stuff
        injectDagger();
        parkingListPresenter.onAttach(this);
        parkingListPresenter.onCallParkingList();
        //end of dagger stuff

        createMapFragment();
    }

    private void createMapFragment()
    {
        // It isn't possible to set a fragment's id programmatically so we set a tag instead and
        // search for it using that.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);

        // We only create a fragment if it doesn't already exist.
        if (mapFragment == null) {
            // To programmatically add the map, we first create a SupportMapFragment.
            mapFragment = SupportMapFragment.newInstance();

            // Then we add it using a FragmentTransaction.
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content, mapFragment, MAP_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }

        mapFragment.setRetainInstance(true);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setBuildingsEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setMaxZoomPreference(25);
        mMap.setMinZoomPreference(15);

        LatLngBounds SAN_FRANCISCO = new LatLngBounds(
                new LatLng(37.692100, -122.521307), new LatLng(37.813489, -122.354833));
        mMap.setLatLngBoundsForCameraTarget (SAN_FRANCISCO);

        LatLng zoomTo = new LatLng(37.773972,-122.431297);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoomTo, 20));
    }

    private void showSnackbar(final String text) {
        View container = findViewById(android.R.id.content);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    //Mvp stuff
    @Override
    public void onFetchDataSuccess(List<ParkingModel> parkingModels) {
        showSnackbar("Data loaded...");
    }

    @Override
    public void onFetchDataError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
    //end of Mvp


    //Dagger
    public void injectDagger()
    {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApp)getApplicationContext()).getApplicationComponent())
                .build();
        activityComponent.inject(this);
    }

    public ActivityComponent getActivityComponent() { return activityComponent; }

    public void setActivityComponent(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    //end of Dagger
}
