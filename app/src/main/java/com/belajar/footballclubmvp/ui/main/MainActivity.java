package com.belajar.footballclubmvp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.belajar.footballclubmvp.R;
import com.belajar.footballclubmvp.data.model.TeamsItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    RecyclerView rvTeam;
    SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private final MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Inisiasi widget
        rvTeam = findViewById(R.id.recycler_main);
        swipeRefresh = findViewById(R.id.swipe_main);
        progressBar = findViewById(R.id.pb_main);

        // Merequest data ke API melalui presenter
        mainPresenter.getDataListTeams();

        // Pada saat swipe refresh mengambil data ulang melalui presenter
        swipeRefresh.setOnRefreshListener(() -> {
            // Menghilangkan swiperefresh setelah user swipe ke bawah
            swipeRefresh.setRefreshing(false);
            // Merequest data ke API melalui presenter
            mainPresenter.getDataListTeams();
        });
    }

    @Override
    public void showProgress() {
        // Menghilangkan recyclerview
        rvTeam.setVisibility(View.GONE);
        // Menampilkan loading
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        // Menampilkan recyclerview
        rvTeam.setVisibility(View.VISIBLE);
        // Menghilangkan loading
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDataList(List<TeamsItem> teamsItemList) {
        // Show data to recyclerview
        rvTeam.setLayoutManager(new LinearLayoutManager(this));
        rvTeam.setAdapter(new MainAdapter(this, teamsItemList));
    }

    @Override
    public void showFailureMessage(String msg) {
        // Menampilkan pesan error
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
