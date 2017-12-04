package com.theappexperts.parkingapp.data.localdb;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    Realm realm;

    public RealmController(Realm realm){this.realm = realm;}

    /*public void saveMapData(final RealmParkingSpot realmParkingSpot){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmParkingSpot);
            }
        });
    }*/

    public void previouslyLoaded(final RealmParkingHistory realmParkingHistory){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmParkingHistory);
            }
        });
    }

    //list of all history return
    public ArrayList<RealmParkingHistory> getCustomerList(){
        ArrayList<RealmParkingHistory> history = new ArrayList<>();

        RealmResults<RealmParkingHistory> realmParkingHistoryRealmResults = realm.where(RealmParkingHistory.class).findAll();

        for (RealmParkingHistory realmParkingHistory: realmParkingHistoryRealmResults){
            history.add(realmParkingHistory);
        }

        return history;
    }


}

