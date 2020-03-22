package com.belajar.footballclubmvp.data.remote;

import com.belajar.footballclubmvp.data.model.ResponseAllTeam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/v1/json/1/search_all_teams.php")
    Call<ResponseAllTeam> getAllTeam(
            @Query("l") String nameLeague
    );
}
