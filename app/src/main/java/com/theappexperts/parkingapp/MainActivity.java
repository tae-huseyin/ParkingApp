package com.theappexperts.parkingapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.theappexperts.parkingapp.data.localdb.RealmController;
import com.theappexperts.parkingapp.data.localdb.RealmParkingHistory;
import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.injection.components.ActivityComponent;
import com.theappexperts.parkingapp.injection.components.DaggerActivityComponent;
import com.theappexperts.parkingapp.injection.modules.ActivityModule;
import com.theappexperts.parkingapp.view.parkinglist.IParkingListMvpView;
import com.theappexperts.parkingapp.view.parkinglist.ParkingListPresenter;
import com.theappexperts.parkingapp.view.parkinglist.fragment.HistoryFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, IParkingListMvpView {

    //Google maps object
    GoogleMap mMap;
    private boolean mMapIsReady = false;

    //getting data stuff :?:notso sure ask kalpesh
    ActivityComponent activityComponent;

    //saving history
    private RealmController realmController;
    //fragment
    FragmentManager fragmentManager;
    @BindView(R.id.btn_view)
    FloatingActionButton btnView;
    private Unbinder unbinder;

    @Inject
    ParkingListPresenter<MainActivity> parkingListPresenter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        btnView.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        //start realm
        realmController = new RealmController(Realm.getDefaultInstance());

        if(savedInstanceState == null) {
            //dagger stuff
            injectDagger();
            parkingListPresenter.onAttach(this);
            parkingListPresenter.onCallParkingList();
            //end of dagger stuff

            //map
            createMapFragment();
        }

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                .add(R.id.frame_layout, new HistoryFragment())
                .addToBackStack("ViewHistory")
                .commit();
                btnView.hide();
            }
        });
    }

    //map stuff
    private void createMapFragment()
    {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

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

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        mMap.setOnInfoWindowClickListener(this);
        mMapIsReady = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

//        Integer id;
//        String lat;
//        String lng;
//        String name;
//        String costPerMin;
//        Integer maxResrMin;
//        Integer minResrMin;
//        Boolean isReserved;
//        String reservedUntil;

        RealmParkingHistory realmParkingHistory = new RealmParkingHistory(marker.getSnippet());
        realmController.previouslyLoaded(realmParkingHistory);

        showSnackbar("Added to History...");

    }
    //end of map stuff

    private void showSnackbar(final String text) {
        View container = findViewById(android.R.id.content);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    //Mvp stuff
    @Override
    public void onFetchDataSuccess(List<ParkingModel> parkingModels) {
        showSnackbar("Parking loading...");

        if(mMapIsReady) {
            for (ParkingModel x : parkingModels) {
                double Lat = Double.parseDouble(x.getLat());
                double Lng = Double.parseDouble(x.getLng());
                LatLng pos = new LatLng(Lat, Lng);

                float colour = ((x.getIsReserved()) ? BitmapDescriptorFactory.HUE_RED : BitmapDescriptorFactory.HUE_GREEN);

                mMap.addMarker(new MarkerOptions()
                        .position(pos)
                        .icon(BitmapDescriptorFactory.defaultMarker(colour))
                        .title(x.getId().toString())
                        .snippet("Reserved Until" + "\n" + ((x.getReservedUntil() == null) ? "Available" : x.getReservedUntil())
                                + "\nReservation Information\n"
                                + "\nMinimum Time:\t" + x.getMinReserveTimeMins()
                                + "\nMaximum Time:\t" + x.getMaxReserveTimeMins()
                                + "\nCost per (min):\t" + x.getCostPerMinute())
                        .zIndex(((x.getIsReserved()) ? 0.0f : 1.0f)));
            }
        }
    }

    @Override
    public void onFetchDataError(String message) {
        showSnackbar(message);
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
