package com.belajar.footballclubmvp.data.remote;

import com.belajar.footballclubmvp.helper.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhammad Firdaus on 22/03/2020.
 */
public class ApiClient {
    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
