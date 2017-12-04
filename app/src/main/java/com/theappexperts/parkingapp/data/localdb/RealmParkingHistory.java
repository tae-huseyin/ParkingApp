package com.theappexperts.parkingapp.data.localdb;

import io.realm.RealmObject;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public class RealmParkingHistory extends RealmObject{

    Integer id;
    String lat;
    String lng;
    String name;
    String costPerMin;
    Integer maxResrMin;
    Integer minResrMin;
    Boolean isReserved;
    String reservedUntil;

    public RealmParkingHistory(){}

//    public RealmParkingHistory(Integer id, String lat, String lng, String name, String costPerMin, Integer maxResrMin, Integer minResrMin, Boolean isReserved, String reservedUntil) {
//        this.id = id;
//        this.lat = lat;
//        this.lng = lng;
//        this.name = name;
//        this.costPerMin = costPerMin;
//        this.maxResrMin = maxResrMin;
//        this.minResrMin = minResrMin;
//        this.isReserved = isReserved;
//        this.reservedUntil = reservedUntil;
//    }

    public RealmParkingHistory(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCostPerMin() {
        return costPerMin;
    }

    public void setCostPerMin(String costPerMin) {
        this.costPerMin = costPerMin;
    }

    public Integer getMaxResrMin() {
        return maxResrMin;
    }

    public void setMaxResrMin(Integer maxResrMin) {
        this.maxResrMin = maxResrMin;
    }

    public Integer getMinResrMin() {
        return minResrMin;
    }

    public void setMinResrMin(Integer minResrMin) {
        this.minResrMin = minResrMin;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(String reservedUntil) {
        this.reservedUntil = reservedUntil;
    }
}
