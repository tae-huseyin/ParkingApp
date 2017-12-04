package com.theappexperts.parkingapp.view.parkinglist.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theappexperts.parkingapp.R;
import com.theappexperts.parkingapp.data.localdb.RealmParkingHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    public ArrayList<RealmParkingHistory> realmParkingHistories;
    public int row_history;
    public Context applicationContext;

    public HistoryAdapter(ArrayList<RealmParkingHistory> realmParkingHistories, int row_history, Context applicationContext) {
        this.realmParkingHistories = realmParkingHistories;
        this.row_history = row_history;
        this.applicationContext = applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(row_history, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvSnippet.setText(realmParkingHistories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return realmParkingHistories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvSnippet)
        TextView tvSnippet;

        Unbinder unbinder;

        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
