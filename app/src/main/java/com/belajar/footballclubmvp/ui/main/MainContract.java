package com.belajar.footballclubmvp.ui.main;

import com.belajar.footballclubmvp.data.model.TeamsItem;

import java.util.List;

public interface MainContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListTeams();
    }
}
