package com.belajar.footballclubmvp.ui.main;

import com.belajar.footballclubmvp.data.model.ResponseAllTeam;
import com.belajar.footballclubmvp.data.remote.ApiClient;
import com.belajar.footballclubmvp.data.remote.ApiInterface;
import com.belajar.footballclubmvp.helper.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams() {
        // Show loading
        view.showProgress();

        Call<ResponseAllTeam> call = apiInterface.getAllTeam(Constant.LEAGUE_NAME);
        call.enqueue(new Callback<ResponseAllTeam>() {
            @Override
            public void onResponse(Call<ResponseAllTeam> call, Response<ResponseAllTeam> response) {
                // Hide loading
                view.hideProgress();
                if (response.body() != null) {
                    // Show data to view
                    view.showDataList(response.body().getTeams());
                }
            }

            @Override
            public void onFailure(Call<ResponseAllTeam> call, Throwable throwable) {
                // Hide loading
                view.hideProgress();
                view.showFailureMessage(throwable.getMessage());
            }
        });
    }
}
