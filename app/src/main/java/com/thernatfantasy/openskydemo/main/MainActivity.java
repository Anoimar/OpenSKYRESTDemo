package com.thernatfantasy.openskydemo.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.thernatfantasy.openskydemo.R;
import com.thernatfantasy.openskydemo.viewmodel.FlightDataListModel;
import com.thernatfantasy.openskydemo.database.FlightInfo;
import com.thernatfantasy.openskydemo.ui.FlightInfoRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView flightInfoRecyclerView;
    private FlightInfoRecyclerViewAdapter flightInfoRecyclerViewAdapter;
    private List<FlightInfo> flightInfoList;
    private SearchView searchView;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FlightDataListModel viewModel;
    private LiveData<List<FlightInfo>> flightInfoListDataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecyclerViewWithFlightData();
        createToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setOnQueryTextListener(onQueryTextListener);
        return super.onCreateOptionsMenu(menu);
    }

    private void createRecyclerViewWithFlightData() {
        createEmptyFlightStatesAdapter();
        createRecyclerView();
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            showFlightData(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void createEmptyFlightStatesAdapter() {
        flightInfoList = new ArrayList<>();
        flightInfoRecyclerViewAdapter = new FlightInfoRecyclerViewAdapter(this, flightInfoList);
    }

    private void createRecyclerView() {
        flightInfoRecyclerView = findViewById(R.id.flight_data_recycler_view);
        flightInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightInfoRecyclerView.setAdapter(flightInfoRecyclerViewAdapter);
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(FlightDataListModel.class);
        showFlightData("");

    }

    private void showFlightData(String query){
        if(flightInfoListDataModel != null){
            flightInfoListDataModel.removeObservers(this);
        }
        flightInfoListDataModel = viewModel.getFlightInfoList(query);
        flightInfoListDataModel.observe(MainActivity.this, flightInfos -> {
            if(flightInfos == null){
                return;
            }
            flightInfoRecyclerViewAdapter.refreshFlightList(flightInfos);
        });
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
