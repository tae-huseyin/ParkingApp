package com.theappexperts.parkingapp.data.network.services;

import com.theappexperts.parkingapp.data.network.constant.Api_Constant;
import com.theappexperts.parkingapp.data.network.model.ParkingModel;
import com.theappexperts.parkingapp.data.network.model.ReserveModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestInterface {
    //creates a get request to the server and stores them in List of ParkingModels
    @GET(Api_Constant.BASE_URL)
    Observable<List<ParkingModel>> getParkingList();

//    @POST(Api_Constant.BASE_URL)
//    Observable<ReserveModel> postReserve(@Path("id") Integer id, @Path("") String reserve);
}
