package com.theappexperts.parkingapp.injection.modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.theappexperts.parkingapp.data.network.constant.Api_Constant;
import com.theappexperts.parkingapp.data.network.services.RequestInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "http://ridecellparking.herokuapp.com/api/v1/parkinglocations/";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() { return Api_Constant.BASE_URL; }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() { return GsonConverterFactory.create(); }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapter() { return RxJava2CallAdapterFactory.create(); }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, CallAdapter.Factory callAdapter, @Named(NAME_BASE_URL) String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .build();
    }

    @Provides
    @Singleton
    RequestInterface provideRequestInterface(Retrofit retrofit)
    {
        return retrofit.create(RequestInterface.class);
    }


}
