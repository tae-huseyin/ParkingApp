package com.theappexperts.parkingapp.view.parkinglist.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theappexperts.parkingapp.R;
import com.theappexperts.parkingapp.data.localdb.RealmController;
import com.theappexperts.parkingapp.data.localdb.RealmParkingHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

public class HistoryFragment extends Fragment {

    RealmController realmController;
    ArrayList<RealmParkingHistory> realmParkingHistories;

    @BindView(R.id.rvHistory)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        realmController = new RealmController(Realm.getDefaultInstance());
        realmParkingHistories = realmController.getHistoryList();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HistoryAdapter(realmParkingHistories, R.layout.row_history, getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
